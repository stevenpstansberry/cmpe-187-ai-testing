package com.sjsu.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class PlantAppsTest {

    private AndroidDriver<MobileElement> driver;

    @Before
    public void setUp() throws Exception {
        // This method will be executed before each Test.
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability("noReset", "true");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    }

    @Test
    public void testPlantumApp() throws Exception {
        // Specific setup for Plantum
        driver.startActivity(new Activity("com.plantum.app", "MainActivity"));
        // Your test code here
    }

    @Test
    public void testPlantInApp() throws Exception {
        // Specific setup for PlantIn
        driver.startActivity(new Activity("com.plantin.app", "MainActivity"));
        // Your test code here
    }

    @Test
    public void testPlantSnapApp() throws Exception {
        // Specific setup for PlantSnap
        driver.startActivity(new Activity("com.plantsnap.app", "MainActivity"));
        // Your test code here
    }

    @Test
    public void testPictureThisApp() throws Exception {
        // Specific setup for PictureThis
        driver.startActivity(new Activity("com.picturethis.app", "MainActivity"));
        // Your test code here
    }
}

