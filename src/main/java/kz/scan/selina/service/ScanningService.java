package kz.scan.selina.service;

public interface ScanningService {

  /**
   * Выполняет все запущенные тесты для сканинга приложения по урлу
   */
  int executeTests();

  /**
   * Составляет Allure отчет по запущенным тестам
   */
  String buildReport();

  /**
   * Очищает историю тестов и результаты прогона прошлых тестовых выполнений
   */
  default void clearWorkspace() {
    // todo remove build dir
  };

}
