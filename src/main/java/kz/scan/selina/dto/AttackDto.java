package kz.scan.selina.dto;


/**
 * Объект передачи данных для клиента. Модель собранной информации
 */
public class AttackDto {

  /**
   * Идентификатор атаки
   */
  long attackId;

  /**
   * Название / Тип атакуемого скрипта
   */
  String attackName;

  /**
   * Скрипт совершенной атаки
   */
  String attackScript;

}
