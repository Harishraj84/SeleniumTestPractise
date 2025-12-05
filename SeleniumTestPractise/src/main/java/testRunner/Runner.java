package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",             // IMPORTANT: Reads failed scenarios from this file
        glue = "StepDefinitions",
        plugin = {
                "pretty",
                "html:build/reports/cucumber/rerun/cucumber-report.html",
        },
        monochrome = true
)
public class Runner {
    // This class runs only the scenarios listed in rerun.txt
}