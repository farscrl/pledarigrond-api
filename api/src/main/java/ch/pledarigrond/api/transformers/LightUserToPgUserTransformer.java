package ch.pledarigrond.api.transformers;

import ch.pledarigrond.common.data.common.LightUserInfo;
import ch.pledarigrond.mongodb.model.PgUserInfo;

public class LightUserToPgUserTransformer {

    public static PgUserInfo toPgUserInfo(LightUserInfo lightUser) {
        PgUserInfo pgUserInfo = new PgUserInfo();
        return updatePgUserInfo(pgUserInfo, lightUser);
    }

    public static PgUserInfo updatePgUserInfo(PgUserInfo pgUserInfo, LightUserInfo lightUser) {
        pgUserInfo.setEmail(lightUser.getEmail());
        pgUserInfo.setPassword(lightUser.getPassword());
        pgUserInfo.setAdmin(lightUser.isAdmin());
        pgUserInfo.setPuterRole(lightUser.getRoles().getPuterRole());
        pgUserInfo.setRumantschgrischunRole(lightUser.getRoles().getRumantschgrischunRole());
        pgUserInfo.setSurmiranRole(lightUser.getRoles().getSurmiranRole());
        pgUserInfo.setSursilvanRole(lightUser.getRoles().getSursilvanRole());
        pgUserInfo.setSutsilvanRole(lightUser.getRoles().getSutsilvanRole());
        pgUserInfo.setValladerRole(lightUser.getRoles().getValladerRole());
        pgUserInfo.setNamesRole(lightUser.getRoles().getNamesRole());

        return pgUserInfo;
    }
}
