package com.example.pages;
import com.example.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends InternalPage {
    @FindBy(css = ".btn_inventory")
    public List<WebElement> addToCartButtons;

    @FindBy(css = ".inventory_item_desc")
    public WebElement firstDescriptionContainer;

    public MainPage(TestContext context) {
        super(context);
    }
}
