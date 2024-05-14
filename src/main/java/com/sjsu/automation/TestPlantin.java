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

    /**TODO
     * Have  a for loop iterate through the container - modify the test case methods to take an element from said
     * container and each eleemnt will correspond to an index in the container
     *
     *
     */

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

    @Test
    /**
     * Corresponds to Test Case ID#28
     * Context Case #2 (Similar Background)
     * Expected: Echeveria Colorata
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (28).jpeg, 64 KB, May 13\"]"));
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

            // Assert that the plant name 
            Assert.assertEquals("Echeveria Colorata", plantName);
            System.out.println("Test passed: The plant name is correctly 'Echeveria Colorata'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#29
     * Context Case #3 (Similar Background)
     * Expected: Mango Tree
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (29).jpeg, 233 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#30
     * Context Case #4 (Similar Background)
     * Expected: Balloon Flower
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (30).jpeg, 131 KB, May 13\"]"));
            plantPicture.click();
            System.out.println("Button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in clicking the button: " + e.getMessage());
        }
        Thread.sleep(6000); // Allow time for scan to occur
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
     * Corresponds to Test Case ID#31
     * Context Case #4 (Similar Background)
     * Expected: Sunflower
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (31).jpeg, 49 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#31
     * Context Case #4 (Similar Background)
     * Expected: Sunflower
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (31).jpeg, 49 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#32
     * Context Case #5 (Different Background)
     * Expected: Sacred Lotus
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (32).jpeg, 7 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#33
     * Context Case #5 (Different Background)
     * Expected: Tiger Tooth Aloe
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (27).jpeg, 286 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#34
     * Context Case #6 (Front Angle)
     * Expected: Water lily
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (33).jpeg, 224 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#35
     * Context Case #7 (Front Angle)
     * Expected: Sunflower
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (34).png, 159 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#36
     * Context Case #8 (Behind Angle)
     * Expected: Sunflower
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (35).jpeg, 131 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#37
     * Context Case #9 (Behind Angle)
     * Expected: Sunflower
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (36).jpeg, 89 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#38
     * Context Case #10 (Bright light)
     * Expected: Mango Tree
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (37).png, 89 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#39
     * Context Case #11 (Bright light)
     * Expected: Tiger Tooth Aloe
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (38).png, 420 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#40
     * Context Case #12 (dark light)
     * Expected: Tiger Tooth Aloe
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (39).jpeg, 1.2 MB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#41
     * Context Case #13 (dark light)
     * Expected: Echeveria Colorata
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (40).jpeg, 650 KB, May 13\"]"));
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

            // Assert that the plant name 
            Assert.assertEquals("Echeveria Colorata", plantName);
            System.out.println("Test passed: The plant name is correctly 'Echeveria Colorata'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#42
     * Context Case #14 (high quality)
     * Expected: California Poppy
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (41).jpeg, 378 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#43
     * Context Case #15 (low quality)
     * Expected: Cherry Tree
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (56).jpeg, 185 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#44
     * Context Case #16 (close distance)
     * Expected: Mazzard Cherry
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"cherries.jpeg, 147 KB, May 13\"]"));
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

            // Assert that the plant name 
            Assert.assertEquals("Mazzard Cherry", plantName);
            System.out.println("Test passed: The plant name is correctly 'Mazzard Cherry'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#45
     * Context Case #17 (optimal distance)
     * Expected: California Poppy
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (42).jpeg, 1.7 MB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#46
     * Context Case #18 (far distance)
     * Expected: California Poppy
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (43).jpg_large, 921 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#47
     * Context Case #19 (Spring) 
     * Expected: Balloon Flower OR Platycodon
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (44).jpeg, 992 kB, May 13\"]"));
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
     * Corresponds to Test Case ID#48
     * Context Case #20 (Winter)
     * Expected: California Poppy
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (45).jpeg, 940 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#49
     * Context Case #21 (Summer)
     * Expected: Sunflower
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"unnamed (46).jpeg, 112 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#50
     * Context Case #22 (Autumn)
     * Expected: Cherry Tree
     */
    public void testCase1() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("\t//android.widget.LinearLayout[@content-desc=\"cherry_tree.jpeg, 514 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#51
     * Context Case #23 (Indoor)
     * Expected: Ballon Flower
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Balloon Flower(Indoor).jpg, 45 kB, May 13\"]"));
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
     * Corresponds to Test Case ID#52
     * Context Case #24 (outdoor)
     * Expected: Ballon Flower
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Balloon Flower(outdoor).jpg, 92 kB, May 13\"]"));
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
     * Corresponds to Test Case ID#53
     * Context Case #24 (Full Part)
     * Expected: Sacred Lotus
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Sacred Lotus(fruit).png, 1.7 MB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#54
     * Context Case #25 (Stem Part)
     * Expected: Sacred Lotus
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Sacred Lotus(stem).png, 334 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#55
     * Context Case #26 (Leaf Part)
     * Expected: Sacred Lotus
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Sacred Lotus(leaf).png, 339 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#56
     * Input Case #8 (Succulent - Echeveria)
     * Expected: Echeveria Colorata
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (48).jpg, 712 KB, May 13\"]"));
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

            // Assert that the plant name 
            Assert.assertEquals("Echeveria Colorata", plantName);
            System.out.println("Test passed: The plant name is correctly 'Echeveria Colorata'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#57
     * Input Case #9 (Ornamental - Balloon flower)
     * Expected: Balloon Flower 
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (49).jpeg, 462 kB, May 13\"]"));
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
     * Corresponds to Test Case ID#58
     * Input Case #10 (Aquatic)
     * Expected: Water Lily
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (50).png, 53 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#59
     * Input Case #11 (Fruiting)
     * Expected: Mango Tree
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (51).png, 92 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#60
     * Input Case #12 (Affected by season)
     * Expected: Sunflower
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (55).jpeg, 2.9 MB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#61
     * Input Case #13 (No occurrence)
     * Expected: No plant detected
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (52).png, 115 KB, May 13\"]"));
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

            // Assert that the plant name 
            Assert.assertEquals("No plant detected", plantName);
            System.out.println("Test passed: The plant name is correctly 'No plant detected'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#62
     * Input Case #14 (one occurrence)
     * Expected: Tiger Tooth Aloe
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (53).jpeg, 212 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#63
     * Input Case #15 (multiple occurrence)
     * Expected: California Poppy
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (54).jpeg, 247 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#64
     * Input Case #16 (unhealthy)
     * Expected: Sacred Lotus
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Sacred lotus(unhealthy).png, 988 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#65
     * Input Case #17 (healthy)
     * Expected: Echeveria
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (57).jpeg, 82 KB, May 13\"]"));
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

            // Assert that the plant name 
            Assert.assertEquals("Echeveria", plantName);
            System.out.println("Test passed: The plant name is correctly 'Echeveria'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    @Test
    /**
     * Corresponds to Test Case ID#66
     * Input Case #18 (juvenile)
     * Expected: Sacred Lotus
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Sacred lotus(juvenile).png, 521 KB, May 13\"]"));
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

            // Assert that the plant name 
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
     * Corresponds to Test Case ID#67
     * Input Case #19 (mature)
     * Expected: Rainier Cherry Tree
     */
    public void testCase5() throws Exception{
        setUpHelper();// Navigate to directory with plant photos
        try {
            // Use the XPath to find the button and click it
            MobileElement plantPicture = (MobileElement) driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"unnamed (20).png, 3.3 MB, May 13\"]"));
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

            // Assert that the plant name 
            Assert.assertEquals("Rainier Cherry Tree", plantName);
            System.out.println("Test passed: The plant name is correctly 'Rainier Cherry Tree'.");
        } catch (Exception e) {
            System.out.println("Error in test execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
        dismissScreen();
    }

    

    
    

    



//    @After
//    public void tearDown(){
//        if (driver != null){
//            driver.quit();
//        }
//    }
}
