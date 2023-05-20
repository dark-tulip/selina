package kz.scan.selina.service;


public interface MLExecutorService {

  void setUrl(String url);

  boolean predict(String content);

}
