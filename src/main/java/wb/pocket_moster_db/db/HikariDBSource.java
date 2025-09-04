package wb.pocket_moster_db.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariDBSource {

    private HikariDBSource() {
    }

    private static final HikariDataSource dataSource;

    static {
        Properties properties = new Properties();

        try {
            properties.load(HikariDataSource.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException();
        }

        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/" + properties.getProperty("db.name"));
        config.setUsername(properties.getProperty("db.username"));
        config.setPassword(properties.getProperty("db.password"));

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
