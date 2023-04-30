package kz.scan.selina.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import kz.scan.selina.configs.ParentJUnit;
import kz.scan.selina.enums.VulnerabilitySeverity;
import kz.scan.selina.exceptions.XssDetection;
import kz.scan.selina.service.AttackService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static kz.scan.selina.models.BasePageLocators.SUBMIT_BUTTON;
import static kz.scan.selina.models.BasePageLocators.getAllInputForms;
import static kz.scan.selina.models.FormTypes.SUBMIT;
import static kz.scan.selina.models.VulnerabilityValidators.*;


/**
 * Класс для внедрения тестов связанных с XSS инъекциями на сайте
 */
public class XssScanningTest extends ParentJUnit {

  /**
   * Сервис для доступа к данным о скриптах из БД
   */
  private static final AttackService asp = new AttackService();

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

  @ParameterizedTest
  @MethodSource("runForXssCritical")
  @Feature("XSS Инъекции")
  @Severity(SeverityLevel.CRITICAL)
  public void inject(String xssScript) {

    // Получить список всех инпутов на сайте
    for (var inputType : getAllInputForms()) {
      ElementsCollection textInputs = $$(inputType);

      // Для каждого инпута внедрить список всех зависимых скриптов
      checkForInjection(textInputs, xssScript);

    }
  }


  /**
   * Триггеры для проверки произошла ли уязвимость
   */
  private void checkForInjection(ElementsCollection inputForms, String inputText) {

    for (SelenideElement inputForm : inputForms) {

      if (inputForm.is(exist)) {

        if (isTextInsertable(inputForm)) {
          inputForm.setValue(inputText);
          System.out.println(": Insertable input: " +" with tag:" + inputForm.getTagName() + " and name: " + inputForm.getAccessibleName());

        } else if (isClickableInput(inputForm)) {
          inputForm.click();
          System.out.println("AAA: Clickable input: " + "with tag: " + inputForm.getAriaRole() + " and name: " + inputForm.getAccessibleName());
        }

        if (isAlertPresent()) {
          throw new XssDetection("Сработала XSS инъекция: " + inputText);
        }
      }
    }

    if (inputForms.findBy(type(SUBMIT.label)).exists()) {
      SelenideElement btn = inputForms.findBy(type(SUBMIT.label));

      if (btn.has(enabled)) {
        btn.click();
        System.out.println("Click to btn: with tag name: " + btn.getTagName() + " and name: " + btn.getAccessibleName());

        if ($("html").has(Condition.matchText(inputText))) {
          throw new XssDetection("Найдено уязвимое место для XSS инъекции: " + inputText);

        }
      }
    }

  }

}

