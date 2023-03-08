package kz.scan.selina.dto;


import kz.scan.selina.enums.VulnerabilitySeverity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

import static kz.scan.selina.utils.RndGenerator.*;

/**
 * Объект передачи данных для клиента. Модель собранной информации
 */
@Data
@Accessors
public class AttackDto {

  /**
   * Идентификатор атаки
   */
  public long scriptId;

  /**
   * Название / Тип атакуемого скрипта
   */
  public String attackName;

  /**
   * Скрипт совершенной атаки
   */
  public String attackScript;

  /**
   * Executed date
   */
  public Date executedDate = new Date();

  /**
   * Серъезность уязвимости
   */
  public VulnerabilitySeverity severityType;


  public static AttackDto rndAttackDto() {
    AttackDto attackDto = new AttackDto();

    attackDto.scriptId = rndInt();
    attackDto.attackName = rndStr();
    attackDto.attackScript = rndStr(50);
    attackDto.executedDate = rndDate();
    attackDto.severityType = rndEnum(VulnerabilitySeverity.class);

    return attackDto;
  }
}
