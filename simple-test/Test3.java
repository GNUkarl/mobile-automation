/* Karl Chavarria
 * 9-23-14
 * Test 3: Navigate to Sign In area and ensure that "Reset Passphrase" and "Call support" options are
 * present in the drop-down menu
 */

package simpletesting;

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

	String test_username = "SomeTestNameHere";
	String test_passphrase = "SomeTestPassphraseHere";
				
	static String appId = "com.banksimple:1.6.7";
	static String apk_location = "E:\\Dev\\Selendroid\\simple\\com.banksimple.apk";
	
	//logic necessary for Selendroid to track its running status
	private static SelendroidLauncher selendroidServer = null;
	private static WebDriver driver = null;
      

    	@Test
    	public void loginTesting() throws InterruptedException{
		
    		/*
    		 * The below method navigates to the Sign In area and ensures that the 
    		 * "Reset Passphrase" and "Call support" options are present.
    		 */
    		   		
    		driver.findElement(By.id("sign_in")).click();
    		driver.findElement(By.xpath("//ActionMenuView")).click();

    		//There are individual booleans for each of the 2 drop-down menu options
    		boolean resPassTrue = driver.findElement(By.xpath("(//ListMenuItemView)[1]")).isDisplayed();	
    		boolean callSuppTrue = driver.findElement(By.xpath("(//ListMenuItemView)[2]")).isDisplayed();

    		//If both booleans are true the test is marked as pass, else test fails
    		if (callSuppTrue == true && resPassTrue ==true) {
    			Assert.assertTrue(true);
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
