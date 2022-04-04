package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.api.services.MongoDbService;
import ch.pledarigrond.api.services.UserService;
import ch.pledarigrond.common.config.Constants;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.DictionaryLanguage;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.PgException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;
import ch.pledarigrond.mongodb.model.PgUserInfo;
import ch.pledarigrond.mongodb.operations.*;
import ch.pledarigrond.mongodb.util.DBCommandQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

@Service
public class MongoDbServiceImpl implements MongoDbService {
    private DBCommandQueue queue = DBCommandQueue.getInstance();

    @Autowired
    private UserService userService;

    @Autowired
    private LuceneService luceneService;

    @Autowired
    private PgEnvironment pgEnvironment;

    @Override
    public void insert(Language language, LexEntry entry) throws Exception {
        String login = getUserLogin();

        addUserInfo(entry.getCurrent());
        LexEntry modified = queue.push(new InsertOperation(language, entry).setLogin(login), language);
        luceneService.update(language, modified);
    }

    @Override
    public void suggestNewEntry(Language language, LexEntry entry) throws Exception {
        if(entry == null) throw new InvalidEntryException("LexEntry must not be null!");
        if(entry.getVersionHistory() == null || entry.getVersionHistory().size() != 1) throw new InvalidEntryException("Invalid suggestion!");
        validateUserModification(entry.getCurrent(), entry.getMostRecent());
        String login = getUserLogin();
        addUserInfo(entry.getMostRecent());
        LexEntry modified = queue.push(new InsertOperation(language, entry).setLogin(login).asSuggestion(), language);
        luceneService.update(language, modified);
    }

    @Override
    public void suggestUpdate(Language language, LexEntry oldEntry, LemmaVersion newEntry) throws Exception {
        if(newEntry == null) throw new InvalidEntryException("Lemma must not be null!");
        if(oldEntry == null) throw new InvalidEntryException("LexEntry must not be null!");
        oldEntry = Converter.convertToLexEntry(Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)).getById(oldEntry.getId()));
        if(oldEntry == null) throw new InvalidEntryException("Entry not found!");
        validateUserModification(oldEntry.getCurrent(), newEntry);
        String login = getUserLogin();
        addUserInfo(newEntry);
        LexEntry modified = queue.push(new UpdateOperation(language, oldEntry, newEntry).setLogin(login).asSuggestion(), language);
        luceneService.update(language, modified);
    }

    @Override
    public void update(Language language, LexEntry oldEntry, LemmaVersion newEntry) throws Exception {
        if(newEntry == null) throw new InvalidEntryException("Lemma must not be null!");
        if(oldEntry == null) throw new InvalidEntryException("LexEntry must not be null!");
        String login = getUserLogin();
        addUserInfo(newEntry);
        LexEntry modified = queue.push(new UpdateOperation(language, oldEntry, newEntry).setLogin(login), language);
        luceneService.update(language, modified);
    }

    @Override
    public void delete(Language language, LexEntry entry) throws Exception {
        String login = getUserLogin();
        queue.push(new DeleteOperation(language, entry).setLogin(login), language);
        luceneService.delete(language, entry);
    }

    @Override
    public void restore(Language language, LemmaVersion invalid, LemmaVersion valid) throws Exception {
//		if(valid == null) throw new InvalidEntryException("Lemma must not be null!");
//		if(invalid == null) throw new InvalidEntryException("Lemma must not be null!");
        String login = getUserLogin();
        addUserInfo(valid);
        LexEntry modified = queue.push(new RestoreOperation(invalid, valid).setLogin(login), language);
        luceneService.update(language, modified);
    }

    @Override
    public void accept(Language language, LexEntry entry, LemmaVersion version) throws Exception {
        String login = getUserLogin();
        LexEntry modified = queue.push(new AcceptOperation(language, entry, version).setLogin(login), language);
        luceneService.update(language, modified);
    }

    @Override
    public void reject(Language language, LexEntry entry, LemmaVersion version) throws Exception {
        String login = getUserLogin();
        addUserInfo(version);
        LexEntry modified = queue.push(new RejectOperation(language, entry, version).setLogin(login), language);
        luceneService.update(language, modified);
    }

    @Override
    public void acceptAfterUpdate(Language language, LexEntry entry, LemmaVersion suggested, LemmaVersion modified) throws Exception {
        String login = getUserLogin();
        LexEntry mod = queue.push(new AcceptAfterUpdateOperation(language, entry, suggested, modified).setLogin(login), language);
        luceneService.update(language, mod);
    }

    @Override
    public void dropOutdatedHistory(Language language, LexEntry entry) throws Exception {
        String login = getUserLogin();
        LexEntry modified = queue.push(new DropHistoryOperation(language, entry).setLogin(login), language);
        luceneService.update(language, modified);
    }

    @Override
    public List<LexEntry> updateOrder(Language language, DictionaryLanguage dictionaryLanguage, List<LemmaVersion> ordered) throws Exception {
        String login = getUserLogin();
        List<LexEntry> modified = queue.pushMulti(new UpdateOrderOperation(language, dictionaryLanguage, ordered).setLogin(login), language);
        luceneService.updateAll(language, modified);
        return modified;
    }

    private void validateUserModification(LemmaVersion current, LemmaVersion version) throws PgException {
        // Server-Side validation of a user modification: Check that only fields which are allowed to modify are sent
        ArrayList<String> fields = new ArrayList<>(Arrays.asList("DStichwort", "RStichwort", LemmaVersion.EMAIL, LemmaVersion.COMMENT));
        version.getEntryValues().keySet().retainAll(fields);
        if(current != null) {
            // Add values of non-user-fields to the suggestion
            Map<String, String> allowed = new HashMap<String, String>(version.getEntryValues());
            version.getEntryValues().putAll(current.getEntryValues());
            // Overwrite user-fields with suggested entries
            version.getEntryValues().putAll(allowed);
        }
        // Check the length of each field: Normal entries must be less than 200 characters,
        // a comment must be less than 500 chars.
        for (String key : fields) {
            String value = version.getEntryValue(key);
            if(value != null) {
                if(LemmaVersion.COMMENT.equals(key)) {
                    if(value.length() > 500) {
						throw new PgException("Please limit your comment to 500 characters.");
                    }
                } else {
                    if(value.length() > 200) {
						throw new PgException("A field cannot contain more than 200 characters.");

                    }
                }
            }
        }
    }

    private String getUserLogin() {
        try {
            PgUserInfo user = userService.getCurrentUserOrDefaultUser();
            return user.getEmail();

        } catch (Exception e) {
            throw new BadCredentialsException("Failed to get user login");
        }
    }

    private void addUserInfo(LemmaVersion lemma) {
        String ip = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr();
        lemma.setIP(ip);
        //lemma.setCreatorRole(userService.getOrCreateCurrentUser().getRole()); // TODO(CGA): Add again
    }
}
