package com.sjsu.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class TestPlantum {

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
            caps.setCapability("appPackage", "plant.identification.flower.tree.leaf.identifier.identify.cat.dog.breed.nature");
            caps.setCapability("appActivity", "org.bpmobile.wtplant.app.view.activity.main.MainActivity");
            caps.setCapability("noReset", "true");  // Prevents clearing app data upon startup




            URL url = new URL("http://0.0.0.0:4723/wd/hub");

            driver = new AppiumDriver<>(url, caps);
            System.out.println("Connection successful!");

            Thread.sleep(5000);
            dismissScreen();
            Thread.sleep(5000);
            dismissScreen();

            setUpHelper();



        } catch (Exception e) {
            System.out.println("Error connecting to Appium server: " + e.getMessage());
        }
    }

    private void dismissScreen() {
        try {
            MobileElement closeButton = (MobileElement) driver.findElementById("plant.identification.flower.tree.leaf.identifier.identify.cat.dog.breed.nature:id/close");
            if (closeButton.isDisplayed()) {
                closeButton.click();
                System.out.println("Screen dismissed.");
            }
        } catch (Exception e) {
            System.out.println("No screen to dismiss.");
        }
    }


    private void setUpHelper() throws Exception {


        // Define the XPath for the TextView that should contain "plant-photos" to check to see if we are already in the directory
        String titleXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView";


        Thread.sleep(3000);
        // Press scan button



        try {
            MobileElement greenButton = (MobileElement) driver.findElementById("plant.identification.flower.tree.leaf.identifier.identify.cat.dog.breed.nature:id/action_top_right");
            greenButton.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }

        Thread.sleep(3000);

        // Press gallery button
        try {
            MobileElement gallery = (MobileElement) driver.findElementById("plant.identification.flower.tree.leaf.identifier.identify.cat.dog.breed.nature:id/last_image_button_container");
            gallery.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }

        Thread.sleep(1000);

        // Get the title directory's name to check to see if we are already in "plant-photos"
        MobileElement titleElement = (MobileElement) driver.findElement(By.xpath(titleXPath));
        String pageTitle = titleElement.getText();

        // If we are in plant-photos, skip navigation
        if (pageTitle.equalsIgnoreCase("plant-photos")) {
            System.out.println("Already in the 'plant-photos' directory, skipping navigation.");
            return; // Exit the method early
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
            MobileElement sdkGallery = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[4]\n"));
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
}
