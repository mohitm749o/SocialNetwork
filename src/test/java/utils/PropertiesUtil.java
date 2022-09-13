package utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to load properties from property file
 */
public class PropertiesUtil {
    public static PropertiesUtil instance;
    private Properties properties;

    private PropertiesUtil() {
    }

    public static PropertiesUtil getInstance() {
        if (instance == null) {
            return new PropertiesUtil();
        }
        return instance;
    }


    public Properties getProperties(String propertyFilePath) {
       /* String propFile = String.format("application-%s.properties", System.getProperty(Constants.SYS_FEATURE_ENV).toLowerCase());
        URL appPropResource = BaseApiHandler.class.getClassLoader().getResource(propFile);
        if (appPropResource == null) {
            throw new IllegalStateException("File: " + propFile + " not found");
        }
        try (final InputStream resourceStream = appPropResource.openStream()) {
            this.properties.load(resourceStream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return null;*/
        try {
            FileInputStream fileInputStream = new FileInputStream(propertyFilePath);
            properties = new Properties();
            properties.load(fileInputStream);
            return properties;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
