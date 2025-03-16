package ch.pledarigrond.api.config;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.database.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitializationBean implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(InitializationBean.class);

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    private UserService userService;

    @Override
    public void afterPropertiesSet() {
        initDefaultUser();
    }

    private void initDefaultUser() {
        if (!pgEnvironment.getAdminUsername().isEmpty() && !pgEnvironment.getAdminPassword().isEmpty()) {
            if (userService.userExists(pgEnvironment.getAdminUsername())) {
                logger.info("Default user already exists. Skipping creation.");
                return;
            }

            logger.info("Creating default user{}", pgEnvironment.getAdminUsername());
            userService.generateDefaultUser(pgEnvironment.getAdminUsername(), pgEnvironment.getAdminPassword());
        }
    }
}
