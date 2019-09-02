package com.abseliamov.flyapplication.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;

public enum InputData {
    STRING(input -> true, ""),
    INTEGER(input -> StringUtils.isNumeric(input), "Please enter a number"),
    DATE(input -> {
        int numberDays = 31;
        boolean dateFlag = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar currentDate = Calendar.getInstance();
        Calendar dayBefore = Calendar.getInstance();
        Date date = null;
        try {
            date = dateFormat.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        dayBefore.add(Calendar.DATE, numberDays);

        ZonedDateTime start = ZonedDateTime.now();
        start = start.toLocalDate().atStartOfDay().atZone(start.getZone()).withEarlierOffsetAtOverlap();


        System.out.println(currentDate.getTime());
        System.out.println(date);
        System.out.println(date.after(currentDate.getTime())); //false
        System.out.println(date.before(currentDate.getTime())); //true
        System.out.println(date.equals(currentDate.getTime())); // false
        System.out.println(date.after(currentDate.getTime())); // false
        System.out.println(date.compareTo(currentDate.getTime())); // -1

        if (date.after(currentDate.getTime()) && date.before(dayBefore.getTime())
                /*date.equals(currentDate.getTime())*/) {
            dateFlag = true;
        } else {
            System.out.println("Search is possible from: " + dateFormat.format(currentDate.getTime()) +
                    " to: " + dateFormat.format(dayBefore.getTime()));
        }
        return dateFlag;
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
