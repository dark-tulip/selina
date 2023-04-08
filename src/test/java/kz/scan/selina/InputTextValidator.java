package kz.scan.selina;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import kz.scan.selina.configs.ParentJUnit;
import kz.scan.selina.exceptions.SqlInjection;
import kz.scan.selina.service.AttackService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.NoAlertPresentException;

import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static kz.scan.selina.models.BasePageLocators.FILE_INPUT;
import static kz.scan.selina.models.BasePageLocators.getAllInputs;


public class InputTextValidator extends ParentJUnit {

  private static final AttackService asp = new AttackService();

  @ParameterizedTest
  @MethodSource("argumentScript")
  @Feature("SQL Injection")
  @Severity(SeverityLevel.CRITICAL)
  public void sqlInjection(String sql) {

    // Получить список всех инпутов на сайте
    for (var inputType : getAllInputs()) {
      ElementsCollection textInputs = $$(inputType);

      // Для каждого инпута внедрить список всех зависимых скриптов
      checkForInjection(textInputs, sql);
    }
  }

  private static boolean isAlertPresent() {
    try {
      Selenide.switchTo().alert();
      return true;
    } catch (NoAlertPresentException Ex) {
      return false;
    }
  }

  private static Stream<Arguments> argumentScript() {
    Stream<Arguments> scripts = asp.selectAll()
      .stream()
      .map(x -> Arguments.of(x.attackScript));

    System.out.println(scripts);
    return scripts;
  }

  private void checkForInjection(ElementsCollection inputs, String sql) {
    for (SelenideElement input : inputs) {
      input.setValue(sql);

      if (isAlertPresent()) {
        throw new SqlInjection("Сработала инъекция: " + sql);
      }
    }
  }

}

