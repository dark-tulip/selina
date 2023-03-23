package kz.scan.selina;

import kz.scan.selina.configs.ParentJUnit;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest extends ParentJUnit {

  @Test
  public void search() {
    $("[data-test='search-input']").sendKeys("Selenium");
    $("button[data-test='full-search-button']").click();
    $("input[data-test='search-input']").shouldHave(attribute("value", "Selenium"));
  }

}
