package com.hm.test.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

//@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/com/hm/test/features/"},glue = {"com.hm.test.steps"},  plugin = {"pretty",
        "json:target/MyReports/report.json",
        "junit:target/MyReports/report.xml",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        dryRun = false,
        monochrome = false)

public class TestRunner extends AbstractTestNGCucumberTests  {

}
