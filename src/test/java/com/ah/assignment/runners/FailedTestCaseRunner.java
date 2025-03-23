package com.ah.assignment.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-html-reports/cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/junit-reports/cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",

        },
        features = "@target/rerun/failed-tests.txt",
        glue = {"com.ah.assignment.stepDefinitions"},
        monochrome = true,
        publish = true
)
public class FailedTestCaseRunner {
}
