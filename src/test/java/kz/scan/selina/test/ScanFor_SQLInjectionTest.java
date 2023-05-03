package kz.scan.selina.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import kz.scan.selina.configs.ParentJUnit;
import kz.scan.selina.enums.VulnerabilitySeverity;
//import org.apache.hc.client5.http.classic.methods.HttpGet;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
//import org.apache.hc.core5.http.HttpEntity;
//import org.apache.hc.core5.http.HttpResponse;
//import org.apache.hc.core5.http.ParseException;
//import org.apache.hc.core5.http.io.HttpRequestHandler;
//import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;


/**
 * Реализация тестов на внедрение SQL инъекций на веб-сайте
 */
public class ScanFor_SQLInjectionTest extends ParentJUnit implements InjectionBase<String> {

  @ParameterizedTest
  @MethodSource("prepareDataSource")
  @Feature("SQL Инъекции")
  @Severity(SeverityLevel.CRITICAL)
  @Override
  public void scan(String dataSource) {
    ElementsCollection urlsWithRequestParam = $$("a").filter(Condition.attributeMatching("href", ".*\\?.*\\=.*"));

    urlsWithRequestParam.asDynamicIterable().forEach(x -> System.out.println("FA9H6B25 :: Defined URL: " + x.getAttribute("href")));

    checkForInjection(urlsWithRequestParam, dataSource);

  }


  @Override
  public void checkForInjection(ElementsCollection urls, String dataSource) {
    Set<String> rawUrls = urls.asFixedIterable().stream().map(url -> removeRequestParamValue(Objects.requireNonNull(url.getAttribute("href")))).collect(Collectors.toSet());  // Нужно превратить в множество чтобы не было запросов дубликатов

    for (var rawUrl : rawUrls) {
      var url = rawUrl + dataSource;

      try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
        HttpGet httpGet = new HttpGet("https://portal.aues.kz/login/index.php");
        URI uri = new URIBuilder(httpGet.getURI())
          .addParameter("lang", dataSource)
          .build();

        httpGet.setURI(uri);
        CloseableHttpResponse response = client.execute(httpGet);

        // Read the contents of an entity and return it as a String.
        String content = EntityUtils.toString(response.getEntity());

        if (content.contains("password")) {
          int start = content.indexOf("password");
          int end = start + 100;

          System.out.println("AAAAA: " + content.substring(start, end));
        }

        System.out.println(content);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (URISyntaxException e) {
        throw new RuntimeException(e);
      }
//      open(url);
      System.out.println("SHF871JB:: " + url);
    }

  }


  static Stream<Arguments> prepareDataSource() {
    return InjectionBase.getAttackService().selectAll().stream().filter(x -> x.attackName.contains("SQL")).filter(x -> x.severityType == VulnerabilitySeverity.HIGH).map(x -> Arguments.of(x.attackScript)).limit(1);  // todo remove after complete
  }


  /**
   * Удаляет значение у переданного параметра
   *
   * @param url - строка запроса, с параметром и его значением
   * @return - строка подзапроса с параметром и удаленным значением<p>
   * f.e <p> Before:
   * https://someurl.kz/index.php?lang=someValue
   * <p> After:
   * https://someurl.kz/index.php?lang=
   * <p>
   */
  private static String removeRequestParamValue(String url) {
    return url.substring(0, url.indexOf("=") + 1);
  }

}
