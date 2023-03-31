package kz.scan.selina.models;

import java.net.URL;
import java.util.List;
import java.util.Random;

public class BasePageLocators {
  public static final String FILE_INPUT = "*[type='file']";
  public static final String SUBMIT_BUTTON = "*[type='submit']";
  public static final String PASSWORD_INPUT = "*[type='password']";
  public static final String CHECKBOX_INPUT = "[type='checkbox']";
  public static final String EMAIL_INPUT = "[type='email']";
  public static final String HIDDEN_INPUT = "[type='hidden']";
  public static final String IMAGE_INPUT = "[type='image']";
  public static final String MONTH_INPUT = "[type='month']";
  public static final String NUMBER_INPUT = "[type='number']";
  public static final String RADIO_INPUT = "[type='radio']";
  public static final String RANGE_INPUT = "[type='range']";
  public static final String RESET_BUTTON = "[type='reset']";
  public static final String SEARCH_INPUT = "[type='search']";
  public static final String TEL_INPUT = "[type='tel']";
  public static final String TEXT_INPUT = "[type='text']";
  public static final String TIME_INPUT = "[type='time']";
  public static final String URL_INPUT = "[type='url']";

  public static List<String> getAllInputs() {
    return List.of(FILE_INPUT, SUBMIT_BUTTON, PASSWORD_INPUT,
      CHECKBOX_INPUT, EMAIL_INPUT, HIDDEN_INPUT, IMAGE_INPUT,
      MONTH_INPUT, NUMBER_INPUT, RADIO_INPUT, RANGE_INPUT, RESET_BUTTON,
      SEARCH_INPUT, TEL_INPUT, TEXT_INPUT, TIME_INPUT, URL_INPUT);
  }

}
