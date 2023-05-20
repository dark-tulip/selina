package kz.scan.selina.exceptions;

public class VulnerableFileUploadedException extends RuntimeException {

  public VulnerableFileUploadedException(String message) {
    super("Загружен уязвимый файл: " + message);
  }

}
