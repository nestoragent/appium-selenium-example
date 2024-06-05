### Test examples

To run IOS test:
1. Get the real device
2. Configure the real device by step:
- https://github.com/appium/appium-xcuitest-driver/blob/master/docs/preparation/real-device-config.md
3. Setup Yummly app from App Store
4. Update params in the config file src/test/resources/config/ios.properties for your device
```
device.name
platform.version
```
6. Execute maven command
```
mvn clean test -Dgroups=IOS -DconfigFile=config/ios.properties
```


To run Web test:
1. Install chrome version ~125
2. Execute maven commands to run in diff screen resolution:
```
mvn clean test -Dgroups=Web -DconfigFile=config/web-max.properties
mvn clean test -Dgroups=Web -DconfigFile=config/web-1024.properties
mvn clean test -Dgroups=Web -DconfigFile=config/web-800.properties
```