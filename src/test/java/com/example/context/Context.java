package com.example.context;

import com.example.utils.ConfigurationReader;
import com.example.utils.DriverFactory;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Context {
    private Context(){}

    private static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;
    public static JavascriptExecutor js;

    public static Scenario scenario;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = DriverFactory.get();
            wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigurationReader.get("timeout"))));
            actions = new Actions(driver);
            js = (JavascriptExecutor) driver;
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
