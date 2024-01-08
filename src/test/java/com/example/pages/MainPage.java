package com.example.pages;
import com.example.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends InternalPage {
    @FindBy(css = ".btn_inventory")
    public List<WebElement> addToCartButtons;

    @FindBy(xpath = "//div[@class='inventory_item_label']/a/div")
    public List<WebElement> listProductName;

    @FindBy(css = ".inventory_item_desc")
    public WebElement firstDescriptionContainer;

    @FindBy(xpath = "//*[@data-icon='shopping-cart']")
    public WebElement buttonCart;

    public void addToCartByPartialName(String namePart) {
        listProductName.forEach(product -> {
            if (product.getText().contains(namePart)) {
                addToCartButtons.get(listProductName.indexOf(product)).click();
            }
        });
    }

    public MainPage(TestContext context) {
        super(context);
    }
}
