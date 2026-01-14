package org.example.lab3;

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

public class LabThree {
    private static final String url = "https://5a571856-e7ec-47ea-b2a6-94b799b66603.mock.pstmn.io";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = url;
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().build();
    }

    @Test
    public void verifyName() {
        Response response = given().get("/ownerName");
        response.then().statusCode(HttpStatus.SC_OK);
        System.out.println("Відповідь на запит по /ownerName");
        System.out.println(response.getBody().asString());
        System.out.println("============================");
    }

    @Test
    public void verifyNameUnsuccess() {
        Response response = given().get("/ownerName/unsuccess");
        response.then().statusCode(HttpStatus.SC_FORBIDDEN);
        System.out.println("Відповідь на запит по /ownerName/unsuccess");
        System.out.println(response.getBody().asString());
        System.out.println("============================");
    }

    @Test
    public void verifyCreateSomething() {
        Response response = given().post("/createSomething?permission=yes");
        response.then().statusCode(HttpStatus.SC_OK);
        System.out.println("Відповідь на запит /createSomething?permission=yes");
        System.out.println(response.getBody().asString());
        System.out.println("============================");
    }

    @Test
    public void verifyCreateSomethingWithoutPermission() {
        Response response = given().post("/createSomething");
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
        System.out.println("Відповідь на запит /createSomething");
        System.out.println(response.getBody().asString());
        System.out.println("============================");
    }

    @Test
    public void verifyPutMethod() {
        Map<String, ?> body = Map.of (
                "name", "Artem",
                "surname", "Radostiev"
        );

        Response response = given().body(body).put("/updateMe");
        response.then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        System.out.println("Відповідь на запит /updateMe");
        System.out.println(response.getBody().asString());
        System.out.println("============================");
    }

    @Test
    public void verifyDeleteMethod() {
        Response response = given().delete("/deleteWorld?SessionID=12345");
        response.then().statusCode(HttpStatus.SC_GONE);
        System.out.println("Відповідь на запит /deleteWorld з параметром сесії");
        System.out.println(response.getBody().asString());
        System.out.println("============================");
    }
}
