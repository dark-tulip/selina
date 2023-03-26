package kz.scan.selina;

import kz.scan.selina.service.AttackService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


public class InputTextValidator {

  private static final AttackService asp = new AttackService();

  private static Stream<Arguments> argumentScript() {
    Stream<Arguments> scripts = asp.selectAll().stream().map(x -> Arguments.of(x.attackScript));

    System.out.println(scripts);
    return scripts;
  }

  @ParameterizedTest
  @MethodSource("argumentScript")
  public void test(String sql) {

    asp.selectAll();
    System.out.println("AAA: " + sql);

  }

}

