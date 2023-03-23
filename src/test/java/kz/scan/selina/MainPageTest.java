package kz.scan.selina;

import kz.scan.selina.configs.ParentJUnit;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest extends ParentJUnit {
//  BasePageLocators basePage = new BasePageLocators();

  @Test
  public void search() {
    $("[data-test='search-input']").sendKeys("Selenium");
    $("button[data-test='full-search-button']").click();

    $("input[data-test='search-input']").shouldHave(attribute("value", "Selenium"));
  }

//  @Test
//  public void toolsMenu() {
//    basePage.toolsMenu.click();
//
//    $("div[data-test='main-submenu']").shouldBe(visible);
//  }
//
//  @Test
//  public void navigationToAllTools() {
//    basePage.seeAllToolsButton.click();
//
//    $("#products-page").shouldBe(visible);
//
//    assertEquals("All Developer Tools and Products by JetBrains", Selenide.title());
//  }
}
