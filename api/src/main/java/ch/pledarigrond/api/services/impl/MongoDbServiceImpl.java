package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.api.services.MongoDbService;
import ch.pledarigrond.api.services.UserService;
import ch.pledarigrond.common.config.Constants;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.PgException;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;
import ch.pledarigrond.mongodb.model.PgUserInfo;
import ch.pledarigrond.mongodb.operations.*;
import ch.pledarigrond.mongodb.util.DBCommandQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

public class MongoDbServiceImpl implements MongoDbService {
    private DBCommandQueue queue = DBCommandQueue.getInstance();

    @Autowired
    private UserService userService;

    @Autowired
    private LuceneService luceneService;

    @Secured( { Constants.Roles.TRUSTED_EX_3, Constants.Roles.TRUSTED_IN_4, Constants.Roles.ADMIN_5 })
    public void insert(LexEntry entry, String locale) throws Exception {
        String login = getUserLogin();

        addUserInfo(entry.getCurrent());
        LexEntry modified = queue.push(new InsertOperation(entry, locale).setLogin(login), locale);
        luceneService.update(modified);
    }

    public void suggestNewEntry(LexEntry entry, String locale) throws Exception {
        if(entry == null) throw new InvalidEntryException("LexEntry must not be null!");
        if(entry.getVersionHistory() == null || entry.getVersionHistory().size() != 1) throw new InvalidEntryException("Invalid suggestion!");
        validateUserModification(entry.getCurrent(), entry.getMostRecent());
        String login = getUserLogin();
        addUserInfo(entry.getMostRecent());
        LexEntry modified = queue.push(new InsertOperation(entry, locale).setLogin(login).asSuggestion(), locale);
        luceneService.update(modified);
    }

    public void suggestUpdate(LexEntry oldEntry, LemmaVersion newEntry, String locale) throws Exception {
        if(newEntry == null) throw new InvalidEntryException("Lemma must not be null!");
        if(oldEntry == null) throw new InvalidEntryException("LexEntry must not be null!");
        oldEntry = Converter.convertToLexEntry(Database.getInstance(locale).getById(oldEntry.getId()));
        if(oldEntry == null) throw new InvalidEntryException("Entry not found!");
        validateUserModification(oldEntry.getCurrent(), newEntry);
        String login = getUserLogin();
        addUserInfo(newEntry);
        LexEntry modified = queue.push(new UpdateOperation(oldEntry, newEntry, locale).setLogin(login).asSuggestion(), locale);
        luceneService.update(modified);
    }

    @Secured( { Constants.Roles.TRUSTED_EX_3, Constants.Roles.TRUSTED_IN_4, Constants.Roles.ADMIN_5 })
    public void update(LexEntry oldEntry, LemmaVersion newEntry, String locale) throws Exception {
        if(newEntry == null) throw new InvalidEntryException("Lemma must not be null!");
        if(oldEntry == null) throw new InvalidEntryException("LexEntry must not be null!");
        String login = getUserLogin();
        addUserInfo(newEntry);
        LexEntry modified = queue.push(new UpdateOperation(oldEntry, newEntry, locale).setLogin(login), locale);
        luceneService.update(modified);
    }


    @Secured( { Constants.Roles.TRUSTED_EX_3, Constants.Roles.TRUSTED_IN_4, Constants.Roles.ADMIN_5 })
    public void delete(LexEntry entry, String locale) throws Exception {
        String login = getUserLogin();
        queue.push(new DeleteOperation(entry, locale).setLogin(login), locale);
        luceneService.delete(entry);
    }

    @Secured( { Constants.Roles.TRUSTED_EX_3, Constants.Roles.TRUSTED_IN_4, Constants.Roles.ADMIN_5 })
    public void restore(LemmaVersion invalid, LemmaVersion valid, String locale) throws Exception {
//		if(valid == null) throw new InvalidEntryException("Lemma must not be null!");
//		if(invalid == null) throw new InvalidEntryException("Lemma must not be null!");
        String login = getUserLogin();
        addUserInfo(valid);
        LexEntry modified = queue.push(new RestoreOperation(invalid, valid).setLogin(login), locale);
        luceneService.update(modified);
    }

    @Secured( { Constants.Roles.TRUSTED_EX_3, Constants.Roles.TRUSTED_IN_4, Constants.Roles.ADMIN_5 })
    public void accept(LexEntry entry, LemmaVersion version, String locale) throws Exception {
        String login = getUserLogin();
        LexEntry modified = queue.push(new AcceptOperation(entry, version, locale).setLogin(login), locale);
        luceneService.update(modified);
    }

    @Secured( { Constants.Roles.TRUSTED_EX_3, Constants.Roles.TRUSTED_IN_4, Constants.Roles.ADMIN_5 })
    public void reject(LexEntry entry, LemmaVersion version, String locale) throws Exception {
        String login = getUserLogin();
        addUserInfo(version);
        LexEntry modified = queue.push(new RejectOperation(entry, version, locale).setLogin(login), locale);
        luceneService.update(modified);
    }

    @Secured( { Constants.Roles.TRUSTED_IN_4, Constants.Roles.ADMIN_5 })
    public void acceptAfterUpdate(LexEntry entry, LemmaVersion suggested, LemmaVersion modified, String locale) throws Exception {
        String login = getUserLogin();
        LexEntry mod = queue.push(new AcceptAfterUpdateOperation(entry, suggested, modified, locale).setLogin(login), locale);
        luceneService.update(mod);
    }

    @Secured( { Constants.Roles.TRUSTED_IN_4, Constants.Roles.ADMIN_5 })
    public void dropOutdatedHistory(LexEntry entry, String locale) throws Exception {
        String login = getUserLogin();
        LexEntry modified = queue.push(new DropHistoryOperation(entry, locale).setLogin(login), locale);
        luceneService.update(modified);
    }

    @Secured( { Constants.Roles.TRUSTED_IN_4, Constants.Roles.ADMIN_5 })
    public List<LexEntry> updateOrder(boolean firstLang, List<LemmaVersion> ordered, String locale) throws Exception {
        String login = getUserLogin();
        List<LexEntry> modified = queue.pushMulti(new UpdateOrderOperation(firstLang, ordered, locale).setLogin(login), locale);
        luceneService.updateAll(modified);
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
