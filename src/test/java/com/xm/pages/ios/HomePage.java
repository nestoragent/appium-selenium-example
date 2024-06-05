package com.xm.pages.ios;

import com.xm.lib.Init;
import com.xm.lib.Page;
import com.xm.lib.pageFactory.PageEntry;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@PageEntry(title = "Home Page")
public class HomePage extends Page {

    private final String AC_ID_PAGE_HEADER = "CookbookHomefeedHeaderTitle";
    private final String AC_ID_SEARCH_INPUT = "Search Field";
    private final String IOS_CHAIN_RECIPES_INPUT = "**/XCUIElementTypeTextField[`value == \"Search for Recipes\"`]";
    private final String IOS_CHAIN_FIRST_RESULT = "**/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]";

    public WebElement getPageHeader() {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.accessibilityId(AC_ID_PAGE_HEADER)));
    }

    public WebElement getSearchInput() {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.accessibilityId(AC_ID_SEARCH_INPUT)));
    }

    public WebElement getSearchForRecipesInput() {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.iOSClassChain(IOS_CHAIN_RECIPES_INPUT)));
    }

    public HomePage() {
        Assertions.assertTrue(getPageHeader().isDisplayed());
    }

    public void openMenu(String menuName) {
        Init.getIosDriver().findElement(AppiumBy.iOSClassChain(menuName)).click();
    }

    public void search_for(String receiptName) {
        pressButton(getSearchInput());
        fillField(getSearchForRecipesInput(), receiptName);
        pressButton(getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.iOSClassChain(IOS_CHAIN_FIRST_RESULT))));
    }
}
