package kz.scan.selina.test;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import kz.scan.selina.configs.ParentJUnit;
import kz.scan.selina.enums.VulnerabilitySeverity;
import kz.scan.selina.service.AttackService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;

/**
 * Реализация тестов на внедрение SQL инъекций на веб-сайте
 */
public class SqlInjectionScanning extends ParentJUnit {

  private static final AttackService asp = new AttackService();

  private static Stream<Arguments> sqlDataset() {

    System.out.println(asp.selectAll());

    Stream<Arguments> scripts = asp.selectAll()
      .stream()
      .filter(x -> x.attackName.contains("SQL"))
      .filter(x -> x.severityType == VulnerabilitySeverity.HIGH)
      .map(x -> Arguments.of(x.attackScript));
    return scripts;
  }

  @ParameterizedTest
  @MethodSource("sqlDataset")
  @Feature("SQL Инъекции")
  @Severity(SeverityLevel.CRITICAL)
  public void tt(String sql) {

    System.out.println(sql);

    $("input[type='file']").uploadFile(new File(""));

//    open("https://portal.aues.kz/login/index.php");
//
//    ElementsCollection html = $$("input").filter(visible);
//
//
//    for (var el : html) {
//      if (el.isDisplayed()) {
//        el.shouldBe(enabled).setValue("AAAA").shouldHave(Condition.value("AAAA"));
//      }
//    }
  }

  @Override
  public boolean checkForInjection(Object data) {
    return false;
  }
}
