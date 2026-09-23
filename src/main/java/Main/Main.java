package Main;


import configuration.ConnectionConfig;
import migration.MigrationStrategy;

import java.sql.SQLException;

public class Main {

    static void main() {
        try (var connection = ConnectionConfig.getConnection()) {
            new MigrationStrategy(connection).executeMigration();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
