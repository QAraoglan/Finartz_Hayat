package com.hayat.stepDefinitions;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.javafaker.Faker;
import com.hayat.utilities.Environment;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class Register {
    
    Map<String,Object> otpSend = new HashMap<>();
    Map<String,Object> otpVerify = new HashMap<>();
    Map<String,Object> register = new HashMap<>();

    Faker faker=new Faker();

    private String token;


    @Given("user send post request to sms OTP")
    public void userSendPostRequestToSmsOTP() {
        otpSend.put("phoneNumber", faker.numerify("+90532#######"));
        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().body(otpSend).log().all()
                .and().post("https://bff-hayat-dev.finartz.dev" + "/api/v1/register/otp/sms").then().log().all().extract().response();

        assertThat(response.statusCode(), is(200));

        JsonPath jsonPath = response.jsonPath();

        otpVerify = jsonPath.getObject("data", Map.class);

    }

    @When("user send post request to OTP verify")
    public void userSendPostRequestToOTPVerify() {
        otpVerify.put("operationType", "VERIFY_PHONE_NUMBER");

        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().body(otpVerify).log().all()
                .and().post("https://bff-hayat-dev.finartz.dev" + "/api/v1/register/otp/verify").then().log().all().statusCode(200).extract().response();

        assertThat(response.statusCode(),is(200));

    }

    @Then("user send post request to register with valid personal info")
    public void userSendPostRequestToRegisterWithValidPersonalInfo() {
        register.put("transactionId",otpVerify.get("transactionId"));
        register.put("firstName",faker.name().firstName());
        register.put("lastName",faker.name().lastName());
        register.put("deviceRegistrationToken","HF");
        register.put("nationalId",faker.numerify("###########"));
        register.put("birthDate",faker.numerify("19##-01-01"));
        register.put("password","Ji7/H4U+R48EsFEwW3sIfBrPUfwBLgQPdNyecbjcM8ezaJ6CsKYr3YTCejtRBxgn3Lg8cvAPqSWsNTj7d0s/dDIYwfhJ+8xqJsvIXhPfTcwj4k/ao1Ff6gM7b0HJYCdcjz5bk+8+hJeYh+hKAAmO+KnQfjo+KxRKaAgn/b4eEPQ=");
        register.put("deviceId",faker.numerify("######"));

        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().body(register).log().all()
                .and().post("https://bff-hayat-dev.finartz.dev" + "/api/v1/register").then().log().all().statusCode(200).extract().response();

        assertThat(response.statusCode(),is(200));

        token = "Bearer "+response.jsonPath().getString("data.token.accessToken");
    }
}
