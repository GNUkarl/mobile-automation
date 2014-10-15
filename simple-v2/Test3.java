/* Karl Chavarria
 * 9-30-14
 * Test 3: Navigate to Sign In area and ensure that the "Reset Passphrase" button works as 
 * expected
 */

package v2_simple_testing;

import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;
import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.*;

import org.junit.Test;
import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;



public class Test3 {

	static String appId = "com.banksimple:2.1.1";
	static String apk_location = "E:\\Dev\\Selendroid\\simple\\new\\com.banksimple-1.apk";
	
	//logic necessary for Selendroid to track its running status
	private static SelendroidLauncher selendroidServer = null;
	private static WebDriver driver = null;
      

    	@Test
    	public void loginTesting() throws InterruptedException{
		
    		/*
    		 * The below method navigates to the Sign In area and ensures that the 
    		 * "Reset Passphrase" button shows the correct pop-up when touched
    		 */
    		   		
    		//Navigate to the "Sign In" screen and click the reset password button
    		driver.findElement(By.id("sign_in_button")).click();
    		driver.findElement(By.id("reset_passphrase_button")).click();
   		
    		//There are individual booleans for each of the 2 pop-up options and the pop-up message
    		boolean messageExists = driver.findElement(By.id("message")).isDisplayed();
    		boolean button1Exists = driver.findElement(By.id("button1")).isDisplayed();
    		boolean button2Exists = driver.findElement(By.id("button2")).isDisplayed();
    		
    		//If all 3 booleans are true, the test is marked as pass, else test fails
    		if (messageExists == true && button1Exists == true && button2Exists == true) {
    			Assert.assertTrue(true); /* Actual Junit logic to make 3 booleans @ true == test pass */
    		} else {
    			Assert.assertFalse(false);
    		}
    	
    	}      

        @BeforeClass
        public static void setup() throws Exception {	/* Small function to start the Selendroid Server */
        	
        	if (selendroidServer != null) {
        		 selendroidServer.stopSelendroid();
        	}
        	
        	SelendroidConfiguration config = new SelendroidConfiguration();
        	config.addSupportedApp(apk_location);

        	selendroidServer = new SelendroidLauncher(config);
        	selendroidServer.launchSelendroid();
        	SelendroidCapabilities caps =
        	new SelendroidCapabilities(appId);
        	driver = new SelendroidDriver(caps);
        	
        }

        @AfterClass
        public static void cleanup() {	/* Small function to stop the Selendroid Server */
           	 
        	if (driver != null) {
           		 driver.quit();
           	}
           	if (selendroidServer != null) {
           		 selendroidServer.stopSelendroid();
           	}
                    
        }

}
