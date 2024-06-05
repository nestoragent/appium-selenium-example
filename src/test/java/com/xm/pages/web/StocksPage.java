package com.xm.pages.web;

import com.xm.lib.Init;
import com.xm.lib.Page;
import com.xm.lib.Props;
import com.xm.lib.pageFactory.PageEntry;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@PageEntry(title = "Stocks")
public class StocksPage extends Page {

    private final static String CSS_BUTTON_FILTER = "button[data-value='%s']";

    @FindBy(css = "li.active > a[href*='/stocks']")
    public WebElement buttonStocksActive;

    public StocksPage() {
        System.out.println("Web StocksPage");
        PageFactory.initElements(Init.getWebDriver(), this);
        new WebDriverWait(Init.getWebDriver(), Duration.ofSeconds(Props.getLong("implicit.wait.duration")))
                .until(ExpectedConditions.visibilityOf(buttonStocksActive));
    }

    public void choose_stock_filter(String filter) {
        new WebDriverWait(Init.getWebDriver(), Duration.ofSeconds(Props.getLong("implicit.wait.duration")))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CSS_BUTTON_FILTER.formatted(filter))));

        pressButton(Init.getWebDriver().findElement(By.cssSelector(CSS_BUTTON_FILTER.formatted(filter))));
        String classes = Init.getWebDriver().findElement(By.cssSelector(CSS_BUTTON_FILTER.formatted(filter))).getAttribute("class");
        System.out.println("classes: " + classes);
        Assertions.assertTrue(classes.contains("active"));
    }

}
