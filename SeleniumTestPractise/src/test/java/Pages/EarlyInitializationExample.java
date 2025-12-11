package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EarlyInitializationExample {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "exampleId")
    private String exampleElement; // Early initialization of the element

    @FindBy(name = "exampleName")
    private WebElement anotherExampleElement;   // Early initialization of another element

    @FindBys({
        @FindBy(className = "exampleClass"),
        @FindBy(tagName = "exampleTag")
    })
    private String combinedExampleElement; // Early initialization using multiple criteria (AND logic)

    @FindAll(
        {
            @FindBy(xpath = "//div[@class='example1']"),
            @FindBy(css = ".example2")
        }
    )
    private String multipleExampleElement;   // Early initialization using multiple locators (OR logic)

    public String getExampleElement() {
        return exampleElement;
    }

    public String getAnotherExampleElement() {
        return anotherExampleElement.toString();
    }

    public String getCombinedExampleElement() {
        return combinedExampleElement;
    }

    public String getMultipleExampleElement() {
        return multipleExampleElement;
    }

    // Early initialization ensures that elements are ready for interaction as soon as the page object is created.
    public EarlyInitializationExample() throws InterruptedException {
        // Constructor logic if needed
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

}
