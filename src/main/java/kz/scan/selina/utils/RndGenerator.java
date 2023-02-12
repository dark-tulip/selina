package kz.scan.selina.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RndGenerator {

  public static long rndInt() {
    return new Random().nextInt(1_000_000);
  }

  public static String rndStr() {
    byte[] array = new byte[12];  // length is bounded by given number
    new Random().nextBytes(array);
    return new String(array, StandardCharsets.UTF_8);
  }

  public static String rndStr(int length) {
    byte[] array = new byte[length];  // length is bounded by given number
    new Random().nextBytes(array);
    return new String(array, StandardCharsets.UTF_8);
  }

  public static Date rndDate() {
    return new Date(ThreadLocalRandom.current().nextInt());
  }

  public static <T extends Enum<T>> T rndEnum(Class<T> e) {
    T[] values = e.getEnumConstants();
    return values[new Random().nextInt(values.length)];
  }

}
