package kz.scan.selina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static kz.scan.selina.config.Migration.runMigration;

@SpringBootApplication
public class SelinaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SelinaApplication.class, args);
    runMigration();
  }

}
