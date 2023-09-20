package com.hayat.stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TopUpIban {

    Faker faker = new Faker();

    @And("user top up with Iban")
    public void userTopUpWithIban() {

        Map<String,Object> details = new LinkedHashMap<>();
        details.put("description", "TR1906250000");

        Map<String,Object> transaction = new LinkedHashMap<>();
        transaction.put("number", faker.numerify("####"));
        transaction.put("type", "DEBIT");
        transaction.put("date","2023-08-16T09:32:22.5199233");
        transaction.put("category", "ATM");
        transaction.put("amount", 100.00);
        transaction.put("currency", "TRY");
        transaction.put("details", details);

        Map<String,Object> bank = new LinkedHashMap<>();
        bank.put("code",1);
        bank.put("name","Hayat");
        Map<String,Object> accountInfo = new LinkedHashMap<>();
        accountInfo.put("iban", 123456789);
        accountInfo.put("bank", bank);

        Map<String,Object> identifier = new LinkedHashMap<>();
        identifier.put("no", "191625");//müşteri nationalId
        identifier.put("type", "id");

        Map<String,Object> client = new LinkedHashMap<>();
        client.put("identifier",identifier);
        client.put("accountInfo",accountInfo);
        client.put("fullName", "Ali Karaoglan");

        Map<String,Object> payload = new LinkedHashMap<>();
        payload.put("messageID", "some guid");
        payload.put("safekeepingAccountNumber", "account number");
        payload.put("transaction", transaction);
        payload.put("client", client);


        Response response = given().header("Authorization", SignIn.accessToken).accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().body(payload).log().all()
                .and().post("https://topup-hayat-dev.finartz.dev" + "/top-up/iban").then().log().all().statusCode(200).extract().response();

        assertThat(response.statusCode(),is(200));



    }
}
