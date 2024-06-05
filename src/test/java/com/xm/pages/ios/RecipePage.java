package com.xm.pages.ios;

import com.xm.lib.pageFactory.PageEntry;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@PageEntry(title = "Recipe")
public class RecipePage extends IosAnyPage {

    private final String AC_ID_TAB = "%s Tab";
    private final String AC_ID_SHOPPING_LIST = "Shopping List";
    private final String IOS_CHAIN_INGREDIENT = "**/XCUIElementTypeOther[`name == \"Rx Ingredient Item\"`][%s]";
    private final String IOS_CHAIN_INGREDIENT_ADDED = "**/XCUIElementTypeOther[`name == \"Rx Ingredient Item\"`][%s]/XCUIElementTypeButton";

    public WebElement getPageHeader() {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.accessibilityId("Rx Header Title")));
    }

    public WebElement getIngredientElement(String position) {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.iOSClassChain(IOS_CHAIN_INGREDIENT.formatted(position))));
    }

    public RecipePage() {
        Assertions.assertTrue(getPageHeader().isDisplayed());
    }

    public void open_tab(String tabName) {
        pressButton(getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.accessibilityId(AC_ID_TAB.formatted(tabName)))));
    }

    public void add_ingredient_to_the_shopping_list(String position) {
        scrollToBottom();
        pressButton(getIngredientElement(position));
    }

    public void open_shopping_list() {
        pressButton(getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.accessibilityId(AC_ID_SHOPPING_LIST))));
    }

    public void verify_that_the_ingredient_added(String position) {
        scrollToBottom();
        WebElement ingredient = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.iOSClassChain(IOS_CHAIN_INGREDIENT_ADDED.formatted(position))));
        Assertions.assertTrue(ingredient.getAttribute("label").contains("remove"));
    }

}
