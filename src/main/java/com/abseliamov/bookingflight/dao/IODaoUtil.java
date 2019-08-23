package com.abseliamov.bookingflight.dao;

import com.abseliamov.bookingflight.utils.IOUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class IODaoUtil {
    private BufferedReader reader = null;

    public BufferedReader getReader(String fileName) {
        if (this.reader == null) {
            try {
                reader = new BufferedReader(new FileReader(IOUtil.getFile(fileName)));
            } catch (FileNotFoundException e) {
                System.out.println("Error reading file" + fileName + " " + e);
            }
        }
        return reader;
    }
}
