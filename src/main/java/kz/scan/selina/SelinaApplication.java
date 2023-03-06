package kz.scan.selina;

import kz.scan.selina.model.AttackName;
import kz.scan.selina.utils.RndGenerator;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static kz.scan.selina.config.DbConfig.openConnection;
import static kz.scan.selina.config.Migration.runMigration;
import static kz.scan.selina.dto.AttackDto.rndAttackDto;

@SpringBootApplication
public class SelinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelinaApplication.class, args);
		System.out.println(rndAttackDto());

		AttackName attackName = new AttackName();
		attackName.setAttackId(RndGenerator.rndInt());
		attackName.setAttackName(RndGenerator.rndStr());

		runMigration();

	}



}
