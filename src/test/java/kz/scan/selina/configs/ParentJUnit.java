package kz.scan.selina.configs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.open;
import static kz.scan.selina.configs.Environment.SCAN_URL;

@ExtendWith({ScreenShooterExtension.class})
public class ParentJUnit {

  @BeforeAll
  public static void setUpAll() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");

    Configuration.browserCapabilities = options;
    Configuration.browserSize = "1280x800";
    Configuration.headless = true;
    Configuration.webdriverLogsEnabled = false;
    SelenideLogger.addListener("allure", new AllureSelenide());
  }

  @BeforeEach
  public void setUp() {
    open(SCAN_URL);
  }

}
