package ru.shop.coffee.configuration;

import lombok.AllArgsConstructor;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
@Component
@Profile("!test")
public class FlywayMigrationInitializer implements InitializingBean {

  private final DataSource dataSource;

  @Override
  public void afterPropertiesSet() throws SQLException {
    String schema;
    try (Connection connection = dataSource.getConnection()) {
      schema = connection.getSchema();
    }
    Flyway flyway = Flyway.configure()
            .dataSource(dataSource)
            .schemas(schema)
            .load();
    flyway.migrate();
  }
}
