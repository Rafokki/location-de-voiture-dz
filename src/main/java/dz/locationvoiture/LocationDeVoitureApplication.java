package dz.locationvoiture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "dz.locationvoiture.repository")
public class LocationDeVoitureApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationDeVoitureApplication.class, args);
    }

}
