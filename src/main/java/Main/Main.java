package Main;

import configuration.ConnectionConfig;
import migration.MigrationStrategy;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try (var connection = ConnectionConfig.getConnection()) {
            System.out.println("A iniciar migração do banco de dados...");
            new MigrationStrategy(connection).executeMigration();
            System.out.println("Migração concluída com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao conectar ao banco de dados:");
            ex.printStackTrace();
        }
    }

}