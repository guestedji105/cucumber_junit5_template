package com.example.steps;

import com.example.context.Book;
import com.example.context.TestContext;
import com.example.pages.LoginPage;
import com.example.pages.MainPage;
import com.example.utils.ConfigurationReader;
import com.example.utils.DriverFactory;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleSteps {
    TestContext context;

    Scenario scenario;

    @Before
    public void beforeMethod(Scenario scenario) {
        context = new TestContext();
        context.driver = DriverFactory.get();
        context.wait = new WebDriverWait(context.driver, Duration.ofSeconds(Long.parseLong(ConfigurationReader.get("timeout"))));
        context.actions = new Actions(context.driver);
        context.js = (JavascriptExecutor) context.driver;
        context.driver.get(ConfigurationReader.get("base_url"));
        this.scenario = scenario;
    }

    @After
    public void afterMethod(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) context.driver;

            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", "screenshot");
        }
        if (context.driver != null) {
            context.driver.quit();
        }
    }

    @BeforeStep
    public void beforeEveryStep() {
        scenario.log("Current URL: " + context.driver.getCurrentUrl());
    }

    @Given("user enter login page")
    public void user_enter_login_page() {
        scenario.log("Entered login page");
    }

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        LoginPage lp = new LoginPage(context);
        lp.usernameInput.sendKeys(ConfigurationReader.get("standard_login"));
        lp.passwordInput.sendKeys(ConfigurationReader.get("password"));
    }

    @When("clicks login button")
    public void clicks_login_button() {
        LoginPage lp = new LoginPage(context);
        lp.loginButton.click();
    }

    @When("user adds first product to the cart")
    public void user_adds_first_product_to_the_cart() {
        MainPage mp = new MainPage(context);
        mp.addToCartButtons.getFirst().click();
    }

    @Then("amount of products in the cart is {int}")
    public void amount_of_products_in_the_cart_is(Integer amount) {
        assertEquals(Integer.parseInt(new MainPage(context).shoppingCartLink.getText()), amount);
    }

    @Then("main page opens")
    public void main_page_opens() {
        MainPage mp = new MainPage(context);
        assertTrue(mp.firstDescriptionContainer.isDisplayed());
    }

    @Then("it have text in footer {string}")
    public void it_have_text_in_footer(String expectedText) {
        MainPage mp = new MainPage(context);
        assertTrue(mp.footer.getText().contains(expectedText));
    }

    @DataTableType
    public Book bookEntryTransformer(Map<String, String> row) {

        return new Book(
                row.get("title"),
                row.get("author"),
                Integer.parseInt(row.get("yearOfPublishing"))
        );
    }

    @Given("the following books")
    public void theFollowingBooks(List<Book> books) {

        for (Book book : books) {
            System.out.printf(
                    "'%s', published in %d, was written by %s\n",
                    book.title,
                    book.yearOfPublishing,
                    book.author
            );
        }
    }

    @When("user enters login {word} and password {string}")
    public void userEntersLoginAndPassword(String login, String password) {
        LoginPage lp = new LoginPage(context);
        lp.usernameInput.sendKeys(login);
        lp.passwordInput.sendKeys(password);
    }

    @Then("error message contains text {string}")
    public void errorMessageContainsText(String expectedErrorMessage) {
        String actualText = new LoginPage(context).loginMessageContainer.getText();
        scenario.log(String.format("expectedText: %s;\r\nactualText: %s", expectedErrorMessage, actualText));
        assertTrue(actualText.contains(expectedErrorMessage));
    }

    @Given("^the following table$")
    public void theFollowingTable(Map<String, String> dataTable) {
        dataTable = processDataTable(dataTable);
        scenario.log(dataTable.toString());
    }

    public Map<String, String> processDataTable(Map<String, String> dataTable) {
        Map<String, String> processedData = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : dataTable.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            processedData.put(key, processPlaceholder(value));
        }
        return processedData;
    }

    private String processPlaceholder(String placeholder) {
        return switch (placeholder.toLowerCase()) {
            case "today" -> LocalDate.now().toString();
            case "randomnumber" -> String.valueOf(new Random().nextInt(1,100));
            case "emptystring" -> "";
            case "null" -> null;
            case "textofelement" -> context.driver.findElement(By.className("login_logo")).getText();
            default -> placeholder;
        };
    }
}
