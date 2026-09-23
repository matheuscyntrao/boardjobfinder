package migration;

import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.AllArgsConstructor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

@AllArgsConstructor
public class MigrationStrategy {

    private final Connection connection;

    public void executeMigration() {
        var originalOut = System.out;
        var originalErr = System.err;

        try (var fos = new FileOutputStream("liquibase.log", true);
             var ps = new PrintStream(fos, true)) {
            System.setOut(ps);
            System.setErr(ps);
            runLiquibase();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao configurar o log da migração", e);
        } finally {
            System.setOut(originalOut);
            System.setErr(originalErr);
        }
    }

    private void runLiquibase() {
        try {
            var database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(connection));

            // Passamos o ClassLoader explícito da classe atual
            var resourceAccessor = new ClassLoaderResourceAccessor(MigrationStrategy.class.getClassLoader());

            var liquibase = new Liquibase(
                    "db/changelog/db.changelog-master.yml",
                    resourceAccessor,
                    database
            );

            liquibase.update("");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao executar as migrações do Liquibase", e);
        }
    }
}