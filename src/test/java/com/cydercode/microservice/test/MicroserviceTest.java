package com.cydercode.microservice.test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class MicroserviceTest {

    @Before
    public void init() {
        restAssured().delete("/allItems").then().log().all();
    }

    @Test
    public void indexShouldReturnHelloMicroService() {
        restAssured().get("/")
                .then()
                .assertThat()
                .statusCode(200)
                .body(equalTo("Hello MicroService!"))
                .and()
                .log()
                    .all();
    }

    @Test
    public void addedItemShouldBeVisible() {
        String item1 = RandomStringUtils.randomAlphabetic(15);
        String item2 = RandomStringUtils.randomAlphabetic(15);
        String expectedResult = String.format("%s, %s", item1, item2);

        restAssured()
                .given()
                    .body(item1)
                .when()
                    .post("/items")
                .then()
                    .body(equalTo("OK"))
                    .and()
                .statusCode(200);

        restAssured()
                .given()
                    .body(item2)
                .when()
                    .post("/items")
                .then()
                    .body(equalTo("OK"))
                    .and()
                .statusCode(200);

        restAssured()
                .when()
                    .get("/items")
                .then()
                    .body(equalTo(expectedResult))
                    .and()
                    .statusCode(200)
                .and()
                    .log()
                        .all();
    }

    public void shouldGetStatistics() {

    }

    private RequestSpecification restAssured() {
        return RestAssured.given().log().all();
    }
}
