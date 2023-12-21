package com.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InternalPage extends BasePage{
    @FindBy(css = ".footer_copy")
    public WebElement footer;

    @FindBy(css = ".shopping_cart_link")
    public WebElement shoppingCartLink;

    InternalPage() {}
}
