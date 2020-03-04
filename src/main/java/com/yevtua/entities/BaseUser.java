package com.yevtua.entities;

public abstract class BaseUser {
    public String username;
    public String password;
    public String email;
    public BaseUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    protected static class BuilderImpl {

    }
}
