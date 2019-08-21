package com.abseliamov.bookingflight.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class IOUtil {
    public static File checkFileExists(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
                return file;
            } catch (IOException e) {
                System.out.println("Exception create file \'" + fileName + "\' " + e);
            }
        }
        return file;
    }

    public static String getFile(String fileName) {
        Properties property = new Properties();
        try {
            property.load(new FileReader("src/main/resources/properties/file.properties"));
        } catch (IOException e) {
            System.out.println("Error reading file properties " + e);
        }
        String resourceFile = property.getProperty(fileName);
        File file = IOUtil.checkFileExists(resourceFile);
        return resourceFile;
    }
}
