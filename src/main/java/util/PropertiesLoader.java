package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private final static Properties PROPERTIES = new Properties();
    private static void loadProperties() {
        try (InputStream stream = PropertiesLoader.class.getClassLoader().getResourceAsStream("application.properties")) {
             PROPERTIES.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
    static {
        loadProperties();
    }
}
