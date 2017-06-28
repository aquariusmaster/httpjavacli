package com.anderb.onseo.httpcli;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by andreb on 27.06.17.
 */
public class PropertiesReader {

    private static final String APP_FILE = "application.properties";
    private static Properties properties;

    public static Properties getProperties(){
        if (properties == null){
            properties = new Properties();
            try (final InputStream stream =
                         PropertiesReader.class.getClassLoader().getResourceAsStream(APP_FILE)){

                // load a properties file
                properties.load(stream);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return properties;
    }
}