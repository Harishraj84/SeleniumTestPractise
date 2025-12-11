package Pages;

//import CustomExceptions.SampleCustomException;
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
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

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
       // WebElement element1 = wait.until(SampleCustomException.exampleExpectedCondition(By.id(""), "", ""));


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

    @And("Handling cookies in selenium")
    public void handlingCookiesInSelenium() {
        // Adding a cookie
        Cookie cookie = new Cookie("cookieName", "cookieValue");
        driver.manage().addCookie(cookie);
        // Retrieving a cookie
        Cookie retrievedCookie = driver.manage().getCookieNamed("cookieName");
        System.out.println("Cookie Value: " + retrievedCookie.getValue());
        // Deleting a cookie
        driver.manage().deleteCookieNamed("cookieName");
        // Deleting all cookies
        driver.manage().deleteAllCookies();
        // Getting all cookies
        Set<Cookie> allCookies = driver.manage().getCookies();
        for (Cookie ck : allCookies) {
            System.out.println(ck.getName() + " : " + ck.getValue());
        }

        // Note: Cookie handling may vary based on browser and its settings.
    }

    @And("Handling javascript executor in selenium")
    public void handlingJavascriptExecutorInSelenium() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down by 1000 pixels
        js.executeScript("window.scrollBy(0,1000)");
        // Scroll to a specific element
        WebElement element = driver.findElement(By.id("elementId"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        // Click an element using JavaScript
        js.executeScript("arguments[0].click();", element);
        // Get the title of the page
        String title = (String) js.executeScript("return document.title;");
        System.out.println("Page Title: " + title);
        // Set a value to an input field
        js.executeScript("arguments[0].value='New Value';", element);
        // Highlight an element
        js.executeScript("arguments[0].style.border='3px solid red'", element);
        // Note: Use JavaScriptExecutor judiciously, as it can bypass some of Selenium's built-in mechanisms.
    }

    @And("Handling the navigation commands in selenium")
    public void handlingTheNavigationCommandsInSelenium() {
        // Navigate to a URL
        driver.navigate().to("https://www.example.com");
        // Navigate back
        driver.navigate().back();
        // Navigate forward
        driver.navigate().forward();
        // Refresh the page
        driver.navigate().refresh();
        // Note: Navigation commands help in controlling the browser history and page state.
    }

    @And(("Handling the browser tabs in selenium"))
    public void handlingTheBrowserTabsInSelenium() {
        // Open a new tab
        ((JavascriptExecutor) driver).executeScript("window.open()");
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        // Switch to the new tab
        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
        }
        // Close the current tab
        driver.close();
        // Switch back to the original tab
        // (Assuming you have stored the original tab's handle)
        // driver.switchTo().window(originalHandle);
        // Note: Handling browser tabs may vary based on browser and its settings.
    }

    @And("Iterate the web table in selenium")
    public void iterateTheWebTableInSelenium() {
        WebElement table = driver.findElement(By.id("tableId"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                System.out.print(cell.getText() + "\t");
            }
        }
    }

    @And("Handling the dropdown in selenium")
    public void handlingTheDropdownInSelenium() {
        WebElement dropdownElement = driver.findElement(By.id("dropdownId"));
        Select select = new Select(dropdownElement);
        // Select by visible text
        select.selectByVisibleText("Option 1");
        // Select by value
        select.selectByValue("option2");
        // Select by index
        select.selectByIndex(3);
        // Deselect all options (if it's a multi-select dropdown)
        select.deselectAll();
        // Note: Ensure the dropdown element is a <select> tag for using the Select class.
    }

    @And("Take screenshot in selenium")
    public void takeScreenshotInSelenium() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        // Now you can save the screenshot file to a desired location
        // For example, using Apache Commons IO:
        // FileUtils.copyFile(screenshot, new File("path/to/save/screenshot.png"));
    }

    @And("Handling browser based pop ups in selenium")
    public void handlingBrowserBasedPopUpsInSelenium() {
        // Browser-based pop-ups are usually handled using the Alert interface in Selenium.
        Alert alert = driver.switchTo().alert();
        // Accept the alert
        alert.accept();
        // Dismiss the alert
        alert.dismiss();
        // Get the text of the alert
        String alertText = alert.getText();
        System.out.println("Alert Text: " + alertText);
        // Send text to the alert (if it's a prompt)
        alert.sendKeys("Sample Text");
        // Note: Ensure that the pop-up is indeed an alert; otherwise, this will throw an exception.
    }

    @And("Handling window system based pop ups in selenium")
    public void handlingWindowSystemBasedPopUpsInSelenium() {
        // Window/system-based pop-ups are not directly handled by Selenium.
        // You may need to use third-party tools like AutoIt (for Windows) or Robot class in Java.
        // Example using Robot class:
        try {
            java.awt.Robot robot = new java.awt.Robot();
            // Simulate pressing the Enter key to accept a pop-up
            robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
            robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("Handling file downloads in selenium")
    public void handlingFileDownloadsInSelenium() {
        // File download handling can be complex and may require browser-specific settings.
        // Here is a basic example for Chrome using ChromeOptions to set the download directory.
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "C:\\path\\to\\download\\directory");
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        driver.findElement(By.id("")).click(); // Trigger file download
    }

    @And("Handling file uploads in selenium")
    public void handlingFileUploadsInSelenium() {
        System.setProperty("webdriver.chrome.driver", "E://chromedriver.exe");
        driver.findElement(By.id("")).sendKeys("");
    }

    @And("Handling hidden elements in selenium")
    public void handlingHiddenElementsInSelenium() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement hiddenElement = driver.findElement(By.id("hiddenElementId"));
        js.executeScript("arguments[0].style.display='block';", hiddenElement);
        hiddenElement.click();
    }
}