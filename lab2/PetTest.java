package org.example.lab2;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetTest {
    private static final String baseUrl = "https://petstore.swagger.io/v2";

    private static final String PET = "/pet";

    private long  petId = 555123123;
    String[] urls = {"https://kabanchik.jpg"};

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseUrl;
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().build();
    }

    @Test
    public void verifyPetAddingAction() {
        Map<String, ?> body = Map.of(
                "id", petId,
                "name", "Radostiev Artem's kabanchik",
                "status", "very cool",
                "photoUrls", urls
        );
        Response response = given().body(body).post(PET);
        response.then().statusCode(HttpStatus.SC_OK);
    }


    @Test(dependsOnMethods = "verifyPetAddingAction")
    public void verifyCreateAction(){
        Map<String, ?> body = Map.of (
                "petId", petId
        );
        Response response = given().body(body).get(PET + "/" + petId);
        response.then().statusCode(HttpStatus.SC_OK);
        System.out.println("Відповідь на VerifyCreateAction");
        System.out.println(response.getBody().asString());
        System.out.println("============================");
    }

    @Test(dependsOnMethods = "verifyPetAddingAction")
    public void verifyUpdateAction(){
        Map<String, ?> body = Map.of (
                "id", petId,
                "name", "Radostiev Artem's 121-22-1 kabanchik",
                "status", "still very cool",
                "photoUrls", urls
        );
        Response response = given().body(body).put(PET);
        response.then().statusCode(HttpStatus.SC_OK);
        System.out.println("Відповідь на PUT");
        System.out.println(response.getBody().asString());
        System.out.println("============================");
    }

}

