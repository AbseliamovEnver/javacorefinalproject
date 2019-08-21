package com.abseliamov.bookingflight.view;

import java.util.List;

public class MenuInputOutputService {
    public static <T> void printMenuHeader(List<T> header) {
        if (header != null) {
            header.forEach(System.out::println);
        }
    }

    public static <T> void printMenuItem(List<T> menu) {

        if (menu != null) {
            for (int i = 0; i < menu.size(); i++) {
                if (i == 0) {
                    System.out.println("\t" + menu.get(i));
                    continue;
                }
                if (i == menu.size() - 1) {
                    System.out.println(0 + ". " + menu.get(i)
                            + "\n*********************************");
                } else {
                    System.out.println(i + ". " + menu.get(i));
                }
            }
        }
    }

    public static boolean validateNumberSize(int min, int max) {
        return min >= 0 && min <= max;
    }
}
