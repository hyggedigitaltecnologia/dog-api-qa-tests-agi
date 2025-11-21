package br.com.jhonattan.dogapi;

import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AllureJunit5.class)
public abstract class BaseApiTest {

    @BeforeAll
    static void setupRestAssured() {
        RestAssured.baseURI = "https://dog.ceo/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        // RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Step("Chamando endpoint: {endpoint}")
    protected void logEndpoint(String endpoint) {
    }
}