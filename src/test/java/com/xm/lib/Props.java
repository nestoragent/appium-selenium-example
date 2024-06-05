package com.xm.lib;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by velichko-aa on 02.03.2016.
 */
public class Props {

    private static Props instance;
    private static Properties props;

    public synchronized static Props getInstance() {
        if (instance == null) {
            instance = new Props();
        }
        return instance;
    }

    /**
     * load properties
     */
    public Props() {
        try {
            String configFile = System.getProperty("configFile", "config/ios.properties");
            System.out.println("Loading properties from: " + configFile);
            //first try
            InputStream in = Props.class.getClassLoader().getResourceAsStream(configFile);
            //if failed, second try
            if (in == null) {
                in = new FileInputStream(configFile);
            }
            props = new Properties();
            props.load(in);
        } catch (IOException e) {
            System.err.println("Failed to initialize props. Error Message = " + e.getMessage());
        }
    }

    public String getProp(String name) {
        String val = getProps().getProperty(name, "");
        if (val.isEmpty())
            System.err.printf("Property %s was not found on Props", val);
        return val.trim();
    }

    public static String get(String prop) {
        return Props.getInstance().getProp(prop);
    }

    public static long getLong(String prop) {
        return Long.parseLong(Props.getInstance().getProp(prop));
    }

    public static String get(String prop, String defaultValue) {
        String val = getProps().getProperty(prop);
        if (val.isEmpty())
            return defaultValue;
        return val.trim();
    }

    public static Properties getProps() {
        return props;
    }
}
