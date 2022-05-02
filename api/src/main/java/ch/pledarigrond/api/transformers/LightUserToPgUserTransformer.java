package ch.pledarigrond.api.transformers;

import ch.pledarigrond.common.data.common.LightUserInfo;
import ch.pledarigrond.mongodb.model.PgUser;

public class LightUserToPgUserTransformer {

    public static PgUser toPgUserInfo(LightUserInfo lightUser) {
        PgUser pgUser = new PgUser();
        return updatePgUserInfo(pgUser, lightUser);
    }

    public static PgUser updatePgUserInfo(PgUser pgUser, LightUserInfo lightUser) {
        pgUser.setEmail(lightUser.getEmail());
        pgUser.setPassword(lightUser.getPassword());
        pgUser.setAdmin(lightUser.isAdmin());
        pgUser.setPuterRole(lightUser.getRoles().getPuterRole());
        pgUser.setRumantschgrischunRole(lightUser.getRoles().getRumantschgrischunRole());
        pgUser.setSurmiranRole(lightUser.getRoles().getSurmiranRole());
        pgUser.setSursilvanRole(lightUser.getRoles().getSursilvanRole());
        pgUser.setSutsilvanRole(lightUser.getRoles().getSutsilvanRole());
        pgUser.setValladerRole(lightUser.getRoles().getValladerRole());
        pgUser.setNamesRole(lightUser.getRoles().getNamesRole());

        return pgUser;
    }
}
