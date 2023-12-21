package com.example.steps;

import com.example.context.Context;
import com.example.utils.ConfigurationReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.example.context.Context.closeDriver;
import static com.example.context.Context.getDriver;

public class Hooks {
    @Before
    public void beforeMethod(Scenario scenario) {
        getDriver().get(ConfigurationReader.get("base_url"));
        Context.scenario = scenario;
    }

    @After
    public void afterMethod(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) getDriver();

            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", "screenshot");
        }
        if (getDriver() != null) {
            closeDriver();
        }
    }
}
