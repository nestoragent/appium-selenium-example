package com.xm.pages.ios;

import com.xm.lib.Page;
import com.xm.lib.pageFactory.PageEntry;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@PageEntry(title = "Search Result")
public class SearchResultPage extends Page {

    private final String AC_ID_PAGE_HEADER = "Search Content View";
    private final String IOS_CHAIN_RECIPE = "**/XCUIElementTypeOther[`label == \"Results View\"`]/XCUIElementTypeCollectionView/XCUIElementTypeCell[%s]";

    public WebElement getPageHeader() {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.accessibilityId(AC_ID_PAGE_HEADER)));
    }

    public SearchResultPage() {
        Assertions.assertTrue(getPageHeader().isDisplayed());
    }

    public void open_recipe(String receiptPosition) {
        pressButton(getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.iOSClassChain(IOS_CHAIN_RECIPE.formatted(receiptPosition)))));
    }

}
