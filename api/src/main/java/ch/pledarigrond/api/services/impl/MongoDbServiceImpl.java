package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.api.services.MongoDbService;
import ch.pledarigrond.api.services.UserService;
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
import ch.pledarigrond.mongodb.model.PgUser;
import ch.pledarigrond.mongodb.operations.*;
import ch.pledarigrond.mongodb.util.DBCommandQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class MongoDbServiceImpl implements MongoDbService {
    private DBCommandQueue queue;

    @Autowired
    private UserService userService;

    @Autowired
    private LuceneService luceneService;

    @Autowired
    private PgEnvironment pgEnvironment;

    @PostConstruct
    public void init() {
        queue = DBCommandQueue.getInstance(pgEnvironment);
    }

    @Override
    public void insert(Language language, LexEntry entry, boolean asSuggestion) throws Exception {
        String login = getUserLogin();

        addUserInfo(language, entry.getCurrent());
        LexEntry modified;
        if (asSuggestion) {
            modified = queue.push(new InsertOperation(pgEnvironment, language, entry).setLogin(login).asSuggestion(), language);
        } else {
            modified = queue.push(new InsertOperation(pgEnvironment, language, entry).setLogin(login), language);
        }

        // lemmaVersion didn't have a lexEntryId (as this ID is created after the LemmaVersion).
        // we add the lexEntryId to the lemmaVersions, so that the data is indexed correctly by lucene
        for (LemmaVersion lv: modified.getVersionHistory()) {
            lv.setLexEntryId(modified.getId());
        }

        luceneService.update(language, modified);
    }

    @Override
    public void suggestNewEntry(Language language, LexEntry entry) throws Exception {
        if(entry == null) throw new InvalidEntryException("LexEntry must not be null!");
        if(entry.getVersionHistory() == null || entry.getVersionHistory().size() != 1) throw new InvalidEntryException("Invalid suggestion!");
        validateUserModification(entry.getCurrent(), entry.getMostRecent());
        String login = getUserLogin();
        addUserInfo(language, entry.getMostRecent());
        LexEntry modified = queue.push(new InsertOperation(pgEnvironment, language, entry).setLogin(login).asSuggestion(), language);
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
        addUserInfo(language, newEntry);
        LexEntry modified = queue.push(new UpdateOperation(pgEnvironment, language, oldEntry, newEntry).setLogin(login).asSuggestion(), language);
        luceneService.update(language, modified);
    }

    // attention: this update function creates a suggestion, not a direct update, it is currently used for public
    // suggestion creation. There is also the function 'suggestUpdate', maybe the two can be merged.
    // If you need to update and accept, use the 'acceptAfterUpdate' function.
    @Override
    public void update(Language language, LexEntry lexEntry, LemmaVersion newVersion) throws Exception {
        if(newVersion == null) throw new InvalidEntryException("Lemma must not be null!");
        if(lexEntry == null) throw new InvalidEntryException("LexEntry must not be null!");

        // set internal id
        newVersion.setInternalId(lexEntry.getNextInternalId());

        String login = getUserLogin();
        addUserInfo(language, newVersion);
        LexEntry modified = queue.push(new UpdateOperation(pgEnvironment, language, lexEntry, newVersion).setLogin(login).asSuggestion(), language);
        luceneService.update(language, modified);
    }

    @Override
    public void reviewLater(Language language, LexEntry lexEntry) throws  Exception {
        if(lexEntry == null) throw new InvalidEntryException("Lemma must not be null!");
        if (lexEntry.getMostRecent().getPgValues().get(LemmaVersion.REVIEW_LATER) != null) {
            lexEntry.getMostRecent().getPgValues().replace(LemmaVersion.REVIEW_LATER, "true");
        }
        LexEntry modified = queue.push(new ReviewLaterOperation(pgEnvironment, language, lexEntry).asSuggestion(), language);
        luceneService.update(language, modified);
    }

    @Override
    public void delete(Language language, LexEntry entry) throws Exception {
        String login = getUserLogin();
        queue.push(new DeleteOperation(pgEnvironment, language, entry).setLogin(login), language);
        luceneService.delete(language, entry);
    }

    @Override
    public void restoreOldVersion(Language language, LemmaVersion invalid, LemmaVersion valid) throws Exception {
        String login = getUserLogin();
        addUserInfo(language, valid);
        LexEntry modified = queue.push(new RestoreOperation(invalid, valid).setLogin(login), language);
        luceneService.update(language, modified);
    }

    @Override
    public void accept(Language language, LexEntry entry, LemmaVersion version) throws Exception {
        String login = getUserLogin();
        LexEntry modified = queue.push(new AcceptOperation(pgEnvironment, language, entry, version).setLogin(login), language);
        luceneService.update(language, modified);
    }

    @Override
    public void reject(Language language, LexEntry entry, LemmaVersion version) throws Exception {
        String login = getUserLogin();
        addUserInfo(language, version);
        LexEntry modified = queue.push(new RejectOperation(pgEnvironment, language, entry, version).setLogin(login), language);
        luceneService.update(language, modified);
    }

    @Override
    public void acceptAfterUpdate(Language language, LexEntry entry, LemmaVersion suggested, LemmaVersion modified) throws Exception {
        String login = getUserLogin();
        LexEntry mod = queue.push(new AcceptAfterUpdateOperation(pgEnvironment, language, entry, suggested, modified).setLogin(login), language);
        luceneService.update(language, mod);
    }

    @Override
    public void dropOutdatedHistory(Language language, LexEntry entry) throws Exception {
        String login = getUserLogin();
        LexEntry modified = queue.push(new DropHistoryOperation(pgEnvironment, language, entry).setLogin(login), language);
        luceneService.update(language, modified);
    }

    @Override
    public List<LexEntry> updateOrder(Language language, DictionaryLanguage dictionaryLanguage, List<LemmaVersion> ordered) throws Exception {
        String login = getUserLogin();
        List<LexEntry> modified = queue.pushMulti(new UpdateOrderOperation(pgEnvironment, language, dictionaryLanguage, ordered).setLogin(login), language);
        luceneService.updateAll(language, modified);
        return modified;
    }

    private void validateUserModification(LemmaVersion current, LemmaVersion version) throws PgException {
        // Server-Side validation of a user modification: Check that only fields which are allowed to modify are sent
        ArrayList<String> fields = new ArrayList<>(Arrays.asList("DStichwort", "RStichwort", LemmaVersion.EMAIL, LemmaVersion.COMMENT));
        version.getLemmaValues().keySet().retainAll(fields);
        if(current != null) {
            // Add values of non-user-fields to the suggestion
            Map<String, String> allowed = new HashMap<String, String>(version.getLemmaValues());
            version.getLemmaValues().putAll(current.getLemmaValues());
            // Overwrite user-fields with suggested entries
            version.getLemmaValues().putAll(allowed);
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
            PgUser user = userService.getCurrentUserOrDefaultUser();
            return user.getEmail();

        } catch (Exception e) {
            throw new BadCredentialsException("Failed to get user login");
        }
    }

    private void addUserInfo(Language language, LemmaVersion lemma) {
        String ip = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr();
        lemma.setIP(ip);
        lemma.setCreatorRole(userService.getCurrentUserOrDefaultUser().getRoleByLanguage(language));
    }
}
