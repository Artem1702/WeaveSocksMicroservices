package com.yevtua.data;

import com.yevtua.entities.BaseUser;
import com.yevtua.entities.User;
import com.yevtua.utils.PropertyReader;
import com.yevtua.utils.TestUtils;

public class UserGenerator {

    public static BaseUser generateNewUser() {
        return new User.Builder() {{
            username = TestUtils.createUsername();
            password = TestUtils.generatePassword();
            email = TestUtils.createEmailForUser(username);
        }}.create();
    }

    public static User getAlreadyRegisteredUser() {
        return new User.Builder() {{
            username = PropertyReader.getInstance().getAlreadyRegisteredUsername();
            password = PropertyReader.getInstance().getAlreadyRegisteredPassword();
        }}.create();
    }
}
