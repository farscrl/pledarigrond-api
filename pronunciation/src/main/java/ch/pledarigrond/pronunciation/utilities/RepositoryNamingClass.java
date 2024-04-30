package ch.pledarigrond.pronunciation.utilities;

import ch.pledarigrond.common.data.common.RequestContext;

public class RepositoryNamingClass {
    public static String getRegistrationsCollectionName() {
        return "registrations_" + RequestContext.getLanguage().name().toLowerCase();
    }
}
