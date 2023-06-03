package kz.scan.selina.xml_gens;

import org.assertj.core.util.Files;

import java.io.*;
import java.nio.file.Path;
import java.util.Objects;

import static kz.scan.selina.xml_gens.PrepareDataForLiquibase.*;

public class PrepareForXssScript {

  public static void main(String[] args) throws IOException {
    File xssRawPayload       = new File( "src/main/resources/scripts/xss.txt");
    File xssFormattedPayload = new File("src/main/resources/liquibase/changelog/004_insert_scripts_xss.xml");

    try(FileWriter fw = new FileWriter(xssFormattedPayload)) {

      // start
      fw.append(getHeader());

      // append all
      appendForFile(fw, xssRawPayload, "2");

      // end
      fw.append(getFooter());
    }

  }

}
