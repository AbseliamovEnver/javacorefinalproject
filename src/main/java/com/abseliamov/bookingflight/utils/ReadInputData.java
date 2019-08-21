package com.abseliamov.bookingflight.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadInputData {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String getValidInputData(String message, InputData typeData) {
        System.out.println(message);
        String inputData = null;

        try {
            inputData = getReader().readLine();
            while (!typeData.getValue().test(inputData)) {
                System.out.println(typeData.getErrorMessage());
                inputData = getReader().readLine();
            }
        } catch (IOException e) {
            System.out.println("Error reading input data from console " + e);
        }
        return inputData;
    }

    public static BufferedReader getReader() {
        return reader;
    }
}
