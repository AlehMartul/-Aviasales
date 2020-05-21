package framework.tools;

import aquality.selenium.browser.AqualityServices;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyTool {
    private String resourcesPath;
    private String name;
    private static Properties properties;

    public ReadPropertyTool(String resourcesPath, String name) {
        this.resourcesPath = resourcesPath;
        this.name = name;
    }

    private static void readProperties(String path, String name) {
        if (properties == null) {
            properties = new Properties();
        }
        try {
            properties.load(new FileReader(new File(path, name)));
        } catch (IOException ex) {
            AqualityServices.getLogger().error(ex.getMessage());
        }
    }

    public static String getData(String data, String path, String name) {
        initializeProperties(path, name);
        return properties.getProperty(data);
    }

    private static void initializeProperties(String path, String name) {
        if (properties == null) {
            readProperties(path, name);
        }
    }
}