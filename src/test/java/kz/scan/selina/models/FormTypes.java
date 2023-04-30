package kz.scan.selina.models;

import java.util.List;

public enum FormTypes {
  PASSWORD("password"),
  LOGIN("login"),
  TEXT("text"),
  EMAIL("email"),

  BUTTON("button"),
  SUBMIT("submit"),
  RADIO("radio"),
  CHECKBOX("checkbox");

  public final String label;

  private FormTypes(String label) {
    this.label = label;
  }

  public static List<String> getAllInputTypes() {
    return List.of(TEXT.label, EMAIL.label, LOGIN.label, PASSWORD.label);
  }

  public static List<String> getButtonTypes() {
    return List.of(BUTTON.label, SUBMIT.label);
  }

  public static List<String> getAllClickableInputTypes() {
    return List.of(RADIO.label, CHECKBOX.label);
  }

}
