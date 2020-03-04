package com.yevtua.data.enums;

public enum CookiesEnum {
    LOGGED_IN("logged_in");

    private String name;

    CookiesEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
