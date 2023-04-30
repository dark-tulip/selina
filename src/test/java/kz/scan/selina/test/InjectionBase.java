package kz.scan.selina.test;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public interface InjectionBase<T> {

  void scan(T dataSource);

  void checkForInjection(ElementsCollection inputForms, T dataSource);

}
