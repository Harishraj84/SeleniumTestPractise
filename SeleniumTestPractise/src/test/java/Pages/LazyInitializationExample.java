package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LazyInitializationExample {
    //
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "exampleId")
    private WebElement exampleElement; // Early initialization of the element

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

    public void getExampleElement() {
         wait.until(ExpectedConditions.visibilityOf(exampleElement)).sendKeys("name");;
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


}
