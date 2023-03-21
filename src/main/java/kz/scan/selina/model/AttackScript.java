package kz.scan.selina.model;


import lombok.Data;
import lombok.experimental.Accessors;


@Data
public class AttackScript {

  /**
   * Идентификатор атаки
   */
  long scriptId;

  /**
   * Идентификатор атаки
   */
  long attackId;

  /**
   * Серъезность выполненного скрипта
   */
  long severityId;

  /**
   * Cодержимое скрипта
   */
  String attackScript;

}
