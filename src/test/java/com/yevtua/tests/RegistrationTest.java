package com.yevtua.tests;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ExtractableResponse;
import com.jayway.restassured.response.Response;
import com.yevtua.data.UserGenerator;
import com.yevtua.entities.BaseUser;
import com.yevtua.data.enums.CookiesEnum;
import org.testng.annotations.Test;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static com.yevtua.data.enums.EndPointsEnum.REGISTER;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RegistrationTest extends BaseTest {

    @Test(groups = "registration")
    public void verifyValidRegistration() {
        BaseUser user = UserGenerator.generateNewUser();

        ExtractableResponse<Response> response;

        response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(REGISTER.getEndpoint())
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .extract();

        assertThat(response.cookies(), hasKey(CookiesEnum.LOGGED_IN.getName()));
        assertThat(response.cookies().get(CookiesEnum.LOGGED_IN.getName()).length(), equalTo(32));
        assertThat(response.body().jsonPath().get().toString(), containsString("id"));
    }

    @Test(groups = "registration")
    public void verifyRegistrationWithAlreadyRegisteredUsername() {
        BaseUser alreadyRegisteredUser = UserGenerator.getAlreadyRegisteredUser();

        Map<String, String> cookies  = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(alreadyRegisteredUser)
                .when()
                .post(REGISTER.getEndpoint())
                .then()
                .assertThat()
                .statusCode(SC_INTERNAL_SERVER_ERROR)
                .extract()
                .cookies();
        assertThat(cookies, not(hasKey(CookiesEnum.LOGGED_IN.getName())));
    }
}
