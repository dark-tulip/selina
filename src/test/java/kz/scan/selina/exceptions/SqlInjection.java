package kz.scan.selina.exceptions;


public class SqlInjection extends RuntimeException {
  public SqlInjection(String message) {
    super(message);
  }
}
