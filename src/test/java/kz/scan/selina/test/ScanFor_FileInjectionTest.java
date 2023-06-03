package kz.scan.selina.test;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import kz.scan.selina.configs.ParentJUnit;
import kz.scan.selina.exceptions.VulnerableFileUploadedException;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.net.URL;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$$;
import static kz.scan.selina.models.BasePageLocators.FILE_INPUT;
import static kz.scan.selina.models.BasePageLocators.SUBMIT_BUTTON;


/**
 * Класс для проверки на внедрение файлов на сайте
 */
@CommonsLog
public class ScanFor_FileInjectionTest extends ParentJUnit implements InjectionBase<File> {


  /**
   * Метод для проверки и внедрения исполняемого файла в HTML
   *
   * @param executableFile объект файла в качестве аргумента
   */
  @ParameterizedTest
  @MethodSource("prepareDataSource")
  @Severity(SeverityLevel.BLOCKER)
  @Feature("Файловая инъекция на сайте")
  public void scan(File executableFile) {

    log.info("CADSE3Z1 :: Injection filename: " + executableFile.getAbsolutePath());

    checkForInjection($$(FILE_INPUT), executableFile);

  }

  @Override
  public void checkForInjection(ElementsCollection inputForms, File file) {
    for (var fileInput : inputForms) {

      if (fileInput.is(enabled)) {
        fileInput.uploadFile(file);

        if ($$(SUBMIT_BUTTON).findBy(enabled).exists()) {
          $$(SUBMIT_BUTTON).findBy(enabled).click();
          throw new VulnerableFileUploadedException("Vulnerable file uploaded to server: " + file.getName() + "to inputForm: " + fileInput.getAccessibleName());
        }

      }
    }
  }

  static Stream<Arguments> prepareDataSource() {
    return Stream.of(
      Arguments.of(getScriptResource("file-injection.php")),
      Arguments.of(getScriptResource("file-injection.bash"))
    );
  }

  /**
   * Получить исполняемые файлы из поддиректории ресурсов
   *
   * @return полный путь с файлу
   */
  public static File getScriptResource(String executableFileName) {

    String resourceLocation = getExecutableFilesDirectory() + executableFileName;
    URL fileUrl = ScanFor_FileInjectionTest.class.getResource(resourceLocation);

    if (fileUrl != null) {
      return new File(fileUrl.getFile());
    }

    log.error(":: File not found: " + resourceLocation);
    return null;
  }

  public static void main(String[] args) {
    System.out.println(log.getClass());
    System.out.println(getScriptResource("file-injection.php"));
  }

  /**
   * Относительное расположение файла с ресурсами
   */
  public static String getExecutableFilesDirectory() {
    return "/scripts/executable_files/";
  }

}
