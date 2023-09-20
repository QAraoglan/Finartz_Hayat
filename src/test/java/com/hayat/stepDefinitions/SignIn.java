package com.hayat.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SignIn {

    public static String accessToken;
    public static String verificationId;

    @Given("user sign in to account")
    public void userSignInToAccount() {

        Map<String,Object> payload = new LinkedHashMap<>();
        payload.put("username","+905552221133");
        payload.put("password","Ji7/H4U+R48EsFEwW3sIfBrPUfwBLgQPdNyecbjcM8ezaJ6CsKYr3YTCejtRBxgn3Lg8cvAPqSWsNTj7d0s/dDIYwfhJ+8xqJsvIXhPfTcwj4k/ao1Ff6gM7b0HJYCdcjz5bk+8+hJeYh+hKAAmO+KnQfjo+KxRKaAgn/b4eEPQ=");

        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().body(payload).log().all()
                .and().post("/api/v1/sign-in/initialize").then().log().all().extract().response();

        verificationId = response.jsonPath().getString("data.verificationId");

    }

    @When("user verify sign in")
    public void userVerifySignIn() {
        Map<String,Object> payload = new LinkedHashMap<>();
        payload.put("deviceId", "290601");
        payload.put("deviceRegistrationToken", "new-tokennn");
        payload.put("verificationId", verificationId);
        payload.put("otp", "123456");
        payload.put("rememberMe", true);

        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().body(payload).log().all()
                .and().post("/api/v1/sign-in/verify").then().log().all().extract().response();

        accessToken = "Bearer "+response.jsonPath().getString("data.accessToken");

    }
}
