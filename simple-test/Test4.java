/* Karl Chavarria
 * 9-23-14
 * Test 4: Navigate to Sign In area and ensure that the FDIC notice is displayed
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



public class Test4 {

	String test_username = "SomeTestNameHere";
	String test_passphrase = "SomeTestPassphraseHere";
	String fdic_notice = "//TypefaceTextView[@value='Banking Services provided by The Bancorp Bank, Member FDIC."
			+ " The Simple Visa® Card is issued by The Bancorp Bank pursuant to a license from Visa U.S.A. Inc."
			+ " and may be used everywhere Visa debit cards are accepted.']";
			
	static String appId = "com.banksimple:1.6.7";
	static String apk_location = "E:\\Dev\\Selendroid\\simple\\com.banksimple.apk";
	
	//logic necessary for Selendroid to track its running status
	private static SelendroidLauncher selendroidServer = null;
	private static WebDriver driver = null;
      

    	@Test
    	public void loginTesting(){
		
    		/*
    		 * The below method navigates to the Sign In area and ensures that the 
    		 * FDIC notice is present.
    		 */
    		   		
    		driver.findElement(By.id("sign_in")).click();

    		boolean fdicNoticeExists = driver.findElement(By.xpath(fdic_notice)).isDisplayed();
    		Assert.assertTrue(fdicNoticeExists); /* Actual Junit logic to make FDIC notice existence == test pass */
    		    				
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
