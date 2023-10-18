package tt.observability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tt.logging.CEFlogService;

@SpringBootApplication
@EnableJpaRepositories("tt.observability.*")
@EntityScan("tt.observability.*")
public class ObservabilityApplication {

  public static void main(String[] args) {
    SpringApplication.run(ObservabilityApplication.class, args);
  }

  @Bean
  public CEFlogService ceflogService() {
    return new CEFlogService();
  }
}
