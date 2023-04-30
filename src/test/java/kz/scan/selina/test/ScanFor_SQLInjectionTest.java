package kz.scan.selina.test;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import kz.scan.selina.configs.ParentJUnit;
import kz.scan.selina.enums.VulnerabilitySeverity;
import kz.scan.selina.service.AttackService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;

/**
 * Реализация тестов на внедрение SQL инъекций на веб-сайте
 */
public class ScanFor_SQLInjectionTest extends ParentJUnit implements InjectionBase<String> {

  @ParameterizedTest
  @MethodSource("prepareDataSource")
  @Feature("SQL Инъекции")
  @Severity(SeverityLevel.CRITICAL)  public void scan(String dataSource) {

  }

  private static final AttackService asp = new AttackService();

  static Stream<Arguments> prepareDataSource() {
    return asp.selectAll()
      .stream()
      .filter(x -> x.attackName.contains("SQL"))
      .filter(x -> x.severityType == VulnerabilitySeverity.HIGH)
      .map(x -> Arguments.of(x.attackScript));
  }

  @Override
  public void checkForInjection(ElementsCollection inputForms, String dataSource) {

  }


}
