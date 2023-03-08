package kz.scan.selina;

import kz.scan.selina.model.Attack;
import kz.scan.selina.utils.RndGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static kz.scan.selina.config.Migration.runMigration;
import static kz.scan.selina.dto.AttackDto.rndAttackDto;

@SpringBootApplication
public class SelinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelinaApplication.class, args);
		System.out.println(rndAttackDto());

		Attack attackName = new Attack();
		attackName.setAttackId(RndGenerator.rndInt());
		attackName.setAttackName(RndGenerator.rndStr());

		runMigration();

	}



}
