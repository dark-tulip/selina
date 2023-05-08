package kz.scan.selina.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;

@Service
public class PythonMLExecutorService implements MLExecutorService {

  private static String executablePythonFileScript = "/Users/tansh/Desktop/selina/src/test/java/kz/scan/selina/test/hello.py";

  @Override
  public void setFilePath(Path absolutePath) {
    executablePythonFileScript = absolutePath.toAbsolutePath().toString();
  }

  @Override
  public void prepareInput(List<String> inputData) {

  }

  @Override
  public boolean predict() {
    try {
      // run the Unix "ps -ef" command, using the Runtime exec method:
      Process p = Runtime.getRuntime().exec("python " + executablePythonFileScript);

      BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
      BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

      printStdInput(stdInput);
      printStdError(stdError);
      System.exit(0);
      return true;

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Print the output of the command
   *
   * @param stdin
   * @throws IOException
   */
  private static void printStdInput(BufferedReader stdin) throws IOException {
    System.out.println("Here is the standard output of the command:\n");
    String s;

    while ((s = stdin.readLine()) != null) {
      System.out.println(s);
    }
  }

  /**
   * Print any errors from the command
   *
   * @param stdin
   * @throws IOException
   */
  private static void printStdError(BufferedReader stdin) throws IOException {
    System.out.println("Here is the standard error of the command (if any):\n");
    String s;
    while ((s = stdin.readLine()) != null) {
      System.out.println(s);
    }
  }
}

