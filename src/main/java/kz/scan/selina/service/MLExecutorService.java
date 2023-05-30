package kz.scan.selina.service;


/**
 * Интерфейс, взаимодействующий со внешним микросервисом,
 * который работает с моделью
 */
public interface MLExecutorService {

  /**
   * Установить URL сервиса, который взаимодействует с моделью
   */
  void setUrl(String url);

  /**
   * выполнить функцию предсказания
   */
  boolean predict(String content);

}
