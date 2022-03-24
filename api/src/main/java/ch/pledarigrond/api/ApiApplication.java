package ch.pledarigrond.api;

import ch.pledarigrond.common.config.PgEnvironment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { "ch.pledarigrond" })
@EnableConfigurationProperties({PgEnvironment.class})
@EnableScheduling
//@ComponentScan(basePackages = {"ch.pledarigrond.common"})
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
