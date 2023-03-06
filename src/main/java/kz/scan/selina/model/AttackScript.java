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
  long attackScriptId;

  /**
   * Идентификатор атаки
   */
  long attackId;

  /**
   * Серъезность выполненного скрипта
   */
  long vulnerabilitySeverityId;

  /**
   * Cодержимое скрипта
   */
  String attackScript;

}
