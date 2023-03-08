package kz.scan.selina.config;

import lombok.SneakyThrows;
import java.sql.Connection;
import java.sql.DriverManager;


public class DbConfig {

  public static String CONNECTION_URL = "jdbc:postgresql://127.0.0.1:5432/selina";

  public static String USER = "postgres";

  public static String PASSWD = "postgres";

  @SneakyThrows
  public static Connection openConnection() {
    return DriverManager.getConnection(CONNECTION_URL, USER, PASSWD);
  }

}
