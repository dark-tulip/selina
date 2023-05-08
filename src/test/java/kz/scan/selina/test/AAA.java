package kz.scan.selina.test;

import kz.scan.selina.service.PythonMLExecutorService;
import org.junit.jupiter.api.Test;


public class AAA {

  PythonMLExecutorService pythonMLExecutorService = new PythonMLExecutorService();

  @Test
  public void ll() {
    pythonMLExecutorService.prepareInput("ghjg ggjhgj");
    System.out.println(pythonMLExecutorService.predict());
  }
}
