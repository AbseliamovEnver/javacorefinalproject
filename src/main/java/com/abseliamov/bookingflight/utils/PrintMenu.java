package com.abseliamov.bookingflight.utils;

import java.util.List;

public class PrintMenu {
    public static void printMenu(List<String> menu) {
        for (int i = 0; i < menu.size(); i++) {
            if (i == menu.size() - 1) {
                System.out.println(0 + ". " + menu.get(i));
                continue;
            }
            System.out.println(i + 1 + ". " + menu.get(i));
        }
    }
}
