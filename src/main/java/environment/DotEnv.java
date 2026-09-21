package environment;

import io.github.cdimascio.dotenv.Dotenv;

public class DotEnv {

    private static Dotenv dotenv = Dotenv.load();

    public static String getValue(String key) {
        try {
            return dotenv.get(key);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Ocorreu um erro ao recuperar a propriedade " + key);
        }
    }

}
