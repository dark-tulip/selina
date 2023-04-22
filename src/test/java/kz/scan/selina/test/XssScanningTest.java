package kz.scan.selina.test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import kz.scan.selina.configs.ParentJUnit;
import kz.scan.selina.enums.VulnerabilitySeverity;
import kz.scan.selina.exceptions.IframeFoundException;
import kz.scan.selina.exceptions.SqlInjection;
import kz.scan.selina.service.AttackService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$$;
import static kz.scan.selina.models.BasePageLocators.getAllInputs;
import static kz.scan.selina.models.VulnerabilityValidators.isAlertPresent;
import static kz.scan.selina.models.VulnerabilityValidators.isIframePresent;


/**
 * Класс для внедрения тестов связанных с XSS инъекциями на сайте
 */
public class XssScanningTest extends ParentJUnit {

  /**
   * Сервис для доступа к данным о скриптах из БД
   */
  private static final AttackService asp = new AttackService();

  @ParameterizedTest
  @MethodSource("runForXssCritical")
  @Feature("XSS Инъекции")
  @Severity(SeverityLevel.CRITICAL)
  public void sqlInjection(String sql) {

    // Получить список всех инпутов на сайте
    for (var inputType : getAllInputs()) {
      ElementsCollection textInputs = $$(inputType);

      // Для каждого инпута внедрить список всех зависимых скриптов
      checkForInjection(textInputs, sql);

    }
  }


  /**
   * Провайдер данных для наиболее критичных веб уязвимостей
   * @return scripts - возращает XSS текст attackScript для внедрения
   */
  private static Stream<Arguments> runForXssCritical() {
    Stream<Arguments> scripts = asp.selectAll()
      .stream()
      .filter(x -> x.attackName.contains("XSS"))
      .filter(x -> x.severityType == VulnerabilitySeverity.HIGH)
      .map(x -> Arguments.of(x.attackScript));
    return scripts;
  }

  public static void main(String[] args) {
    runForXssCritical();
  }


  /**
   * Триггеры для проверки произошла ли уязвимость
   * @param inputs
   * @param inputText
   */
  private void checkForInjection(ElementsCollection inputs, String inputText) {

    for (SelenideElement input : inputs) {

      if (input.isDisplayed()) {
        input.sendKeys(inputText);

        if (isAlertPresent()) {
          throw new SqlInjection("Сработала инъекция: " + inputText);
        }

        if (isIframePresent()) {
          throw new IframeFoundException(inputText);
        }

      }
      return;
    }
  }

}

