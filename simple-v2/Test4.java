/* Karl Chavarria
 * 9-30-14
 * Test 4: Navigate to Sign In area and ensure that the FDIC notice is displayed
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



public class Test4 {

	String fdic_notice = "//TypefaceTextView[@value='Banking Services provided by The Bancorp Bank, Member FDIC."
			+ " The Simple Visa® Card is issued by The Bancorp Bank pursuant to a license from Visa U.S.A. Inc."
			+ " and may be used everywhere Visa debit cards are accepted.']";
			
	static String appId = "com.banksimple:2.1.1";
	static String apk_location = "E:\\Dev\\Selendroid\\simple\\new\\com.banksimple-1.apk";
	
	//logic necessary for Selendroid to track its running status
	private static SelendroidLauncher selendroidServer = null;
	private static WebDriver driver = null;
      

    	@Test
    	public void loginTesting() throws InterruptedException {
		
    		/*
    		 * The below method navigates to the Sign In area and ensures that the 
    		 * FDIC notice is present.
    		 */
    		   		
    		//click "Sign In" to move to the sign in screen
    		driver.findElement(By.id("sign_in_button")).click();

    		//ensure that the FDIC notice exists in order to pass the JUnit test
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
