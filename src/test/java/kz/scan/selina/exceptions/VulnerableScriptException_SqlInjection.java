package kz.scan.selina.exceptions;


public class VulnerableScriptException_SqlInjection extends RuntimeException {
  public VulnerableScriptException_SqlInjection(String message) {
    super("Обнаружена SQL инъекция, реакция на скрипт: " + message);
  }
}
