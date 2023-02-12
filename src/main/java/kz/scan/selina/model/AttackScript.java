package kz.scan.selina.model;


import kz.scan.selina.enums.VulnerabilitySeverity;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors
public class AttackScript {

  /**
   * Идентификатор атаки
   */
  long attackId;

  /**
   * Серъезность выполненного скрипта
   */
  VulnerabilitySeverity vulnerabilitySeverity;

  /**
   * Cодержимое скрипта
   */
  String attackScript;

}
