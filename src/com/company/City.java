package com.company;

public enum City {
    TAIPEI(0),
    TAICHUNG(1),
    KAOHSIUNG(2),
    OTHER(3);

    private int id;
    City(int id) {
        this.id = id;
    }

    int getId(){
        return this.id;
    }

    public static City fromId(int id) {
        for (City type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }
}
