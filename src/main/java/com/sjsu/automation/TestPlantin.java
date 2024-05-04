package com.sjsu.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.*;
import java.net.URL;
import org.openqa.selenium.By;


public class TestPlantin {

    AppiumDriver<MobileElement> driver;

    @Before
    //Handles inital set up for plantin
    public void setup() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            caps.setCapability("appPackage", "com.myplantin.app");
            caps.setCapability("appActivity", "com.myplantin.app.presentation.ui.activity.MainActivity");
            caps.setCapability("noReset", "true");  // Prevents clearing app data upon startup



            URL url = new URL("http://0.0.0.0:4723/wd/hub");

            driver = new AppiumDriver<>(url, caps);
            System.out.println("Connection successful!");

            Thread.sleep(5000);
            dismissScreen();


        } catch (Exception e) {
            System.out.println("Error connecting to Appium server: " + e.getMessage());
        }
    }

    /** Dismisses a generic pop-up screen if it is displayed
     */
    private void dismissScreen() {
        try {
            MobileElement closeButton = (MobileElement) driver.findElementById("com.myplantin.app:id/btnClose");
            if (closeButton.isDisplayed()) {
                closeButton.click();
                System.out.println("Screen dismissed.");
            }
        } catch (Exception e) {
            System.out.println("No screen to dismiss.");
        }
    }


    private void setUpHelper() throws Exception {
        Thread.sleep(3000);
        // Press scan button
        try {
            MobileElement greenButton = (MobileElement) driver.findElementById("com.myplantin.app:id/btnScan");
            greenButton.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        // Press gallery button
        try {
            MobileElement gallery = (MobileElement) driver.findElementById("com.myplantin.app:id/btnGallery");
            gallery.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        Thread.sleep(1000);
        // Press hamburger menu button
        try {
            MobileElement hamburgerMenu = (MobileElement) driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Show roots']"));
            hamburgerMenu.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        Thread.sleep(1000);
        // SDK Drive
        try {
            MobileElement sdkGallery = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]\n"));
            sdkGallery.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        Thread.sleep(1000);
        // Pictures Directory
        try {
            MobileElement picturesButton = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[10]\n"));
            picturesButton.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        Thread.sleep(1000);
        //Plant Pictures Directory
        try {
            MobileElement plantPicturesButton = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout\n"));
            plantPicturesButton.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        Thread.sleep(1000);
    }

    @Test
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (1).png, 2.49 MB, Apr 23\"]"));
            plantPicture.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        Thread.sleep(6000); // Allow time for scan to occur
        try {
            // Get the text from the planeName element
            MobileElement plantNameElement = (MobileElement) driver.findElementById("com.myplantin.app:id/tvPlantName");
            String plantName = plantNameElement.getText();
            System.out.println("Found plant name: " + plantName);

            // Assert that the plant name is "Sunflower"
            Assert.assertEquals("Sunflower", plantName);
            System.out.println("Test passed: The plant name is correctly 'Sunflower'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    public void testCase2() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (10).png, 1.09 MB, Apr 23\"]"));
            plantPicture.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        Thread.sleep(5000); // Allow time for scan to occur
        try {
            // Get the text from the planeName element
            MobileElement plantNameElement = (MobileElement) driver.findElementById("com.myplantin.app:id/tvPlantName");
            String plantName = plantNameElement.getText();
            System.out.println("Found plant name: " + plantName);

            // Assert that the plant name is "Tiger Tooth Aloe"
            Assert.assertEquals("Tiger Tooth Aloe", plantName);
            System.out.println("Test passed: The plant name is correctly 'Tiger Tooth Aloe'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

//    @After
//    public void testClosingLoginScreen() {
//        System.out.println("The app should now be open on the device.");
//
//        // Assuming the 'X' button has an ID 'btnClose'
//        try {
//            MobileElement closeButton = driver.findElementById("com.myplantin.app:id/btnClose");
//            closeButton.click();
//            System.out.println("Clicked on the 'X' button to close the login screen.");
//
//            // Optional: Add assertions or checks to confirm the new screen is displayed after the click
//        } catch (Exception e) {
//            System.out.println("Failed to click on 'X' button: " + e.getMessage());
//        }
//    }


//    @After
//    public void tearDown(){
//        if (driver != null){
//            driver.quit();
//        }
//    }
}
