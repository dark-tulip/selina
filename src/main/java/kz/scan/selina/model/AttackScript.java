package kz.scan.selina.model;


import kz.scan.selina.enums.VulnerabilitySeverity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;


@Data
@Accessors
@Entity
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
