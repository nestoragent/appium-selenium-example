package com.xm.pages.web;

import com.xm.lib.Init;
import com.xm.lib.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebAnyPage extends Page {

    public void pressByActions(WebElement clickElement) {
        Actions actions = new Actions(Init.getWebDriver());
        actions.moveToElement(clickElement).click().build().perform();
    }

    public void scrollTo(WebElement element) {
        ((JavascriptExecutor) Init.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickByJs(WebElement element) {
        ((JavascriptExecutor) Init.getWebDriver()).executeScript("arguments[0].click();", element);
    }

    public void scrollToByActions(WebElement element) {
        Actions actions = new Actions(Init.getWebDriver());
        actions.moveToElement(element).build().perform();
    }
}
