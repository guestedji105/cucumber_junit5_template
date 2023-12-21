package com.example.steps;

import com.example.pages.MainPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageSteps {
    @When("user adds first product to the cart")
    public void user_adds_first_product_to_the_cart() {
        MainPage mp = new MainPage();
        mp.addToCartButtons.getFirst().click();
    }

    @Then("amount of products in the cart is {int}")
    public void amount_of_products_in_the_cart_is(Integer amount) {
        assertEquals(Integer.parseInt(new MainPage().shoppingCartLink.getText()), amount);
    }

    @Then("main page opens")
    public void main_page_opens() {
        MainPage mp = new MainPage();
        assertTrue(mp.firstDescriptionContainer.isDisplayed());
    }

    @Then("it have text in footer {string}")
    public void it_have_text_in_footer(String expectedText) {
        MainPage mp = new MainPage();
        assertTrue(mp.footer.getText().contains(expectedText));
    }
}
