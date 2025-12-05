package StepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SampleStepDefinitions {

    WebDriver driver;


    @Given("User is on the Google Home Page")
    public void user_is_on_google_home_page() {
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @When("User types {string} in the search box")
    public void userTypesInTheSearchBox(String arg0) {

    }

    @And("User clicks on the Google Search button")
    public void userClicksOnTheGoogleSearchButton() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("User should see results related to {string}")
    public void userShouldSeeResultsRelatedTo(String arg0) {
        // Write code here that turns the phrase above into concrete actions
    }


}
