package kz.scan.selina.test;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public interface InjectionBase {

  static Stream<Arguments> getDataSet() {
    return null;
  }

  boolean checkForInjection(Object data);

}
