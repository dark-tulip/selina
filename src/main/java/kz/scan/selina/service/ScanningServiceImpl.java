package kz.scan.selina.service;


import kz.scan.selina.model.CommandExecutionMetadata;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@CommonsLog
public class ScanningServiceImpl implements ScanningService {

  @Autowired
  CommandExecutorService commandExecutorService;


  @Override
  public int executeTests() {
    String command1 = "./gradlew";
    String command2 = "test";

    CommandExecutionMetadata commandResults =
      commandExecutorService.execute("bash", "-c", command1, command2);

    return commandResults.getStatusCode();

  }

  @Override
  public String buildReport() {
    String javaBuilder = "./gradlew";
    String allureCommand = "allureServe";
    String portOption = "--port";
    String portNum = "62000";

    CommandExecutionMetadata commandResults =
      commandExecutorService.execute(javaBuilder, allureCommand, portOption, portNum);

    if (commandResults.getOutput().contains("Caused by: java.net.BindException: Address already in use")) {
      var PORT_KILLER = "bash -c 'sudo kill -9 `sudo lsof -t -i:62000` > /dev/null'";

      log.info("QBBLIVXD :: Kill the application running on port 62000");
      log.info("QBBLIVXD :: Try to execute the following command:\n" + PORT_KILLER);

    }

    return commandResults.getOutput();
  }



  public static void main(String[] args) {

    ScanningServiceImpl scanningService = new ScanningServiceImpl();

    scanningService.commandExecutorService = new CommandExecutorServiceImpl();

    scanningService.executeTests();

    scanningService.buildReport();

  }

}
