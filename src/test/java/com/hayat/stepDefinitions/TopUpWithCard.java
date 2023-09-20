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

public class TopUpWithCard {
    @And("user post request to top up money with card")
    public void userPostRequestToTopUpMoneyWithCard() {

        Map<String,Object> payload = new LinkedHashMap<>();
        payload.put("customerNo", "277912987");
        payload.put("currency", "TRY");
        payload.put("amount", "1100");//min 1000 TRY

        Response response = given().header("Authorization", SignIn.accessToken).accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().body(payload).log().all()
                .and().post("/api/v1/top-up/card/init").then().log().all().extract().response();


        assertThat(response.statusCode(),is(200));
    }

    @Then("user post request to success card top up")
    public void userPostRequestToSuccessCardTopUp() {

        Response response = given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("Response", "Approved")
                .formParam("ReturnOid", "7e7fd903-c05b-4bcb-a935-afb96f347199")
                .formParam("AuthCode", 5555)
                .formParam("HostRefNum", 5858)
                .formParam("ProcReturnCode", 6969)
                .formParam("TransId", 6974455)
                .formParam("mdStatus", 5)
                .formParam("MaskedPan", 896314)
                .formParam("HASH", "mTIpFx44s7xpG2p0efXKBiY8gYE")
                .formParam("clientid", "520000240")
                .formParam("amount", 1100)
                .formParam("oid", "7e7fd903-c05b-4bcb-a935-afb96f347199")
                .formParam("rnd", "Wed Feb 15 15:55:39 TRT 2023")
                .formParam("okUrl", "https://bff-hayat-dev.finartz.dev/api/v1/top-up/card/success-call-back-url")
                .formParam("failUrl", "https://bff-hayat-dev.finartz.dev/api/v1/top-up/card/success-call-back-url")
                .formParam("currency", "008")
                .formParam("storetype", "3d_pay_hosting")
                .formParam("refreshtime", 10)
                .formParam("encoding", "utf-8")
                .header("Authorization", SignIn.accessToken).accept(ContentType.JSON)
                .when().post("/api/v1/top-up/card/success-call-back-url").then().log().all().extract().response();


        assertThat(response.statusCode(),is(200));
    }
}
