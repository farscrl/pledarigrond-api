package ch.pledarigrond.api;

import ch.pledarigrond.common.config.PgEnvironment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.nio.file.Paths;

@SpringBootApplication(scanBasePackages = { "ch.pledarigrond" })
@EnableConfigurationProperties({PgEnvironment.class})
@EnableScheduling
@EnableAsync
//@ComponentScan(basePackages = {"ch.pledarigrond.common"})
public class ApiApplication {

	public static void main(String[] args) {
		System.out.println("Current working directory: " + Paths.get("").toAbsolutePath().toString());
		SpringApplication.run(ApiApplication.class, args);
	}
}
