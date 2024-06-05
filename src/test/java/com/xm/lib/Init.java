package com.xm.lib;

import com.xm.drivers.IOSDriverManager;
import com.xm.lib.pageFactory.PageFactory;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by VelichkoAA on 12.01.2016.
 */
public class Init {

    private static final Map<String, Object> stash = new HashMap<>();
    private static WebDriver webDriver;
    private static IOSDriver iosDriver;
    private static PageFactory pageFactory;
    private static String testInstrument = Props.get("automation.instrument");
    private static String testPlatform = Props.get("automation.platform");

    public static WebDriver getWebDriver() {
        if (null == webDriver) {
            try {
                createWebDriver();
            } catch (UnreachableBrowserException e) {
                System.err.println("Failed to create web driver" + e.getMessage());
            }
        }
        return webDriver;
    }

    public static IOSDriver getIosDriver() {
        if (null == iosDriver) {
            try {
                createMobileDriver();
            } catch (UnreachableBrowserException e) {
                System.err.println("Failed to create web driver" + e.getMessage());
            }
        }
        return iosDriver;
    }

    public static void setWebDriver(WebDriver webDriver) {
        Init.webDriver = webDriver;
    }

    public static void setMobileDriver(IOSDriver driver) {
        Init.iosDriver = driver;
    }

    public static PageFactory getPageFactory() {
        if (null == pageFactory) {
            pageFactory = new PageFactory("%s.%s".formatted(Props.get("automation.pages"), testPlatform));
        }
        return pageFactory;
    }

    public static void createWebDriver() {
        if (null == testInstrument) {
            testInstrument = "Chrome";
        }

        switch (TestInstrumentEnum.valueOf(testInstrument)) {
            case Chrome:
                WebDriverManager.chromedriver().browserVersion(Props.get("browser.version")).setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("enable-automation");
                options.addArguments("no-sandbox");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-browser-side-navigation");
                options.addArguments("--disable-gpu");
                setWebDriver(new ChromeDriver(options));
                break;
            default:
                System.err.println("Unknown test instrument: " + testInstrument);
                break;
        }

        getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(Props.get("implicit.wait.duration"))));
        getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(Props.get("implicit.wait.duration"))));
        getWebDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(Long.parseLong(Props.get("implicit.wait.duration"))));


        if (Props.get("browser.width").equals("maximum") || Props.get("browser.height").equals("maximum")) {
            getWebDriver().manage().window().maximize();
        } else {
            getWebDriver().manage().window().setSize(
                    new Dimension(Integer.parseInt(Props.get("browser.width")),
                            Integer.parseInt(Props.get("browser.height"))));
        }
    }

    public static void createMobileDriver() {
        if (null == testPlatform) {
            testPlatform = "ios";
        }

        switch (TestPlatformEnum.valueOf(testPlatform)) {
            case ios:
                IOSDriverManager.createIOSDriver();
                break;
            default:
                System.err.println("Unknown test instrument");
                break;
        }

    }

    public static void dispose() {
        try {
            switch (TestPlatformEnum.valueOf(testPlatform)) {
                case web:
                    webDriver.quit();
                    break;
                case ios:
                    IOSDriverManager.quitSession();
                    break;
                default:
                    System.err.println("Unknown test instrument");
                    break;
            }
        } catch (Exception | Error e) {
            System.err.println("Failed to quit web driver. Error message = " + e.getMessage());
        }
        setWebDriver(null);
        pageFactory = null;
    }

    public static Map<String, Object> getStash() {
        return stash;
    }

}
