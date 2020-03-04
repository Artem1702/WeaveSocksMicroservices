package com.yevtua.tests;

import com.yevtua.utils.TestUtils;
import com.yevtua.entities.BaseUser;
import com.yevtua.data.enums.CookiesEnum;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static com.yevtua.data.UserGenerator.getAlreadyRegisteredUser;
import static com.yevtua.data.enums.EndPointsEnum.LOGIN;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginTest extends BaseTest {

    @Test(groups = "login")
    public void verifyValidLogin() {
        BaseUser user = getAlreadyRegisteredUser();

        Map<String, String> cookies = given()
                .auth()
                .preemptive()
                .basic(user.username, user.password)
                .when()
                .get(LOGIN.getEndpoint())
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .cookies();

        assertThat(cookies, hasKey(CookiesEnum.LOGGED_IN.getName()));
        assertThat(cookies.get(CookiesEnum.LOGGED_IN.getName()).length(), equalTo(32));
    }

    @Test(groups = "login")
    public void verifyLoginWithoutCreds() {
        Map<String, String> cookies = given()
                .auth()
                .preemptive()
                .basic("", "")
                .when()
                .get(LOGIN.getEndpoint())
                .then()
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .extract()
                .cookies();

        assertThat(cookies, hasKey(CookiesEnum.LOGGED_IN.getName()));
        assertThat(cookies.get(CookiesEnum.LOGGED_IN.getName()).length(), equalTo(32));
    }

    @Test(groups = "login")
    public void verifyInvalidPasswordLogin() {
        BaseUser user = getAlreadyRegisteredUser();

        Map<String, String> cookies = given()
                .auth()
                .basic(user.username, TestUtils.generatePassword())
                .when()
                .get(LOGIN.getEndpoint())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .cookies();

        assertThat(cookies, not(hasKey(CookiesEnum.LOGGED_IN.getName())));
    }

    @Test(groups = "login")
    public void verifyInvalidCredsLogin() {
        Map<String, String> cookies = given()
                .auth()
                .basic(TestUtils.createUsername(), TestUtils.generatePassword())
                .when()
                .get(LOGIN.getEndpoint())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .cookies();

        assertThat(cookies, not(hasKey(CookiesEnum.LOGGED_IN.getName())));
    }
}
