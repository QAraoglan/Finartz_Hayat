package com.hayat.stepDefinitions;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Test1 {
Faker faker=new Faker();

    @Test
    public void test1(){


        System.out.println(faker.numerify("19##-01-01"));
        System.out.println(faker.numerify("+90532#######"));
        Map<String,Object> requestBody = new HashMap<>();
      requestBody.put("phoneNumber", faker.phoneNumber() );
      given().accept(ContentType.JSON).contentType(ContentType.JSON)
              .when().body(requestBody).log().all().and().post("https://bff-hayat-dev.finartz.dev/api/v1/register/otp/sms")
              .then().log().all().statusCode(200).log().all();

    }
}
