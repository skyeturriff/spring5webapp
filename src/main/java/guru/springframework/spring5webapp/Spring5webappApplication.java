package guru.springframework.spring5webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * When the application is run, useful output will be logged to the
 * console. An important info is the output of the following property:
 *
 * o.s.b.a.h2.H2ConsoleAutoConfiguration:
 * H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:testdb'
 *
 * That means an in-memory database is created, with the schema name
 * of testdb. Note that spring.h2.console.enabled=true must be set in
 * the application.properties file. You can then login to the H2 console
 * through browser using that url.
 *
 * When logging in, ensure that the JDBC URL: matches the one provided
 * by the application output on startup.
 *
 * This is a great tool for getting feedback quickly while developing
 * or making changes to your domain model. You can see what Hibernate
 * is creating in the backend.
 */
@SpringBootApplication
public class Spring5webappApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring5webappApplication.class, args);
	}

}
