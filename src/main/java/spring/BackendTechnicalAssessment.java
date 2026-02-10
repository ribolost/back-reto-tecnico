package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"web", "service", "persistence"})
//@SpringBootApplication
public class BackendTechnicalAssessment {
    public static void main(String[] args) {
        SpringApplication.run(new Class[]{BackendTechnicalAssessment.class,
                PersistenceConfiguration.class}, args);
    }

}
