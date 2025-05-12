package dfoodbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "dfoodbackend.repositories")
public class DfoodbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DfoodbackendApplication.class, args);
	}

}
