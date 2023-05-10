package kz.scan.selina.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Arrays;


@Component
public class PythonMLExecutorService implements MLExecutorService {

  private static String executablePythonFile = "/Users/tansh/Desktop/selina/src/main/java/kz/scan/selina/model_ml/predictor.py ";
  private static final String scriptExecutor = "python ";
  private static String inputArguments = "";

  static Logger logger = LogManager.getLogger(PythonMLExecutorService.class);

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

      String result = getStdOutput(p.getInputStream());
      String errors = getStdOutput(p.getErrorStream());

      if (Boolean.parseBoolean(result)) {
        return true;
      }

      if (!errors.isEmpty()) {
        logger.error(errors);
      }

    } catch (IOException e) {
      logger.error(Arrays.toString(e.getStackTrace()));
    }

    return false;
  }

  /**
   * Print the output of the command
   */
  private static String getStdOutput(InputStream stdin) throws IOException {
    StringBuilder sb = new StringBuilder();

    try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(stdin))) {

      String s;
      while ((s = stdInput.readLine()) != null) {
        sb.append(s);
        sb.append("\n");
      }

    } catch (IOException e) {
      logger.error("\n::: Exception I1NEKKYW:\n", e);
    }

    return sb.toString().trim();  // remove last "\n"
  }

}

