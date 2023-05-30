package kz.scan.selina.service;

import kz.scan.selina.model.CommandExecutionMetadata;
import lombok.SneakyThrows;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@CommonsLog
@Component
class CommandExecutorServiceImpl implements CommandExecutorService {

  private static String PROJECT_DIR = System.getProperty("user.dir");

  public static void setProjectDir(String projectDir) {
    PROJECT_DIR = projectDir;
  }

  public CommandExecutionMetadata execute(String cmd) {

    ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.command("bash", "-c", cmd);
    processBuilder.directory(new File(PROJECT_DIR));

    StringBuilder output = new StringBuilder();
    int exitCode = 1;

    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line).append("\n");
        log.info(line);
      }

      exitCode = process.waitFor();

      log.warn("Exited with error code : " + exitCode);


    } catch (IOException | InterruptedException e) {
      output.append(e.getMessage());
      log.error(e.getLocalizedMessage());

    }

    return CommandExecutionMetadata.of(exitCode, output.toString());
  }


  public static void main(String[] args) {
    CommandExecutorServiceImpl commandExecutorService = new CommandExecutorServiceImpl();
    System.out.println(commandExecutorService.execute("./"));
  }

}
