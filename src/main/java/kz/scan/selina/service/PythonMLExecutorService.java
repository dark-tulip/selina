package kz.scan.selina.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;


@Component
@CommonsLog
public class PythonMLExecutorService implements MLExecutorService {

  private static String REQUEST_ORIGIN = "http://0.0.0.0:8080/analyze";

  @Override
  public void setUrl(String url) {
    REQUEST_ORIGIN = url;
  }

  @Override
  public boolean predict(String content) {

    try {
      var objectMapper = new ObjectMapper();
      String requestBody = objectMapper
        .writeValueAsString(content);

      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(REQUEST_ORIGIN))
        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
        .build();

      HttpResponse<String> response = client.send(request,
        HttpResponse.BodyHandlers.ofString());

      content = response.body();

      log.info(request.method() + ", with uri: " + request.uri());
      log.info("response value: " + content);

    } catch (InterruptedException | IOException e) {
      log.error(e.getMessage());
      return false;
    }

    if (Objects.equals(content, "1")) {
      return true;
    }

    return false;
  }


  public static void main(String[] args) {

    System.out.println( new PythonMLExecutorService().predict("1 or 1 = 1;"));

  }

}

