package com.example.steps;

import com.example.pages.LoginPage;
import com.example.utils.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.example.context.Context.scenario;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        LoginPage lp = new LoginPage();
        lp.usernameInput.sendKeys(ConfigurationReader.get("standard_login"));
        lp.passwordInput.sendKeys(ConfigurationReader.get("password"));
    }

    @When("clicks login button")
    public void clicks_login_button() {
        LoginPage lp = new LoginPage();
        lp.loginButton.click();
    }

    @When("user enters login {word} and password {string}")
    public void userEntersLoginAndPassword(String login, String password) {
        LoginPage lp = new LoginPage();
        lp.usernameInput.sendKeys(login);
        lp.passwordInput.sendKeys(password);
    }

    @Then("error message contains text {string}")
    public void errorMessageContainsText(String expectedErrorMessage) {
        String actualText = new LoginPage().loginMessageContainer.getText();
        scenario.log(String.format("expectedText: %s;\r\nactualText: %s", expectedErrorMessage, actualText));
        assertTrue(actualText.contains(expectedErrorMessage));
    }
}
