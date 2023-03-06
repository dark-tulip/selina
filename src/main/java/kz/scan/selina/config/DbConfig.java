package kz.scan.selina.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

public class DbConfig {

  public static String CONNECTION_URL = "jdbc:postgresql://127.0.0.1:5432/selina";

  public static String USER = "postgres";

  public static String PASSWD = "postgres";

  @SneakyThrows
  public static Connection openConnection() {
    return DriverManager.getConnection(CONNECTION_URL, USER, PASSWD);
  }

}
