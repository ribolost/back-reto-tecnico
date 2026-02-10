package spring;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("persistence.repository")
@EntityScan("persistence.model")
public class PersistenceConfiguration {
}
