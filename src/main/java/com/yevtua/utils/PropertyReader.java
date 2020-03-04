package com.yevtua.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static PropertyReader instance;
    private static final Object lock = new Object();
    private static String propertyFilePath = "src/test/resources/config.properties";

    private static String baseUri;
    private static String alreadyRegisteredUsername;
    private static String alreadyRegisteredPassword;

    public static PropertyReader getInstance () {
        if (instance == null) {
            synchronized (lock) {
                instance = new PropertyReader();
                instance.loadData();
            }
        }
        return instance;
    }

    private void loadData() {
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream(propertyFilePath));
        } catch (IOException e) {
            System.out.println("Configuration properties file can't be found");
        }

        baseUri = prop.getProperty("base.uri");
        alreadyRegisteredUsername = prop.getProperty("already.registered.username");
        alreadyRegisteredPassword = prop.getProperty("already.registered.password");
    }

    public String getBaseUri () {
        return baseUri;
    }

    public String getAlreadyRegisteredUsername () {
        return alreadyRegisteredUsername;
    }

    public String getAlreadyRegisteredPassword () {
        return alreadyRegisteredPassword;
    }
}
