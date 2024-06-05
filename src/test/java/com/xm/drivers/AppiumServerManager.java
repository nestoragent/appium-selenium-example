package com.xm.drivers;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;


public class AppiumServerManager {
    public static AppiumDriverLocalService service;

    public static AppiumDriverLocalService getService() {
        return service;
    }

    public static void startServer(final String platformName) {
        final AppiumServiceBuilder builder = new AppiumServiceBuilder();
        if (platformName.equalsIgnoreCase("android")) {
            builder.withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
                    .withArgument(GeneralServerFlag.USE_DRIVERS, "uiautomator2");
        } else if (platformName.equalsIgnoreCase("ios")) {
            builder.withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
                    .withArgument(GeneralServerFlag.USE_DRIVERS, "xcuitest")
                    .withArgument(GeneralServerFlag.ALLOW_INSECURE, "chromedriver_autodownload");
        }
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public static void stopServer() {
        service.stop();
    }
}
