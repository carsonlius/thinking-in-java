package com.carsonlius.thinkinjava.enums;

public enum  OzWitch {
    WEST("西面"), EAST("东面");

    private String description;

    OzWitch(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void main(String[] args) {
        for (OzWitch value : OzWitch.values()) {
            System.out.println("value: " + value + " description:" + value.getDescription());

        }
    }
}
