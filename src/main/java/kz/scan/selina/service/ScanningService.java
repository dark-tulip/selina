package kz.scan.selina.service;

public interface ScanningService {

  /**
   * Выполняет все запущенные тесты для сканинга приложения по урлу
   */
  void executeTests();

  /**
   * Составляет Allure отчет по запущенным тестам
   */
  void buildReport();

  /**
   * Очищает историю тестов и результаты прогона прошлых тестовых выполнений
   */
  void clearWorkspace();

}
