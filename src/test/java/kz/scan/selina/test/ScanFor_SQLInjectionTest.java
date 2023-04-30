package kz.scan.selina.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

/**
 * Реализация тестов на внедрение SQL инъекций на веб-сайте
 */
public class ScanFor_SQLInjectionTest extends ParentJUnit implements InjectionBase<String> {

  @ParameterizedTest
  @MethodSource("prepareDataSource")
  @Feature("SQL Инъекции")
  @Severity(SeverityLevel.CRITICAL)
  @Override
  public void scan(String dataSource) {
    ElementsCollection urlsWithRequestParam = $$("a")
      .filter(Condition.attributeMatching("href", ".*\\?.*\\=.*"));

    urlsWithRequestParam.asDynamicIterable().forEach(x ->
      System.out.println("FA9H6B25 :: Defined URL: " + x.getAttribute("href"))
    );

    checkForInjection(urlsWithRequestParam, dataSource);

  }

  @Override
  public void checkForInjection(ElementsCollection urls, String dataSource) {
    Set<String> rawUrls = urls
      .asFixedIterable()
      .stream().map(url -> removeRequestParamValue(Objects.requireNonNull(url.getAttribute("href"))))
      .collect(Collectors.toSet());  // Нужно превратить в множество чтобы не было запросов дубликатов

    for(var rawUrl: rawUrls) {
      var url = rawUrl + dataSource;
      open(url);
      System.out.println("SHF871JB:: " + url);
    }

  }

  static Stream<Arguments> prepareDataSource() {
    return InjectionBase.getAttackService().selectAll()
      .stream()
      .filter(x -> x.attackName.contains("SQL"))
      .filter(x -> x.severityType == VulnerabilitySeverity.HIGH)
      .map(x -> Arguments.of(x.attackScript))
      .limit(1);  // todo remove after complete
  }

  /**
   * Удаляет значение у переданного параметра
   * @param url - строка запроса, с параметром и его значением
   * @return - строка подзапроса с параметром и удаленным значением<p>
   * f.e <p> Before:
   * https://someurl.kz/index.php?lang=someValue
   * <p> After:
   * https://someurl.kz/index.php?lang=
   * <p>
   */
  private static String removeRequestParamValue(String url) {
    return url.substring(0, url.indexOf("=") + 1);
  }

}
