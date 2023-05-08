package kz.scan.selina.service;

import java.nio.file.Path;
import java.util.List;

public interface MLExecutorService {

  void setFilePath(Path absolutePath);

  void prepareInput(String inputData);

  boolean predict();

}
