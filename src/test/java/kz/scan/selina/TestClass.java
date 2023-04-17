package kz.scan.selina;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestClass {

  @BeforeAll
  public static void setUp() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");

    Configuration.browserCapabilities = options;
    Configuration.headless = true;
    Configuration.webdriverLogsEnabled = false;

    // java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.WARNING);

  }

  @Test
  public void tt() {
    open("https://portal.aues.kz/login/index.php");

    ElementsCollection html = $$("input").filter(visible);


    for (var el : html) {
      if (el.isDisplayed()) {
        el.shouldBe(enabled).setValue("AAAA").shouldHave(Condition.value("AAAA"));
      }
    }
  }
}
