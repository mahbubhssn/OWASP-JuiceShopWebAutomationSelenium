package com.herokuapp.juiceshop.base;

import com.herokuapp.juiceshop.util.GeneralUtility;
import com.herokuapp.juiceshop.util.WebEventListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    protected static WebDriver driver;
    protected Properties properties;
    protected EventFiringWebDriver e_driver;
    protected WebEventListener eventListener;

    public BaseTest() {
        try {
            String filePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
            properties = new Properties();
            FileInputStream inputStream = new FileInputStream(filePath);
            properties.load(inputStream);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initializeBrowser() {

        String desiredBrowser = properties.getProperty("browserName");
        String headLess = properties.getProperty("headLess");

        switch (desiredBrowser) {

            case "CHROME":

                WebDriverManager.chromedriver().setup();
                if (headLess.equals("true")) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("headless");
                    //options.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(options);
                    break;
                } else {
                    driver = new ChromeDriver();
                    break;
                }

            case "FIREFOX":

                WebDriverManager.firefoxdriver().setup();
                if (headLess.equals("true")) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.setHeadless(true);
                    options.addArguments("--remote-allow-origins=*");
                    driver = new FirefoxDriver(options);
                    break;
                } else {
                    driver = new FirefoxDriver();
                    break;
                }

            case "MSEDGE":

                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case "MSIE":

                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;

            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        e_driver = new EventFiringWebDriver(driver);
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(GeneralUtility.PAGE_LOAD_TIME));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GeneralUtility.IMPLICIT_WAIT));

        driver.get(properties.getProperty("baseUrl"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GeneralUtility.IMPLICIT_WAIT));
    }

    public String getUsername() {
        return properties.getProperty("username").trim();
    }

    public String getPassword() {
        return properties.getProperty("password").trim();
    }

    public void scrollToElementAndClick(WebElement webElement) {
        webElement.isDisplayed();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", webElement);
        GeneralUtility.waitForDomStable();
        webElement.click();
    }
}
