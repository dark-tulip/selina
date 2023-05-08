package kz.scan.selina.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import kz.scan.selina.configs.ParentJUnit;
import kz.scan.selina.enums.VulnerabilitySeverity;
import kz.scan.selina.exceptions.VulnerableScriptException_SqlInjection;
import kz.scan.selina.service.PythonMLExecutorService;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$$;


/**
 * Реализация тестов на внедрение SQL инъекций на веб-сайте
 */
@SpringBootTest
public class ScanFor_SQLInjectionTest extends ParentJUnit implements InjectionBase<String> {

  @Autowired
  PythonMLExecutorService pythonMLExecutorService;

  @ParameterizedTest
  @MethodSource("prepareDataSource")
  @Feature("SQL Инъекции")
  @Severity(SeverityLevel.CRITICAL)
  @Override
  public void scan(String dataSource) {
    ElementsCollection urlsWithRequestParam = $$("a")
      .filter(Condition.attributeMatching("href", ".*\\?.*\\=.*"));

    urlsWithRequestParam
      .asDynamicIterable()
      .forEach(x -> System.out.println("FA9H6B25 :: Defined URL: " + x.getAttribute("href")));

    checkForInjection(urlsWithRequestParam, dataSource);
  }

  @Override
  public void checkForInjection(ElementsCollection urls, String dataSource) {

    // пройтись по уникальным ссылкам на сайте и добавить RequestParams
    for (var rawUrl : getUniqUrls(urls)) {

      // open http connection
      sendHttpRequest(dataSource, rawUrl);

    }
  }

  static Stream<Arguments> prepareDataSource() {
    return InjectionBase.getAttackService()
      .selectAll().stream()
      .filter(x -> x.attackName.contains("SQL"))
      .filter(x -> x.severityType == VulnerabilitySeverity.HIGH)
      .map(x -> Arguments.of(x.attackScript))
      .limit(1);  // todo remove after complete
  }

  /**
   * Отправить GET запрос и проанализирвать содержимое
   * @param dataSource
   * @param rawUrl запрос без параметров
   */
  private void sendHttpRequest(String dataSource, String rawUrl) {
    try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

      // construct http query
      HttpGet httpGet = new HttpGet(rawUrl);
      URI uri = new URIBuilder(httpGet.getURI())
        .addParameters(constructNvps(getQueryParams(rawUrl), dataSource))
        .build();

      httpGet.setURI(uri);

      // get response from website
      CloseableHttpResponse response = client.execute(httpGet);

      // Read the contents of an entity and return it as a String.
      String content = EntityUtils.toString(response.getEntity());

      boolean predictResult = analyzeContent(content);

      if (predictResult) {
        throw new VulnerableScriptException_SqlInjection(dataSource);
      }

    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
  }

  /**
   * Main method to analye content
   * @param content
   */
  private boolean analyzeContent(String content) {
    pythonMLExecutorService.prepareInput(content);
    return pythonMLExecutorService.predict();
  }


  /**
   * получить параметры запроса
   * @param url - необработанная строка с параметрами запроса и их значениями
   * @return - возращает названия атрибутов отправляемых на сервер
   */
  public static Set<String> getQueryParams(String url) {
    int queryParamsDelimetrIndex = url.indexOf("?");

    if (queryParamsDelimetrIndex == -1) {
      return null;
    }

    String[] paramsAndValues = url.substring(queryParamsDelimetrIndex + 1).split("&");
    Set<String> params = new HashSet<>();

    for (String param : paramsAndValues) {
      param = param.split("=")[0];
      if (!params.contains(param)) {
        params.add(param);
      }
    }
    return params;
  }

  /**
   * Получить список уникальных урлов на сайте
   * @param urls необработанные ссылки с сайта
   * @return только уникальные ссылки
   */
  private static Set<String> getUniqUrls(ElementsCollection urls) {
    return urls
      .asFixedIterable().stream()
      .map(url -> removeLastRequestParamValue(Objects.requireNonNull(url.getAttribute("href"))))
      .collect(Collectors.toSet());  // Нужно превратить в множество чтобы не было запросов дубликатов
  }


  private static List<NameValuePair> constructNvps(Set<String> params, String dataSource) {
    return params.stream()
      .map(x -> new BasicNameValuePair(x, dataSource))
      .collect(Collectors.toList());
  }


  /**
   * Удаляет значение у последнего переданного параметра
   *
   * @param url - строка запроса, с параметром и его значением
   * @return - строка подзапроса с параметром и удаленным значением<p>
   * f.e <p> Before:
   * https://someurl.kz/index.php?lang=someValue
   * <p> After:
   * https://someurl.kz/index.php?lang=
   * <p>
   */
  private static String removeLastRequestParamValue(String url) {
    return url.substring(0, url.lastIndexOf("=") + 1);
  }

}
