package utils;

import java.io.*;
import java.util.Properties;

public class PropertyUtils {
    private static PropertyUtils propertyUtils;
    Properties properties;

    public static synchronized PropertyUtils getInstance() {
        if (propertyUtils == null) {
            propertyUtils = new PropertyUtils();
        }
        return propertyUtils;
    }

    private PropertyUtils() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try {
            properties.load(getClass().getClassLoader().getResource("application.properties").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
