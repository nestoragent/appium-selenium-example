package com.xm.drivers;

import com.xm.lib.Init;
import com.xm.lib.Props;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;

import java.nio.file.Path;
import java.time.Duration;

import static com.xm.drivers.AppiumServerManager.*;

public class IOSDriverManager {

    private static XCUITestOptions xcuiTestOptions() {
        return new XCUITestOptions()
                .setDeviceName(Props.get("device.name"))
                .setPlatformVersion(Props.get("platform.version"))
                .setUdid(Props.get("uuid"))
                .setBundleId(Props.get("bundle.id"))
                .setAutomationName(AutomationName.IOS_XCUI_TEST)
                .setNewCommandTimeout(Duration.ofSeconds(60))
                .setUpdatedWdaBundleId("")
                .setNoReset(true)
                .showXcodeLog();
    }

    public static void quitSession() {
        if (null != Init.getIosDriver()) {
            System.out.println("Closing the driver...");
            Init.getIosDriver().quit();
            stopServer();
        }
    }

    public static void createIOSDriver() {
        XCUITestOptions data = xcuiTestOptions();
        startServer("ios");
        Init.setMobileDriver(new IOSDriver(getService().getUrl(), data));
        setupDriverTimeouts();
    }

    private static void setupDriverTimeouts() {
        Init.getIosDriver().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
    }
}
