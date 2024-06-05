package com.xm.pages.ios;

import com.xm.lib.Init;
import com.xm.lib.Page;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;

public abstract class IosAnyPage extends Page {

    public void close_popup() {
        pressButton(getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.iOSClassChain("**/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeButton"))));
    }

    public void scrollToElement(String elementId) {
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("element", elementId);
        scrollObject.put("direction", "down");
        Init.getIosDriver().executeScript("mobile:scroll", scrollObject);
    }

    public void scrollToBottom() {
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("direction", "down");
        Init.getIosDriver().executeScript("mobile:scroll", scrollObject);
    }
}
