package com.hayat.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"rerun:target/rerun.txt",
                "html:target/cucumber-reports.html",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber",
                "json:target/cucumber.json"},
        stepNotifications = true,
        features = "src/test/resources/features/",
        glue = "com/hayat/stepDefinitions",
        dryRun = false,
        tags = ""
)
public class CukesRunner {
}
