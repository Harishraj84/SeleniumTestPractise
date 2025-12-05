package Pages;

import CustomExceptions.SampleCustomException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assert;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class SamplePageClass {

    WebDriver driver = new ChromeDriver();

    @Given("the basic things for selenium")
    public void theBasicThingsForSelenium() {
      //  driver = new ChromeDriver();
    }

    @And("Run the tests in selenium grid")
    public void runTheTestsInSeleniumGrid() throws MalformedURLException, InterruptedException {
        // Example code to run tests in Selenium Grid
        // Set the desired capabilities for the browser
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.WINDOWS); // You can set Linux or Mac
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.100:4444/"), capabilities);

        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        System.out.println("Tile of the page is: " + driver.getTitle());
        Thread.sleep(10000);
        driver.quit();

    }

        @When("Handle the window")
    public void handleTheWindow() {
        driver.manage().window().maximize();
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(parentWindow))
                driver.switchTo().window(handle);
        }
    }

    @And("file uploading")
    public void fileUploading() {
        System.setProperty("webdriver.chrome.driver", "E://chromedriver.exe");
        driver.findElement(By.id("")).sendKeys("");
    }

    @And("Implicit wait Explicit wait Fluent wait")
    public void implicitWaitExplicitWaitFluentWait() {
        WebDriver.Timeouts timeouts = driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(""))));

        // Using custom Expected Condition
        WebElement element1 = wait.until(SampleCustomException.exampleExpectedCondition(By.id(""), "", ""));


        // Fluent Wait
        Wait<WebDriver> fluentWait = new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class);
        WebElement elementFluent = fluentWait.until(driver -> driver.findElement(By.id("")));

        // Another Fluent Wait example
        FluentWait<WebDriver> fluentWait1 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        WebElement element11 = fluentWait1.until(driver -> driver.findElement(By.id("")));
    }

    @And("Assertions in selenium")
    public void assertionsInSelenium() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Expected Title";
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualTitle).isEqualTo(expectedTitle);
        softAssertions.assertAll();

        Assertions.assertEquals("a", "a", "are not equal");
    }

    @And("Shadow dom handling")
    public void shadowDomHandling() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = (WebElement) js.executeScript("return arguments[0].shadowRoot", driver.findElement(By.id("")));

        SearchContext shadowRoot = driver.findElement(By.cssSelector("host-element-selector")).getShadowRoot();
        WebElement shadowElement = shadowRoot.findElement(By.cssSelector("shadow-element-selector"));

        // Interact with nested shadowElement as needed
        SearchContext nestedShadowRoot = shadowElement.getShadowRoot();
        WebElement nestedShadowElement = nestedShadowRoot.findElement(By.cssSelector("nested-shadow-element-selector"));
        WebElement nestedShadowElement1 = driver.findElement(By.cssSelector("host-element-selector"))
                .getShadowRoot()
                .findElement(By.cssSelector("shadow-element-selector"))
                .getShadowRoot()
                .findElement(By.cssSelector("nested-shadow-element-selector"));


    }

    @And("Handling the alerts")
    public void handlingTheAlerts() {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept(); // To accept the alert
        alert.dismiss(); // To dismiss the alert
        alert.sendKeys("abc"); // To send text to the alert
    }

    @And("Handling the frames")
    public void handlingTheFrames() {
        driver.switchTo().frame(0); // Switch by index
        driver.switchTo().frame("frameName"); // Switch by name or ID
        WebElement frameElement = driver.findElement(By.id("frameId"));
        driver.switchTo().frame(frameElement); // Switch by WebElement
        driver.switchTo().defaultContent(); // Switch back to main content
        driver.switchTo().parentFrame(); // Switch back to parent frame
    }

    @And("Handling file downloads")
    public void handlingFileDownloads() {
        // File download handling can be complex and may require browser-specific settings.
        // Here is a basic example for Chrome using ChromeOptions to set the download directory.
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "C:\\path\\to\\download\\directory");
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        driver.findElement(By.id("")).click(); // Trigger file download
    }

    @And(("Mouse hover action"))
    public void mouseHoverAction() {
        // Mouse hover action using Actions class
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        WebElement elementToHover = driver.findElement(By.id("elementId"));
        actions.moveToElement(elementToHover).perform();    // Perform the hover action
        actions.clickAndHold(); //
        actions.contextClick(); // Right click
        actions.doubleClick(); // Double click
        //actions.dragAndDrop(By.xpath(""),""); //
        //actions.keyUp().keyDown().keyUp().release(); //
    }
}