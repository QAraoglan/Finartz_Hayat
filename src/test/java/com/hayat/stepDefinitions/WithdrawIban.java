package com.hayat.stepDefinitions;

import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WithdrawIban {


    @Then("user withdraw money from iban {string}")
    public void userWithdrawMoneyFromIban(String iban) {

        Map<String,Object> payload = new LinkedHashMap<>();
        payload.put("iban", iban);
        payload.put("amount", 50);
        payload.put("currency", "TRY");
        payload.put("description", "desc");

        Response response = given().header("Authorization", SignIn.accessToken).accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().body(payload).log().all()
                .and().post("/api/v1/withdraw/iban").then().log().all().extract().response();


        assertThat(response.statusCode(),is(200));

    }
}
