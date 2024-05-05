package com.sjsu.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.*;
import java.net.URL;

//Impossible to tes tPictureThis since app's activity is not exported
public class TestPictureThis {

    AppiumDriver<MobileElement> driver;

    @Test
    //Handles inital set up for plantin
    public void setup() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            caps.setCapability("appPackage", "cn.danatech.xingseus");
            caps.setCapability("appActivity", "com.glority.picturethis.app.kt.view.home.MainActivity");
            caps.setCapability("noReset", "true");  // Prevents clearing app data upon startup




            URL url = new URL("http://0.0.0.0:4723/wd/hub");

            driver = new AppiumDriver<>(url, caps);
            System.out.println("Connection successful!");

            Thread.sleep(5000);
            //dismissScreen();


        } catch (Exception e) {
            System.out.println("Error connecting to Appium server: " + e.getMessage());
        }
    }



}
