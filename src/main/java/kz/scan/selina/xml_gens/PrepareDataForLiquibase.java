package kz.scan.selina.xml_gens;

import kz.scan.selina.utils.RndGenerator;

import java.io.*;

public class PrepareDataForLiquibase {

  public static String getHeader() {
    String author      = System.getProperty("user.name");
    String changeSetId = RndGenerator.rndStr(5);

    return String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
      "<databaseChangeLog\n" +
      "        xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog/1.9\"\n" +
      "        xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
      "        xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog/1.9\n" +
      "                      http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-1.9.xsd\">\n" +
      "\n" +
      "    <changeSet author=\"%s\" id=\"%s\">\n", author, changeSetId);
  }

  public static String getFooter() {
    return "\n    </changeSet>\n" +
      "\n" +
      "</databaseChangeLog>";
  }

  static void appendForFile(FileWriter fw, File rawPayload, String attackId) {
    try (BufferedReader reader = new BufferedReader(new FileReader(rawPayload))) {
      String sql = reader.readLine();
      while (sql != null) {
        if (!sql.isBlank()) {
          fw.append(formatIntoSqlTags(sql, attackId));
        }
        sql = reader.readLine();
      }
    } catch (IOException ex) {
      System.out.println("ERROR :: " + ex.getMessage());
    }
  }

  private static String formatIntoSqlTags(String sql, String attackId) {
    String severityId = Long.toString(RndGenerator.rndInt(1, 3));
    String wrappedSql = PrepareDataForLiquibase.wrapEscapeCharacters(sql);

    sql = String.format("\n        <sql>\n" +
      "            <![CDATA[\n" +
      "            INSERT INTO attack_script(attack_id, severity_id, attack_script)\n" +
      "            VALUES (%s, %s, '%s');\n" +
      "            ]]>\n" +
      "        </sql>", attackId, severityId, wrappedSql);

    return sql;
  }


  private static String wrapEscapeCharacters(String sql) {
    return sql
      .replaceAll(";", "\\\\;")
      .replaceAll("\"", "\\\\\"")
      .replaceAll("\'", "\'\'");
  }

}
