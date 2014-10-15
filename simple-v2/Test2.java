/* Karl Chavarria
 * 9-30-14
 * Test 2: Navigates to the "Sign In" screen and tests login functionality
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



public class Test2 {

	String test_name = "SomeTestNameHere";
	String test_pass = "SomeTestPassphrase";
			
	static String appId = "com.banksimple:2.1.1";
	static String apk_location = "E:\\Dev\\Selendroid\\simple\\new\\com.banksimple-1.apk";
	
	//logic necessary for Selendroid to track its running status  
	private static SelendroidLauncher selendroidServer = null;
	private static WebDriver driver = null;
      

	@Test
	public  void inviteTesting() throws InterruptedException{
		
		/*
		 * The below method navigates to the "Sign In" section and then feeds invalid login info.
		 * The presence "Invalid login" pop-up is tested for
		 * 
		 * To do: Obtain a working login account (for test) and create a new test to work
		 * with valid login info. Also add loop to verify content of zap_text pop-up
		 */
    	      	  
		//click "Sign In" to move to the sign in screen
		driver.findElement(By.id("sign_in_button")).click();
		
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).sendKeys(new String[] {test_name});

		driver.findElement(By.id("passphrase")).click();
		driver.findElement(By.id("passphrase")).sendKeys(new String[] {test_pass});

		driver.findElement(By.id("submit_button")).click();
		
		//ensure that the "Invalid Login" pop-up appears in order to pass the JUnit test
		boolean invalidPopupExists = driver.findElement(By.id("zap_text")).isDisplayed();
		Assert.assertTrue(invalidPopupExists); /* Actual Junit logic to make zap_text existence == test pass */
		
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
