package com.hayat.stepDefinitions;

import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SendMoney {
    @Then("user send money to hayat customer")
    public void userSendMoneyToHayatCustomer() {

        Map<String,Object> payload = new LinkedHashMap<>();
        payload.put("senderCustomerNo", "277912987");
        payload.put("receiverCustomerNo", "310624559");
        payload.put("amount", 10);
        payload.put("currency", "TRY");
        payload.put("description", "paragonderdim");

        Response response = given().header("Authorization", SignIn.accessToken).accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().body(payload).log().all()
                .and().post("/api/v1/send-money").then().log().all().extract().response();


        assertThat(response.statusCode(),is(200));
    }
}
