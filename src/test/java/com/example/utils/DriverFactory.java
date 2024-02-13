package com.example.utils;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class DriverFactory {

    public static WebDriver get(Scenario scenario) throws URISyntaxException, MalformedURLException {
        String browser = ConfigurationReader.get("browser");
        WebDriver driver;
        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if (ConfigurationReader.get("headless").toLowerCase().contains("true")) {
                    options.addArguments("--headless");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-infobars");
                    options.addArguments("--disable-popup-blocking");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--lang=en-en");
                }

                if (ConfigurationReader.get("remote_server").toLowerCase().contains("true")) {
                    options.setCapability("platformName", ConfigurationReader.get("remote_server_platform"));
                    options.setCapability("se:name", scenario.getName());
                    driver = new RemoteWebDriver(new URI(ConfigurationReader.get("remote_server_url")).toURL(), options);
                } else {
                    driver = new ChromeDriver(options);
                }
                if (ConfigurationReader.get("maximize").toLowerCase().contains("true")) {
                    driver.manage().window().maximize();
                }
                return driver;
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions options = new FirefoxOptions();

                if (ConfigurationReader.get("headless").toLowerCase().contains("true")) {
                    options.addArguments("--headless");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-infobars");
                    options.addArguments("--disable-popup-blocking");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--lang=en-en");
                }

                if (ConfigurationReader.get("remote_server").toLowerCase().contains("true")) {
                    options.setCapability("platformName", ConfigurationReader.get("remote_server_platform"));
                    options.setCapability("se:name", scenario.getName());
                    driver = new RemoteWebDriver(new URI(ConfigurationReader.get("remote_server_url")).toURL(), options);
                } else {
                    driver = new FirefoxDriver(options);
                }
                if (ConfigurationReader.get("maximize").toLowerCase().contains("true")) {
                    driver.manage().window().maximize();
                }
                return driver;
            }
            case "edge" -> {
                if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                    throw new WebDriverException("Ваша операционная система не поддерживает запуск браузера Edge");
                }
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
        }

        throw new WebDriverException("WebDriver не выбран в конфигурационном файле configuration.properties");
    }
}
