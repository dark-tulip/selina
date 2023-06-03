package kz.scan.selina.test;

import com.codeborne.selenide.ElementsCollection;
import kz.scan.selina.exceptions.VulnerableScriptException_SqlInjection;
import kz.scan.selina.service.AttackService;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public interface InjectionBase<T> {

  /**
   * Запустить исполнение теста
   * @param dataSource - ресурс данных от параметризированных тестов
   */
  void scan(T dataSource) throws VulnerableScriptException_SqlInjection;

  /**
   * Проверить факт уязвимости
   * @param inputForms - форма ввода данных на странице
   * @param dataSource - ресурс внедряемых данных
   * @throws VulnerableScriptException_SqlInjection - кидать исключение при нахождении уязвимости
   */
  void checkForInjection(ElementsCollection inputForms, T dataSource) throws VulnerableScriptException_SqlInjection;

  /**
   * Сервис для доступа к данным о скриптах из БД
   */
  static AttackService getAttackService() {
    return new AttackService();
  }

}
