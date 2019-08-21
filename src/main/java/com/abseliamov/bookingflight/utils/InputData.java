package com.abseliamov.bookingflight.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public enum InputData {
    STRING(input -> true, ""),
    INTEGER(input -> StringUtils.isNumeric(input), "Please enter a number"),
    DATE(input -> {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        try {
            dateFormat.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }, "Please enter date in format dd.MM.yyyy");

    private Predicate<String> value;
    private String errorMessage;

    InputData(Predicate<String> data, String errorMessage) {
        this.value = data;
        this.errorMessage = errorMessage;
    }

    public Predicate<String> getValue() {
        return value;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
