package kz.scan.selina.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RndGenerator {

  public static long rndInt() {
    return new Random().nextInt(1_000_000);
  }

  /**
   *
   * @param from от какого числа включительно [from; to]
   * @param to - до какого числа включительно
   * @return рандомное число [from; to]
   */
  public static long rndInt(int from, int to) {
    return new Random().nextInt(to) + from;
  }

  public static String rndStr() {
    char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 12; i++) {
      sb.append(chars[new Random().nextInt(chars.length)]);
    }

    return sb.toString();
  }

  public static String rndStr(int length) {
    char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      sb.append(chars[new Random().nextInt(chars.length)]);
    }

    return sb.toString();
  }


  public static Date rndDate() {
    return new Date(ThreadLocalRandom.current().nextInt());
  }

  public static <T extends Enum<T>> T rndEnum(Class<T> e) {
    T[] values = e.getEnumConstants();
    return values[new Random().nextInt(values.length)];
  }

}
