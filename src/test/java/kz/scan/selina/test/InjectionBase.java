package kz.scan.selina.test;

import com.codeborne.selenide.ElementsCollection;
import kz.scan.selina.service.AttackService;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public interface InjectionBase<T> {

  void scan(T dataSource);

  void checkForInjection(ElementsCollection inputForms, T dataSource);

  /**
   * Сервис для доступа к данным о скриптах из БД
   */
  static AttackService getAttackService() {
    return new AttackService();
  }

}
