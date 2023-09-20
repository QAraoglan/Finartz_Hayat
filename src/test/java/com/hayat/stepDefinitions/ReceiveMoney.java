package com.hayat.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReceiveMoney {
    public static String contractNumber;


    @And("user want to receive money")
    public void userWantToReceiveMoney() {

        Map<String,Object> payload = new LinkedHashMap<>();
        payload.put("requesterCustomerNo", "277912987");
        payload.put("requestedCustomerNo", null);
        payload.put("requestedCustomerPhoneNumber", "+905456542211");
        payload.put("amount", 10);//min 10 TRY
        payload.put("currency", "TRY");
        payload.put("description", "paraistedim");

        Response response = given().header("Authorization", SignIn.accessToken).accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().body(payload).log().all()
                .and().post("/api/v1/receive-money/init").then().log().all().extract().response();

        contractNumber = response.jsonPath().getString("data.contractNo");

        assertThat(response.statusCode(),is(200));

    }


    @And("user cancel money receive")
    public void userCancelMoneyReceive() {

        Response response = given().header("Authorization", SignIn.accessToken).queryParam("contractNo",contractNumber).accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().put("/api/v1/receive-money/cancel").then().log().all().extract().response();


        assertThat(response.statusCode(),is(200));
    }


    @Then("user complete money receive")
    public void userCompleteMoneyReceive() {

        Map<String,Object> payload = new LinkedHashMap<>();
        payload.put("contractNo", contractNumber);
        payload.put("status", "APPROVED");

        Response response = given().header("Authorization", SignIn.accessToken).accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().body(payload).log().all()
                .and().put("/api/v1/receive-money/complete").then().log().all().extract().response();


        assertThat(response.statusCode(),is(200));
    }
}
