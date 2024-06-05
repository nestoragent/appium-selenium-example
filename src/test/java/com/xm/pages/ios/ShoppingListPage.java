package com.xm.pages.ios;

import com.xm.lib.pageFactory.PageEntry;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@PageEntry(title = "Shopping List")
public class ShoppingListPage extends IosAnyPage {

    private final String AC_ID_PAGE_HEADER = "Header Title";
    private final String AC_ID_RECIPES_TAB = "RECIPES";
    private final String IOS_CHAIN_RECIPE = "**/XCUIElementTypeOther[`name CONTAINS[cd] \"%s\"`]";

    public WebElement getPageHeader() {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.accessibilityId(AC_ID_PAGE_HEADER)));
    }

    public ShoppingListPage() {
        Assertions.assertEquals("Shopping List", getPageHeader().getText());
    }

    public void expand_more_actions_for(String ingredient) {
        WebElement parent = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.accessibilityId(ingredient))).findElement(AppiumBy.xpath("./.."));
        WebElement button = parent.findElement(AppiumBy.xpath("./XCUIElementTypeButton[2]"));
        pressButton(button);
    }

    public void open_recipes_tab() {
        pressButton(getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId(AC_ID_RECIPES_TAB))));
    }

    public void open_recipe(String recipe) {
        pressButton(getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.iOSClassChain(IOS_CHAIN_RECIPE.formatted(recipe)))));
    }
}
