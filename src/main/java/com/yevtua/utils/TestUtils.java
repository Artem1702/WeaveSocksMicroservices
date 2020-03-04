package com.yevtua.utils;

import java.time.LocalTime;

public class TestUtils {

    public static String createUsername() {
        return "user" + LocalTime.now().hashCode();
    }

    public static String createEmailForUser(String username) {
        return username + "@mail.com";
    }

    public static String generatePassword() {
        return "pass" + LocalTime.now().hashCode();
    }
}
