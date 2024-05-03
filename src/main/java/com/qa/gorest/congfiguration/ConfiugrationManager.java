package com.qa.gorest.congfiguration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfiugrationManager {
    private Properties prop;
    private FileInputStream fis;

    public Properties initProp() {
        prop = new Properties();
        try {
            fis = new FileInputStream("K:\\Automation\\RestAssuredFW-2024\\src\\main\\resources\\Config\\Config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prop;
    }

}
