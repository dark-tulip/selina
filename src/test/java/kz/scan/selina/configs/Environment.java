package kz.scan.selina.configs;

import java.nio.file.Path;
import java.nio.file.Paths;

public interface Environment {

  Path EXECUTABLE_FILES_PATH = Paths.get("src","test","resources");

  String SCAN_URL = "https://vip.blokino.org/";

}
