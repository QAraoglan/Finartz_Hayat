package com.hayat.stepDefinitions;

import com.hayat.utilities.ConfigurationReader;
import com.hayat.utilities.DBUtils;
import com.hayat.utilities.Driver;
//import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Hooks {

	@BeforeAll
	public static void init(){

		RestAssured.baseURI = "https://bff-hayat-dev.finartz.dev";

	}

	@Before("@db")
	public void dbHook() {
		System.out.println("creating database connection");
		DBUtils.createConnection();
	}
	
	@After("@db")
	public void afterDbHook() {
		System.out.println("closing database connection");
		DBUtils.destroyConnection();

	}
	
	@Before("@ui")
	public void setUp() {
		// we put a logic that should apply to every scenario
		Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	
	//@After
//	public void tearDown(Scenario scenario) {
//		// only takes a screenshot if the scenario fails
//		if (scenario.isFailed()) {
//			// taking a screenshot
//			final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
//			scenario.embed(screenshot, "image/png");
//		}
//		Driver.closeDriver();
//	}
	
	
	
	
	
}
