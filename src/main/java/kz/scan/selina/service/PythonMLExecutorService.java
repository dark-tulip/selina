package kz.scan.selina.service;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Path;


@Component
public class PythonMLExecutorService implements MLExecutorService {

  private static String executablePythonFile = "/Users/tansh/Desktop/selina/src/test/java/kz/scan/selina/test/hello.py ";
  private static final String scriptExecutor = "python ";
  private static String inputArguments = "";


  @Override
  public void setFilePath(Path absolutePath) {
    executablePythonFile = absolutePath.toAbsolutePath().toString();
  }

  @Override
  public void prepareInput(String inputArgs) {
    inputArguments = inputArgs;
  }

  @Override
  public boolean predict() {
    try {
      String command = scriptExecutor + executablePythonFile + inputArguments;
      Process p = Runtime
        .getRuntime()
        .exec(command);

      printStdInput(p.getInputStream());
      printStdError(p.getErrorStream());
      return true;

    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Print the output of the command
   */
  private static void printStdInput(InputStream stdin) throws IOException {
    System.out.println("MWMI189B:: printStdInput");

    try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(stdin))) {
      String s;
      while ((s = stdInput.readLine()) != null) {
        System.out.println(s);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Print any errors from the command
   */
  private static void printStdError(InputStream stdin) throws IOException {
    System.out.println("MSO6F6DO:: printStdError");

    try (BufferedReader stdError = new BufferedReader(new InputStreamReader(stdin))) {
      String s;
      while ((s = stdError.readLine()) != null) {
        System.out.println(s);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}

