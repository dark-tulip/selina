package kz.scan.selina.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommandExecutionMetadata {

  /**
   * Код ошибки - статус исполнения команды
   */
  int statusCode;


  /**
   * stdOut программы
   */
  String output;

  public static CommandExecutionMetadata of(int statusCode, String outputResult) {
    return new CommandExecutionMetadata(statusCode, outputResult);
  }

}
