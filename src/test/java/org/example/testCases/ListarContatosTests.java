package org.example.testCases;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ListarContatosTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://private-anon-8453769886-apidetarefas.apiary-mock.com";
    }

    @Test
    public void statusCode(){
        RestAssured.given().
        when().
            get("/contacts").
        then().
            statusCode(200);
    }

    @Test
    public void contract(){
        RestAssured.given().
        when().
            get("/contacts").
        then().
            statusCode(200).body(matchesJsonSchemaInClasspath("listar-contatos-schema.json"));
    }
}