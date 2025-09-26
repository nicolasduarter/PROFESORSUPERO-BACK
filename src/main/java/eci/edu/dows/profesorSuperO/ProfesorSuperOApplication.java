package eci.edu.dows.profesorSuperO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ProfesorSuperOApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfesorSuperOApplication.class, args);
	}

}
