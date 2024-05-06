package com.sjsu.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.*;
import java.net.URL;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;
import org.openqa.selenium.By;
public class TestPlantSnap extends PlantTest{


    @Override
    public String getAppPackage() {
        return "com.fws.plantsnap2";
    }

    @Override
    public String getAppActivity() {
        return "com.fws.plantsnap2.MainActivity";
    }

    @Override
    protected void dismissScreen() {
        try {
            System.out.println("Attempt to dismiss");

            try {
                MobileElement skipButton = (MobileElement) driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Skip\"]\n"));

                if (skipButton.isDisplayed()) {
                    skipButton.click();
                    System.out.println("Screen dismissed.");
                }
            } catch (Exception e){
                System.out.println("No Skip Button Found");
            }
            try {
                MobileElement cancelButton = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button"));

                if (cancelButton.isDisplayed()) {
                    cancelButton.click();
                    System.out.println("Screen dismissed.");
                }
            } catch (Exception e){
                System.out.println("No Cancel Button Found");
            }
        } catch (Exception e) {
            System.out.println("No screen to dismiss.");
        }
    }

    @Override
    protected void setUpHelper() throws Exception {


        Thread.sleep(3000);
        // Press scan button
        try {
            MobileElement greenButton = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[3]\n"));
            greenButton.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        // Press gallery button

        try {
            MobileElement gallery = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[5]\n"));
            gallery.click();
            System.out.println("Gallery Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }

        Thread.sleep(1000);

        try {
        // Get the title directory's name to check to see if we are already in "plant-photos"
        MobileElement titleElement = (MobileElement) driver.findElement(By.xpath(titleXPath));
        String pageTitle = titleElement.getText();

        // If we are in plant-photos, skip navigation
        if (pageTitle.equalsIgnoreCase("plant-photos")) {
            System.out.println("Already in the 'plant-photos' directory, skipping navigation.");
            return; // Exit the method early
        }} catch (Exception e){
            System.out.println("Not in Directory");
        }
    }

    @Test
    public void initialize() throws Exception {
        setup();
        Thread.sleep(1500);
        dismissScreen();
        Thread.sleep(1500);
        dismissScreen();
        setUpHelper();

    }


}
