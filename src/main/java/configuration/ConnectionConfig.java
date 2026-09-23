package configuration;

import environment.DotEnv;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConnectionConfig {

    public static Connection getConnection() throws SQLException {
        var connection = DriverManager.getConnection(DotEnv.getValue("DB_URL"), DotEnv.getValue("DB_USERNAME"), DotEnv.getValue("DB_PASSWORD"));
        connection.setAutoCommit(true);
        return connection;
    }

}
