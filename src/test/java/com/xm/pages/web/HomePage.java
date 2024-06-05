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
import java.util.List;

@PageEntry(title = "Home")
public class HomePage extends Page {

    @FindBy(css = "div#navigation-collapse a.logo")
    public WebElement divLogo;

    @FindBy(css = "li.main_nav_trading")
    public WebElement liTrading;

    @FindBy(css = "li.menu-stocks > a")
    public WebElement liStocks;

    public HomePage() {
        PageFactory.initElements(Init.getWebDriver(), this);
        new WebDriverWait(Init.getWebDriver(), Duration.ofSeconds(Props.getLong("implicit.wait.duration")))
                .until(ExpectedConditions.visibilityOf(divLogo));
        Assertions.assertTrue(divLogo.isDisplayed());

        List<WebElement> firstCookies = Init.getWebDriver().findElements(By.cssSelector("button[class*=acceptDefaultCookieFirstVisit]"));
        if (!firstCookies.isEmpty()) {
            pressButton(firstCookies.getFirst());
        }
    }

    public void open_menu(String menu) {
        switch (menu) {
            case "Trading":
                pressButton(liTrading);
                break;
            case "Stocks":
                new WebDriverWait(Init.getWebDriver(), Duration.ofSeconds(Props.getLong("implicit.wait.duration")))
                        .until(ExpectedConditions.elementToBeClickable(liStocks));
                pressButton(liStocks);
                break;
            default:
                throw new UnknownError("Unknown case" + menu);
        }
    }

}
