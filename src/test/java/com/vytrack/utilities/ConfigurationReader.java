package com.vytrack.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties Properties = new Properties();

        static {
            String path = "configuration.properties";
            try {
                FileInputStream file = new FileInputStream(path);
                Properties.load(file);
                file.close();
            } catch (IOException e) {
                System.out.println("Properties class not found");
            }
        }

    public static String getProperty(String Key) {
            return Properties.getProperty(Key);
    }


}
