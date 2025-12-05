//package testRunner;
//
//package com.example.runners;
//
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.runner.RunWith;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = "src/test/resources/features", // Path to your feature files
//        glue = "com.example.steps",              // Package where your step definitions are
//        plugin = {
//                "pretty",
//                "html:target/cucumber-reports/chrome/cucumber-report.html", // Separate report for Chrome
//                "json:target/cucumber-reports/chrome/cucumber.json"
//        },
//        monochrome = true,
//        tags = "@Chrome" // Only run scenarios tagged with @Chrome
//)
//public class ChromeRunner {
//    // This runner will be executed by the 'chromeTest' Gradle task.
//}
