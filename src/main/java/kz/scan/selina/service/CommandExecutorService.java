package kz.scan.selina.service;


import kz.scan.selina.model.CommandExecutionMetadata;

/**
 * Сервис для исполнения команд в Java Runtime
 */
interface CommandExecutorService {

  /**
   * Исполнение команды в терминальной оболочке
   *
   * @param cmd - строковое представление исполяемой bash команды
   * @return command execution output
   */
  CommandExecutionMetadata execute(String cmd);


}
