package kz.scan.selina.xml_gens;

import java.io.*;

import static kz.scan.selina.xml_gens.PrepareDataForLiquibase.*;

/**
 * Парсер для SQL кода чтобы отформатировть для формата наката миграций
 */
public class PrepareForSqlInjection {

  public static void main(String[] args) throws IOException {
    File sqlRawPayload       = new File("src/main/resources/scripts/sql.txt");
    File sqlFormattedPayload = new File("src/main/resources/liquibase/changelog/003_insert_scripts_sql.xml");

    try(FileWriter fw = new FileWriter(sqlFormattedPayload)) {

      // start
      fw.append(getHeader());

      // append all
      appendForFile(fw, sqlRawPayload, "1");

      // end
      fw.append(getFooter());
    }

  }

}
