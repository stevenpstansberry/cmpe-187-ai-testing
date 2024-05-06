package com.sjsu.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class TestPlantum {

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
            caps.setCapability("appPackage", "plant.identification.flower.tree.leaf.identifier.identify.cat.dog.breed.nature");
            caps.setCapability("appActivity", "org.bpmobile.wtplant.app.view.activity.main.MainActivity");
            caps.setCapability("noReset", "true");  // Prevents clearing app data upon startup




            URL url = new URL("http://0.0.0.0:4723/wd/hub");

            driver = new AppiumDriver<>(url, caps);
            System.out.println("Connection successful!");

            Thread.sleep(1000);
            dismissScreen();
            dismissScreen();



        } catch (Exception e) {
            System.out.println("Error connecting to Appium server: " + e.getMessage());
        }
    }

    private void dismissScreen() {
        try {
            System.out.println("Attempt to dismiss");

            try {
                MobileElement closeButton = (MobileElement) driver.findElementById("plant.identification.flower.tree.leaf.identifier.identify.cat.dog.breed.nature:id/close");
                if (closeButton.isDisplayed()) {
                    closeButton.click();
                    System.out.println("Screen dismissed.");
                }
            } catch (Exception e){
                System.out.println("No close button found.");
            }
            try {
                MobileElement cancelButton = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/android.widget.ImageView[1]\n"));
                cancelButton.click();
                System.out.println("Screen dismissed.");
            } catch (Exception e){
                System.out.println("No cancel button found.");
            }

        } catch (Exception e) {
            System.out.println("No screen to dismiss.");
        }
    }

    private void scrollDown() {
        // Dimensions of the screen
        int screenHeight = driver.manage().window().getSize().height;
        int screenWidth = driver.manage().window().getSize().width;

        // Starting and ending Y coordinates
        int start_y = (int) (screenHeight * 0.8);
        int end_y = (int) (screenHeight * 0.2);

        // X coordinate remains the same (middle of the screen)
        int x = screenWidth / 2;

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }


    private void setUpHelper() throws Exception {


        // Define the XPath for the TextView that should contain "plant-photos" to check to see if we are already in the directory
        String titleXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView";


        Thread.sleep(2000);
        // Press scan button



        try {
            MobileElement greenButton = (MobileElement) driver.findElementById("plant.identification.flower.tree.leaf.identifier.identify.cat.dog.breed.nature:id/action_top_right");
            greenButton.click();
            System.out.println("Begin Scan Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }

        Thread.sleep(3000);

        // Press gallery button
        try {
            MobileElement gallery = (MobileElement) driver.findElementById("plant.identification.flower.tree.leaf.identifier.identify.cat.dog.breed.nature:id/last_image_button_container");
            gallery.click();
            System.out.println("Gallery Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }

        Thread.sleep(2000);

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



    @Test
    /**
     * Corresponds to Test Case ID#2
     * Context Case #C11 (Far)
     * Input Case #I1
     * Expected: Sunflower
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (1).png, 2.49 MB, Apr 23\"]"));
            plantPicture.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        Thread.sleep(1500); // Allow time for image to be loaded into app

        try {
            MobileElement confirmButton = (MobileElement) driver.findElementById("plant.identification.flower.tree.leaf.identifier.identify.cat.dog.breed.nature:id/crop_button");
            confirmButton.click();
        } catch (Exception e){
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }

        Thread.sleep(1500); // Allow time for scan to occur

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
    /**
     * Corresponds to Test Case ID#11
     * Context Case #C11 (Far)
     * Input Case #I1
     * Expected: Tiger Tooth Aloe
     */
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
    @Test
    /**
     * Corresponds to Test Case ID#11
     * Context Case #C10 (Optimal)
     * Input Case #I1
     * Expected: Tiger Tooth Aloe
     */
    public void testCase3() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (11).png, 1.13 MB, Apr 23\"]\n"));
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

    @Test
    /**
     * Corresponds to Test Case ID#14
     * Context Case #C12
     * Input Case #I3
     * Expected: California Poppy
     */
    public void testCase4() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (12).png, 592 kB, Apr 23\"]\n"));
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

            // Assert that the plant name is "California Poppy"
            Assert.assertEquals("California Poppy", plantName);
            System.out.println("Test passed: The plant name is correctly 'California Poppy'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#15
     * Context Case #C2
     * Input Case #I4
     * Expected: Balloon Flower OR Platycodon
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (13).png, 737 kB, Apr 23\"]"));
            plantPicture.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        Thread.sleep(5000); // Allow time for scan to occur
        try {
            // Get the text from the plantName element
            MobileElement plantNameElement = (MobileElement) driver.findElementById("com.myplantin.app:id/tvPlantName");
            String plantName = plantNameElement.getText();
            System.out.println("Found plant name: " + plantName);

            // Check if the plant name is "Balloon Flower" or "Platycodon"
            boolean isCorrectName = "Balloon Flower".equals(plantName) || "Platycodon".equals(plantName);
            Assert.assertTrue("Test failed: The plant name should be 'Balloon Flower' or 'Platycodon'. Found: " + plantName, isCorrectName);
            System.out.println("Test passed: The plant name is correctly '" + plantName + "'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#13
     * Context Case #C2
     * Input Case #I2
     * Expected: Echeveria pulidonis
     */
    public void testCase6() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (14).png, 383 kB, Apr 23\"]"));
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

            // Assert that the plant name is "Echeveria"
            Assert.assertEquals("Echeveria pulidonis", plantName);
            System.out.println("Test passed: The plant name is correctly 'Echeveria pulidonis'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#6
     * Context Case #C2 (Far)
     * Input Case #I5
     * Expected: Sunflower
     */
    public void testCase7() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (15).png, 0.91 MB, Apr 23\"]"));
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
    /**
     * Corresponds to Test Case ID#7
     * Context Case #C2 (Far)
     * Input Case #I6
     * Expected: Water Lily
     */
    public void testCase8() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (16).png, 1.26 MB, Apr 23\"]\n"));
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

            // Assert that the plant name is "Water Lily"
            Assert.assertEquals("Water Lily", plantName);
            System.out.println("Test passed: The plant name is correctly 'Water Lily'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }
    @Test
    /**
     * Corresponds to Test Case ID#8
     * Context Case #C2 (Far)
     * Input Case #I7
     * Expected: Water Lily
     */
    public void testCase9() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (17).png, 1.75 MB, Apr 23\"]"));
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

            // Assert that the plant name is "Water Lily"
            Assert.assertEquals("Water Lily", plantName);
            System.out.println("Test passed: The plant name is correctly 'Water Lily'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }


    @Test
    /**
     * Corresponds to Test Case ID#21
     * Context Case #C2 (Far)
     * Input Case #I11
     * Expected: Sunflower
     */
    public void testCase10() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (18).png, 554 kB, Apr 23\"]\n"));
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
    /**
     * Corresponds to Test Case ID#22
     * Context Case #C2 (Far)
     * Input Case #I12
     * Expected: Sunflower
     */
    public void testCase11() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (19).png, 1.38 MB, Apr 23\"]\n"));
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
    /**
     * Corresponds to Test Case ID#3
     * Context Case #C3 (Front Angle)
     * Input Case #I7
     * Expected: Water Lily
     */
    public void testCase12() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (2).png, 3.45 MB, Apr 23\"]\n"));
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

            // Assert that the plant name is "Water Lily"
            Assert.assertEquals("Water Lily", plantName);
            System.out.println("Test passed: The plant name is correctly 'Water Lily'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#20
     * Context Case #C2 (Far)
     * Input Case #I9
     * Expected: Cherry Tree
     */
    public void testCase13() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (20).png, 3.43 MB, Apr 23\"]\n"));
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

            // Assert that the plant name is "Cherry Tree"
            Assert.assertEquals("Cherry Tree", plantName);
            System.out.println("Test passed: The plant name is correctly 'Cherry Tree'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#19
     * Context Case #C2 (Far)
     * Input Case #I8
     * Expected: Mango Tree
     */
    public void testCase14() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (21).png, 270 kB, Apr 23\"]\n"));
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

            // Assert that the plant name is "Mango Tree"
            Assert.assertEquals("Mango Tree", plantName);
            System.out.println("Test passed: The plant name is correctly 'Mango Tree'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#24
     * Context Case #C2 (Far)
     * Input Case #I14
     * Expected: Tiger Tooth Aloe
     */
    public void testCase15() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (22).png, 2.09 MB, Apr 23\"]\n"));
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

    @Test
    /**
     * Corresponds to Test Case ID#25
     * Context Case #C2
     * Input Case #I15
     * Expected: Balloon Flower OR Platycodon
     */
    public void testCase16() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        scrollDown();
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (23).png, 1.52 MB, Apr 23\"]"));
            plantPicture.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        Thread.sleep(5000); // Allow time for scan to occur
        try {
            // Get the text from the plantName element
            MobileElement plantNameElement = (MobileElement) driver.findElementById("com.myplantin.app:id/tvPlantName");
            String plantName = plantNameElement.getText();
            System.out.println("Found plant name: " + plantName);

            // Check if the plant name is "Balloon Flower" or "Platycodon"
            boolean isCorrectName = "Balloon Flower".equals(plantName) || "Platycodon".equals(plantName);
            Assert.assertTrue("Test failed: The plant name should be 'Balloon Flower' or 'Platycodon'. Found: " + plantName, isCorrectName);
            System.out.println("Test passed: The plant name is correctly '" + plantName + "'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#23
     * Context Case #C2 (Far)
     * Input Case #I14
     * Expected: Tiger Tooth Aloe
     */
    public void testCase17() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        scrollDown();
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (24).png, 560 kB, Apr 23\"]\n"));
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
    @Test
    /**
     * Corresponds to Test Case ID#27
     * Context Case None
     * Input Case #I10
     * Expected: No Result
     */
    public void testCase18() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        scrollDown();
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (25).png, 674 kB, Apr 23\"]\n"));
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

            // Assert that the plant name is "No Result"
            Assert.assertEquals("No Result", plantName);
            System.out.println("Test passed: The plant name is correctly 'No Result'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#26
     * Context Case #C2
     * Input Case #I16
     * Expected: Balloon Flower OR Platycodon
     */
    public void testCase19() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        scrollDown();
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (26).png, 0.97 MB, Apr 23\"]\n"));
            plantPicture.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        Thread.sleep(5000); // Allow time for scan to occur
        try {
            // Get the text from the plantName element
            MobileElement plantNameElement = (MobileElement) driver.findElementById("com.myplantin.app:id/tvPlantName");
            String plantName = plantNameElement.getText();
            System.out.println("Found plant name: " + plantName);

            // Check if the plant name is "Balloon Flower" or "Platycodon"
            boolean isCorrectName = "Balloon Flower".equals(plantName) || "Platycodon".equals(plantName);
            Assert.assertTrue("Test failed: The plant name should be 'Balloon Flower' or 'Platycodon'. Found: " + plantName, isCorrectName);
            System.out.println("Test passed: The plant name is correctly '" + plantName + "'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#7
     * Context Case #C4 (Behind Angle)
     * Input Case #I7
     * Expected: Sacred Lotus
     */
    public void testCase20() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        scrollDown();
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (3).png, 130 kB, Apr 23\"]\n"));
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

            // Assert that the plant name is "Sacred Lotus"
            Assert.assertEquals("Sacred Lotus", plantName);
            System.out.println("Test passed: The plant name is correctly 'Sacred Lotus'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }
    @Test
    /**
     * Corresponds to Test Case ID#5
     * Context Case #C5 (Bright)
     * Input Case #I3
     * Expected: California Poppy
     */
    public void testCase21() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        scrollDown();
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (4).png, 1.26 MB, Apr 23\"]\n"));
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

            // Assert that the plant name is "California Poppy"
            Assert.assertEquals("California Poppy", plantName);
            System.out.println("Test passed: The plant name is correctly 'California Poppy'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#6
     * Context Case #C6 (Dark)
     * Input Case #I3
     * Expected: California Poppy
     */
    public void testCase22() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        scrollDown();
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (5).png, 1.53 MB, Apr 23\"]\n"));
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

            // Assert that the plant name is "California Poppy"
            Assert.assertEquals("California Poppy", plantName);
            System.out.println("Test passed: The plant name is correctly 'California Poppy'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }


    @Test
    /**
     * Corresponds to Test Case ID#8
     * Context Case #C8(Low)
     * Input Case #I4
     * Expected: Balloon Flower OR Platycodon
     */
    public void testCase23() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        scrollDown();
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (6).png, 43.99 kB, Apr 23\"]\n"));
            plantPicture.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        Thread.sleep(5000); // Allow time for scan to occur
        try {
            // Get the text from the plantName element
            MobileElement plantNameElement = (MobileElement) driver.findElementById("com.myplantin.app:id/tvPlantName");
            String plantName = plantNameElement.getText();
            System.out.println("Found plant name: " + plantName);

            // Check if the plant name is "Balloon Flower" or "Platycodon"
            boolean isCorrectName = "Balloon Flower".equals(plantName) || "Platycodon".equals(plantName);
            Assert.assertTrue("Test failed: The plant name should be 'Balloon Flower' or 'Platycodon'. Found: " + plantName, isCorrectName);
            System.out.println("Test passed: The plant name is correctly '" + plantName + "'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#10
     * Context Case #C10 (Optimal)
     * Input Case #I1
     * Expected: Tiger Tooth Aloe
     */
    public void testCase24() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        scrollDown();
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (7).png, 2.02 MB, Apr 23\"]\n"));
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

    @Test
    /**
     * Corresponds to Test Case ID#9
     * Context Case #C19 (Close)
     * Input Case #I1
     * Expected: Tiger Tooth Aloe
     */
    public void testCase25() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        scrollDown();
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (8).png, 252 kB, Apr 23\"]\n"));
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

    @Test
    /**
     * Corresponds to Test Case ID#7
     * Context Case #C7(High)
     * Input Case #I4
     * Expected: Balloon Flower OR Platycodon
     */
    public void testCase26() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        scrollDown();
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (9).png, 1.77 MB, Apr 23\"]\n"));
            plantPicture.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        Thread.sleep(5000); // Allow time for scan to occur
        try {
            // Get the text from the plantName element
            MobileElement plantNameElement = (MobileElement) driver.findElementById("com.myplantin.app:id/tvPlantName");
            String plantName = plantNameElement.getText();
            System.out.println("Found plant name: " + plantName);

            // Check if the plant name is "Balloon Flower" or "Platycodon"
            boolean isCorrectName = "Balloon Flower".equals(plantName) || "Platycodon".equals(plantName);
            Assert.assertTrue("Test failed: The plant name should be 'Balloon Flower' or 'Platycodon'. Found: " + plantName, isCorrectName);
            System.out.println("Test passed: The plant name is correctly '" + plantName + "'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#1
     * Context Case #C1 (Similar Background)
     * Input Case #I15
     * Expected: Sunflower
     */
    public void testCase27() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        scrollDown();
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed.png, 183 kB, Apr 23\"]\n"));
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
}
