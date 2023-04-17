package kz.scan.selina.exceptions;

public class IframeFoundException extends RuntimeException {

  public IframeFoundException(String message) {
    super("Найден iframe: " + message);
  }

}
