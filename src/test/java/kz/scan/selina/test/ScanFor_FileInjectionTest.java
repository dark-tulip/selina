package kz.scan.selina.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static kz.scan.selina.configs.Environment.EXECUTABLE_FILES_PATH;
import static kz.scan.selina.models.BasePageLocators.FILE_INPUT;


/**
 * Класс для провреки на внедрение файлов на сайте
 */
public class ScanFor_FileInjectionTest implements InjectionBase<File> {


  @BeforeAll
  public static void setUp() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");

    Configuration.browserCapabilities = options;
    Configuration.headless = true;
    Configuration.webdriverLogsEnabled = false;
  }


  @Override
  public void checkForInjection(ElementsCollection inputForms, File file) {

  }

  /**
   * Метод для проверки и внедрения исполняемого файла в HTML
   * @param executableFile объект файла в качестве аргумента
   */
  @ParameterizedTest
  @MethodSource("executableFileDataSet")
  @Severity(SeverityLevel.BLOCKER)
  @Feature("Файловая инъекция на сайте")
  public void scan(File executableFile) {

    SelenideElement fileInput = $(FILE_INPUT);

    if (fileInput.is(Condition.exist)) {

      fileInput.uploadFile(executableFile);

      // todo tansh add exception trigger
    }
  }

}
