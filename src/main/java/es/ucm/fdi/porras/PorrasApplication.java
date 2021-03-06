package es.ucm.fdi.porras;

import es.ucm.fdi.porras.storage.StorageProperties;
import es.ucm.fdi.porras.storage.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;


import java.util.Arrays;


@SpringBootApplication
@Slf4j
@EnableConfigurationProperties(StorageProperties.class)
public class PorrasApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PorrasApplication.class);
		Environment env = app.run(args).getEnvironment();
		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running!\n\tAccess URLs:\n\t" +
						"\tLocal:     http://127.0.0.1:{}\n\t" + "\tDatabase:  http://127.0.0.1:{}/h2-console\n\t" +
						"Running with Spring profile(s) : {}" +
						"\n----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				env.getProperty("server.port"), env.getProperty("server.port"), Arrays.toString(env.getActiveProfiles()));

	}

    /*@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }*/

}
