package kz.scan.selina.model;


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
   * Cодержимое скрипта
   */
  String attackScript;

}
