package kz.scan.selina.service;


import org.springframework.stereotype.Component;

@Component
public class ScanningServiceImpl implements ScanningService {

  private static final String PROJECT_DIR = System.getProperty("user.dir");

  @Override
  public void executeTests() {
    String executeCommand = PROJECT_DIR + "./gradlew test";
  }

  @Override
  public void buildReport() {
    String executeCommand = PROJECT_DIR  + "./gradlew allureServe";

  }

  @Override
  public void clearWorkspace() {
    String executeCommand = "./gradlew allureServe";

  }

  public static void main(String[] args) {
    ScanningService scanningService = new ScanningServiceImpl();

    scanningService.buildReport();

    System.out.println();

  }
}
