package com.abseliamov.bookingflight.dao;

import java.io.*;
import java.util.List;

public abstract class GeneralDao<T> {
    private File file;
    private List<T> fileData;

    private void readFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            fileData = (List<T>) inputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
