package kz.scan.selina.config;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.SneakyThrows;

import static kz.scan.selina.config.DbConfig.openConnection;

public class Migration {

  public static final String MASTER_CHANGELOG = "liquibase/changelog-master.xml";

  @SneakyThrows
  public static void runMigration() {
    java.sql.Connection connection = openConnection();
    Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
    Liquibase liquibase = new liquibase.Liquibase(MASTER_CHANGELOG, new ClassLoaderResourceAccessor(), database);
    liquibase.update(new Contexts(), new LabelExpression());
  }

}
