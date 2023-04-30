package kz.scan.selina.exceptions;


public class XssDetection extends RuntimeException {
  public XssDetection() {
    super();
  }

  public XssDetection(String message) {
    super(message);
  }

}
