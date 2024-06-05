package com.xm.pages.ios;

import com.xm.lib.Page;
import com.xm.lib.pageFactory.PageEntry;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@PageEntry(title = "Sign In")
public class SignInPage extends Page {

    private final String AC_ID_PAGE_HEADER = "Sign in or create a free profile";
    private final String IOS_CHAIN_BUTTON_DO_IT_LATER = "**/XCUIElementTypeStaticText[`label == \"I'll do it later\"`]";

    public WebElement getPageHeader() {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.accessibilityId(AC_ID_PAGE_HEADER)));
    }

    public WebElement getBtnIllDoItLater() {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.iOSClassChain(IOS_CHAIN_BUTTON_DO_IT_LATER)));
    }

    public SignInPage() {
        Assertions.assertEquals("Sign in or create a free profile", getPageHeader().getText());
    }

    public void click_on_the_bottom_button_do_it_later() {
        pressButton(getBtnIllDoItLater());
    }


}
