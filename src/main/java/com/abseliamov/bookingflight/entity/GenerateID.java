package com.abseliamov.bookingflight.entity;

import java.util.UUID;

public abstract class GenerateID {
    private long id;

    protected GenerateID() {
        id = generateId();
    }

    private static long generateId() {
        long id = UUID.randomUUID().getMostSignificantBits();
        if (id < 0) {
            id = generateId();
        }
        return id;
    }
}
