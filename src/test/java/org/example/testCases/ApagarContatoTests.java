package org.example.testCases;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ApagarContatoTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://private-anon-8453769886-apidetarefas.apiary-mock.com";
    }

    @Test
    public void deleteContact(){
        RestAssured.given().
        when().
            delete("/contacts").
        then().
            statusCode(204);
    }
}
