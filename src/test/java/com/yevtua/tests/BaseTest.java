package com.yevtua.tests;

import com.jayway.restassured.RestAssured;
import com.yevtua.utils.PropertyReader;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    @BeforeClass
    public void setBaseUri() {
        RestAssured.baseURI = PropertyReader.getInstance().getBaseUri();
    }

}
