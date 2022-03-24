package ch.pledarigrond.mongodb.util;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;

public class DbSelector {
    public static String getDbNameByLanguage(PgEnvironment pgEnvironment, Language language) {
        return switch (language) {
            case PUTER -> pgEnvironment.getDbPuter();
            case RUMANTSCHGRISCHUN -> pgEnvironment.getDbRumantschgrischun();
            case SURMIRAN -> pgEnvironment.getDbSurmiran();
            case SURSILVAN -> pgEnvironment.getDbSursilvan();
            case SUTSILVAN -> pgEnvironment.getDbSutsilvan();
            case VALLADER -> pgEnvironment.getDbVallader();
        };
    }

    public static String getDbCronByLanguage(PgEnvironment pgEnvironment, Language language) {
        return switch (language) {
            case PUTER -> pgEnvironment.getBackupPuterCron();
            case RUMANTSCHGRISCHUN -> pgEnvironment.getBackupRumantschgrischunCron();
            case SURMIRAN -> pgEnvironment.getBackupSurmiranCron();
            case SURSILVAN -> pgEnvironment.getBackupSursilvanCron();
            case SUTSILVAN -> pgEnvironment.getBackupSutsilvanCron();
            case VALLADER -> pgEnvironment.getBackupValladerCron();
        };
    }
}
