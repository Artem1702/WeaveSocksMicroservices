package com.yevtua.data.enums;

public enum EndPointsEnum {
    LOGIN("login"),
    REGISTER("register");

    private String endpoint;

    EndPointsEnum(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
