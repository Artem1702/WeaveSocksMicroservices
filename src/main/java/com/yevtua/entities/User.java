package com.yevtua.entities;

import java.io.Serializable;

public class User extends BaseUser implements Serializable {
    public User(String username, String password, String email) {
        super(username, password, email);
    }

    protected static class BuilderImpl extends BaseUser.BuilderImpl {
        public String username;
        public String password;
        public String email;
    }

    public static class Builder extends BuilderImpl {
        public User create() {
            return new User(
                    username,
                    password,
                    email
            );
        }
    }
}
