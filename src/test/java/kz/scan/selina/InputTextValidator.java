package kz.scan.selina;

import kz.scan.selina.service.AttackService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;


@SpringJUnitConfig
@SpringBootTest
public class InputTextValidator {

  private static AttackService attackServiceProvider;

  @Autowired
  private AttackService attackService;


  @PostConstruct
  private void init() {
    attackServiceProvider = attackService;

    System.out.println();
  }

  private static Stream<Arguments> argumentScript() {
    Stream<Arguments> scripts = attackServiceProvider.selectAll()
      .stream()
      .map(x -> Arguments.of(x.attackScript));

    System.out.println(scripts);
    return scripts;
  }

  @Test
  public void ll() {
    attackServiceProvider.selectAll();
  }

  @ParameterizedTest
  @MethodSource("argumentScript")
  public void test(String sql) {

    attackService.selectAll();
    System.out.println("AAA: " + sql);

  }

}

