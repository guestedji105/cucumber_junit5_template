package com.example.pages;

import com.example.context.Context;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public BasePage() {
        PageFactory.initElements(Context.getDriver(), this);
    }
}