package com.example.hotel.enums;

public enum Breakfast {
    yes("有"),
    no("无");

    private String value;

    Breakfast(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
