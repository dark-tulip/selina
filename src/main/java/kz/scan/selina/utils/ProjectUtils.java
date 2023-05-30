package kz.scan.selina.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProjectUtils {

  /**
   * @return текущая рабочая директория проекта
   * @default: /Users/tansh/Desktop/selina
   */
  public static String getProjectDir() {
    return System.getProperty("user.dir");
  }


  /**
   * @return получить пользователя запустившего JVM или программу
   * @default: tansh
   */
  public static String getUsername() {
    return System.getProperty("user.name");
  }


  public static void main(String[] args) {
    System.out.println(getProjectDir());
    System.out.println(getUsername());

    System.out.println();
  }
}
