package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {
    public static WebDriver createDriver() throws Exception {
        String gridUrl = System.getProperty("grid.url","http://localhost:4444");
        String browser = System.getProperty("browser","chrome").toLowerCase();

        URL hubUrl = new URL(gridUrl + (gridUrl.endsWith("/wd/hub") ? "" : "wd/hub"));

        switch (browser) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability("se:recordVideo", false);
                return new RemoteWebDriver(hubUrl, firefoxOptions);
            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.setCapability("se:region","IN");
                return new RemoteWebDriver(hubUrl, chromeOptions);
        }
    }
}
