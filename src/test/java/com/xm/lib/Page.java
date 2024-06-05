package com.xm.lib;

import com.xm.lib.pageFactory.PageEntry;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;

public class Page {

    /**
     * <p>
     * getTitle.</p>
     *
     * @return a {@link String} object.
     */
    public String getTitle() {
        return this.getClass().getAnnotation(PageEntry.class).title();
    }

    public void takeAction(String action, Object... param) throws Throwable {
        action = action.replaceAll(" ", "_");
        try {
            MethodUtils.invokeMethod(this, action, param);
        } catch (NoSuchMethodException e) {
            StringBuilder sb = new StringBuilder();

            sb.append("There is no \"").append(action).append("\" action ")
                    .append("in ")
                    .append(this.getTitle()).append(" page object")
                    .append("\n");
            sb.append("Possible actions are:")
                    .append("\n");
            Class tClass = this.getClass();
            Method[] methods = tClass.getDeclaredMethods();
            for (Method method : methods) {
                sb.append("\t\"")
                        .append(this.getTitle()).append("\"->\"")
                        .append(method.getName()).append("\" with ")
                        .append(method.getGenericParameterTypes().length)
                        .append(" input parameters").append("\n");
            }
            throw new NoSuchMethodException(sb.toString());
        } catch (InvocationTargetException ex) {
            throw ex.getCause();
        }
    }

    protected WebDriverWait getWebDriverWait() {
        return new WebDriverWait(Init.getIosDriver(), Duration.ofSeconds(Props.getLong("implicit.wait.duration")));
    }


    /**
     * Common method for both mobile and web
     * @param webElement
     */
    public void pressButton(WebElement webElement) {
        webElement.click();
    }

    public void fillField(WebElement element, String text) {
        try {
            element.clear();
        } catch (InvalidElementStateException e) {
            System.err.println("Failed to clear web element. Error message = " + e.getMessage());
        }
        element.sendKeys(text);
    }
}
