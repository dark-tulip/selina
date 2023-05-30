package kz.scan.selina.service;


import kz.scan.selina.model.CommandExecutionMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScanningServiceImpl implements ScanningService {

  @Autowired
  CommandExecutorService commandExecutorService;


  @Override
  public int executeTests() {
    String command = "gradlew test";

    CommandExecutionMetadata commandResults = commandExecutorService.execute(command);

    return commandResults.getStatusCode();

  }

  @Override
  public String buildReport() {
    String command = "gradlew allureServe";

    CommandExecutionMetadata commandResults = commandExecutorService.execute(command);

    return commandResults.getOutput();
  }


  public static void main(String[] args) {
    ScanningServiceImpl scanningService = new ScanningServiceImpl();

    scanningService.commandExecutorService = new CommandExecutorServiceImpl();

    scanningService.executeTests();

    scanningService.buildReport();

  }

}
