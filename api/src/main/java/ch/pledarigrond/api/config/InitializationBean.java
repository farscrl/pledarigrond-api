package ch.pledarigrond.api.config;

import ch.pledarigrond.api.services.UserService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.mongodb.exceptions.InvalidUserException;
import ch.pledarigrond.mongodb.model.PgUserInfo;
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
    public void afterPropertiesSet() throws Exception {
        initUsers();
    }

    private void initUsers() throws InvalidUserException {
        if (!pgEnvironment.getAdminUsername().equals("") && !pgEnvironment.getAdminPassword().equals("")) {
            logger.info("Creating new user");

            if (userService.userExists(pgEnvironment.getAdminUsername())) {
                logger.info("User already exists. Skipping user creation.");
                return;
            }

            PgUserInfo pgUserInfo = new PgUserInfo(pgEnvironment.getAdminUsername(), pgEnvironment.getAdminPassword());

            userService.insert(pgUserInfo);
        }
    }
}
