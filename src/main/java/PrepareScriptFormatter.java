import kz.scan.selina.utils.RndGenerator;
import org.postgresql.core.Field;

import java.io.*;

/**
 * Парсер для SQL кода чтобы отформатировть для формата наката миграций
 */
public class PrepareScriptFormatter {

  public static void main(String[] args) {
    File sqlPayload = new File("/Users/tansh/Desktop/selina/src/main/resources/scripts/sql.txt");

    try (BufferedReader reader = new BufferedReader(new FileReader(sqlPayload))) {
      String sql = reader.readLine();
      while (sql != null) {

        if (!sql.isBlank()) {
          System.out.println(formatSqlForLiquibase(sql));
          sql = reader.readLine();
        }

      }
    } catch (IOException ex) {
      System.out.println("ERROR :: " + ex.getMessage());
    }

  }


  private static String formatSqlForLiquibase(String sql) {

    sql = sql
      .replaceAll(";", "\\\\;")
      .replaceAll("\"", "\\\\\"")
      .replaceAll("\'", "\'\'");

    String severityId = Long.toString(RndGenerator.rndInt(1, 3));

    sql = String.format("        <sql>\n" +
      "            <![CDATA[\n" +
      "            INSERT INTO attack_script(attack_id, severity_id, attack_script)\n" +
      "            VALUES (1, %s, '%s');\n" +
      "            ]]>\n" +
      "        </sql>", severityId, sql);

    return sql;
  }
}
