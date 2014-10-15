/* Karl Chavarria
 * 9-23-14
 * Test 1: Navigate to Sign In area and ensure that the "Sign In" button doesn't
 * appear until username and passphrase are populated.
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



public class Test1 {

	String test_username = "SomeTestNameHere";
	String test_passphrase = "SomeTestPassphraseHere";
			
	static String appId = "com.banksimple:1.6.7";
	static String apk_location = "E:\\Dev\\Selendroid\\simple\\com.banksimple.apk";
	
	//logic necessary for Selendroid to track its running status
	private static SelendroidLauncher selendroidServer = null;
	private static WebDriver driver = null;
      

    	@Test
    	public void loginTesting(){
		
    		/*
    		 * The below method navigates to the Sign In area and ensures that the 
    		 * Sign In box doesn't appear until the proper username and passphrase fields
    		 * are populated
    		 */
    		   		
    		driver.findElement(By.id("sign_in")).click();

    		driver.findElement(By.id("username")).click();
    		driver.findElement(By.id("username")).sendKeys(new String[] {test_username});
	
    		driver.findElement(By.id("passphrase")).click();
    		driver.findElement(By.id("passphrase")).sendKeys(new String[] {test_passphrase});

    		boolean signInButtonExists = driver.findElement(By.id("submit")).isDisplayed();
    		Assert.assertTrue(signInButtonExists); /* Actual Junit logic to make submit existence == test pass */
		
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
