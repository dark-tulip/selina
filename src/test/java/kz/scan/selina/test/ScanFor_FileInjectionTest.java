package kz.scan.selina.test;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import kz.scan.selina.configs.ParentJUnit;
import kz.scan.selina.exceptions.VulnerableFileUploadedException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$$;
import static kz.scan.selina.models.BasePageLocators.FILE_INPUT;
import static kz.scan.selina.models.BasePageLocators.SUBMIT_BUTTON;


/**
 * Класс для провреки на внедрение файлов на сайте
 */
public class ScanFor_FileInjectionTest extends ParentJUnit implements InjectionBase<File> {


  @Override
  public void checkForInjection(ElementsCollection inputForms, File file) {
    for (var fileInput : inputForms) {
      if (fileInput.is(enabled)) {
        fileInput.uploadFile(file);
      }

      if ($$(SUBMIT_BUTTON).findBy(enabled).exists()) {
        $$(SUBMIT_BUTTON).findBy(enabled).click();
        throw new VulnerableFileUploadedException("Vulnerable file uploaded: " + file.getName() + "to inputForm: " + fileInput.getAccessibleName());
      }
    }
  }

  /**
   * Метод для проверки и внедрения исполняемого файла в HTML
   *
   * @param executableFile объект файла в качестве аргумента
   */
  @ParameterizedTest
  @MethodSource("executableFileDataSet")  // todo realize data provider
  @Severity(SeverityLevel.BLOCKER)
  @Feature("Файловая инъекция на сайте")
  public void scan(File executableFile) {

    ElementsCollection fileInputs = $$(FILE_INPUT).filter(exist);

    if (fileInputs.size() > 0) {
      checkForInjection(fileInputs, executableFile);
    }
  }

}
