package kz.scan.selina.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import kz.scan.selina.configs.ParentJUnit;
import kz.scan.selina.enums.VulnerabilitySeverity;
import kz.scan.selina.exceptions.VulnerableScriptException_XssDetection;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static kz.scan.selina.models.BasePageLocators.getAllInputForms;
import static kz.scan.selina.models.FormTypes.SUBMIT;
import static kz.scan.selina.models.VulnerabilityValidators.*;


public class ScanFor_XSSInjectionTest extends ParentJUnit implements InjectionBase<String> {

  @ParameterizedTest
  @MethodSource("prepareDataSource")
  @Feature("XSS Инъекции")
  @Severity(SeverityLevel.CRITICAL)
  public void scan(String xssScript) {

    // Получить список всех инпутов на сайте
    for (var inputForm : getAllInputForms()) {
      ElementsCollection forms = $$(inputForm);

      // Для каждого инпута внедрить список всех зависимых скриптов
      checkForInjection(forms, xssScript);

    }
  }

  /**
   * Провайдер данных для наиболее критичных веб уязвимостей
   * @return scripts - возращает XSS текст attackScript для внедрения
   */
  private static Stream<Arguments> prepareDataSource() {
    return InjectionBase.getAttackService().selectAll()
      .stream()
      .filter(x -> x.attackName.contains("XSS"))
      .filter(x -> x.severityType == VulnerabilitySeverity.HIGH)
      .map(x -> Arguments.of(x.attackScript));
  }

  /**
   * Триггеры для проверки произошла ли уязвимость
   */
  public void checkForInjection(ElementsCollection inputForms, String dataSource) {

    for (SelenideElement inputForm : inputForms) {

      if (inputForm.is(exist)) {

        if (isTextInsertableInputForm(inputForm)) {
          inputForm.setValue(dataSource);
          System.out.println("05REXIDB: Insertable input: " +" with tag:" + inputForm.getTagName() + " and name: " + inputForm.getAccessibleName());

        } else if (isClickableInputForm(inputForm)) {
          inputForm.click();
          System.out.println("XOEGB933: Clickable input: " + "with tag: " + inputForm.getAriaRole() + " and name: " + inputForm.getAccessibleName());
        }

        if (isAlertPresent()) {
          throw new VulnerableScriptException_XssDetection("Сработала XSS инъекция: " + dataSource);
        }
      }
    }

    if (inputForms.findBy(type(SUBMIT.label)).exists()) {
      SelenideElement btn = inputForms.findBy(type(SUBMIT.label));

      if (btn.has(enabled)) {
        btn.click();
        System.out.println("Click to btn: with tag name: " + btn.getTagName() + " and name: " + btn.getAccessibleName());

        if ($("html").has(Condition.matchText(dataSource))) {
          throw new VulnerableScriptException_XssDetection("Найдено уязвимое место для XSS инъекции: " + dataSource);

        }
      }
    }

  }

}

