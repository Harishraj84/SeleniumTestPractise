//package testRunner;
//
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.runner.RunWith;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = "src/test/resources/features",             // IMPORTANT: Reads failed scenarios from this file
//        glue = "StepDefinitions",
//        plugin = {
//                "pretty",
//                "html:target/cucumber-reports/cucumber-report.html",
//                "json:target/cucumber-reports/cucumber.json"
//        },
//        monochrome = true,
//        tags = "@SmokeTest"
//)
//public class Runner {
//    // This class runs only the scenarios listed in rerun.txt
//}