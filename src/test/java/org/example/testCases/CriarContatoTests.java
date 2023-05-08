package org.example.testCases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CriarContatoTests {

    private Contact contact;

    @BeforeAll
    public static void setup() { RestAssured.baseURI = "https://private-anon-8453769886-apidetarefas.apiary-mock.com"; }

    @BeforeEach
    void init() { contact = new Contact(); }

    @Test
    public void createContact(){
        Response response = RestAssured.given().
                header("Content-type", "application/json").
                body(contact.asString()).
            when().
                post("/contacts").
            then().
                statusCode(201).extract().response();

        Contact contactResponse = new Contact(response.jsonPath());
        Assertions.assertEquals(contactResponse, contact);
    }

    @Test
    public void contract(){
        RestAssured.given().
            when().
                get("/contacts").
            then().
                statusCode(201).body(matchesJsonSchemaInClasspath("criarContatoSchema.json"));
    }

    @Test
    public void createContactWithoutEmail(){
        contact.setEmail("");

        Response response = RestAssured.given().
                header("Content-type", "application/json").
                body(contact.asString()).
            when().
                post("/contacts").
            then().
                statusCode(400).extract().response();

        String errorMessage = response.jsonPath().get("errors.email");
        Assertions.assertEquals(errorMessage, "não pode ficar em branco");
    }

    @Test
    public void createContactWithAlreadyRegisteredEmail() {
        RestAssured.given().
            header("Content-type", "application/json").
            body(contact.asString()).
        when().
            post("/contacts").
        then().
            statusCode(201);

        Contact newContact = new Contact();
        newContact.setEmail(contact.getEmail());

        Response response = RestAssured.given().
                header("Content-type", "application/json").
                body(newContact.asString()).
            when().
                post("/contacts").
            then().
                statusCode(400).extract().response();

        String errorMessage = response.jsonPath().get("errors.email");
        Assertions.assertEquals(errorMessage, "já está em uso");
    }

    @Test
    public void createContactWithoutName(){
        contact.setFirstName("");

        Response response = RestAssured.given().
                header("Content-type", "application/json").
                body(contact.asString()).
            when().
                post("/contacts").
            then().
                statusCode(400).extract().response();

        String errorMessage = response.jsonPath().get("errors.name");
        Assertions.assertEquals(errorMessage, "não pode ficar em branco");
    }

    @Test
    public void createContactWithAgeAsString(){
        String contatAsString = contact.asString().replace("\"age\":" + contact.getAge(), "\"age\":\"" + contact.getAge() + "\"");

        Response response = RestAssured.given().
                header("Content-type", "application/json").
                body(contatAsString).
            when().
                post("/contacts").
            then().
                statusCode(400).extract().response();

        String errorMessage = response.jsonPath().get("errors.age");
        Assertions.assertEquals(errorMessage, "não é um número");
    }
}
