package com.abseliamov.bookingflight.entity;

public enum TypeCabin {
    ECONOMY(1),
    BUSINESS(2),
    ECONOMY_AND_BUSINESS(3);

    private final int value;

    TypeCabin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TypeCabin getType(int value) {
        if (value == 1) return TypeCabin.ECONOMY;
        if (value == 2) return TypeCabin.BUSINESS;
        if (value == 3) return TypeCabin.ECONOMY_AND_BUSINESS;
        return null;
    }

    public static void printEnum(TypeCabin[] typeCabin) {
        System.out.println("*********************************");
        System.out.println("ID\tClass cabin");
        System.out.println("*********************************");
        for (TypeCabin type : TypeCabin.values()) {
            System.out.println(type.getValue() + ".\t" + type.name());
        }
        System.out.println("*********************************");
    }
}
