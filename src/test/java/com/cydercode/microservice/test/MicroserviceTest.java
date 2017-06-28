package com.cydercode.microservice.test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class MicroserviceTest {

    @Test
    public void indexShouldReturnHelloMicroService() {
        restAssured().get("/")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(equalTo("Hello MicroService!"));
    }

    private RequestSpecification restAssured() {
        return RestAssured.given().log().all();
    }
}
