//package CustomExceptions;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//
//public class SampleCustomException {
//    public static ExpectedCondition<WebElement> exampleExpectedCondition(final By locator,
//                                 final String attributeName, final String expectedValue) {
//        WebDriver driver = new ChromeDriver();
//        return new ExpectedCondition<WebElement>() {
//
//            @Override
//            public WebElement apply(WebDriver input) {
//                // custom logic for
//                try {
//                    WebElement element = driver.findElement(locator);
//                    // Check if the attribute exists and its value matches the expected value
//                    if (element.getAttribute(attributeName) != null &&
//                            element.getAttribute(attributeName).equals(expectedValue)) {
//                        return element; // Condition met, return the element
//                    } else {
//                        return null; // Condition not met, continue polling
//                    }
//                } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
//                    return null; // Element not found or stale, continue polling
//                }
//            }
//
//        };
//    }
//}
