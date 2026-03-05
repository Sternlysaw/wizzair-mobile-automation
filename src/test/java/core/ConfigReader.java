package core;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in src/test/resources");
            }
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    /**
     * Reads a config value.
     * System properties (-Dkey=value) override config.properties.
     */
    public static String get(String key) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank()) {
            return sys.trim();
        }

        String value = props.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new RuntimeException("Missing property: " + key + " (check config.properties or -D" + key + "=...)");
        }
        return value.trim();
    }

    /**
     * Reads a config value but returns null if missing.
     * System properties (-Dkey=value) override config.properties.
     */
    public static String getOptional(String key) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank()) {
            return sys.trim();
        }

        String value = props.getProperty(key);
        return (value == null || value.isBlank()) ? null : value.trim();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String v = getOptional(key);
        if (v == null) return defaultValue;
        return Boolean.parseBoolean(v);
    }
}