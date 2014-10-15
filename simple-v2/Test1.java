/* Karl Chavarria
 * 9-30-14
 * Test 1: Ensure that the "Get Invited" function on the app's initial screen behaves as expected.
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



public class Test1 {

	String test_email = "SomeTestEmailHere@site.test";
			
	static String appId = "com.banksimple:2.1.1";
	static String apk_location = "E:\\Dev\\Selendroid\\simple\\new\\com.banksimple-1.apk";
	
	//logic necessary for Selendroid to track its running status  
	private static SelendroidLauncher selendroidServer = null;
	private static WebDriver driver = null;
      

	@Test
	public  void inviteTesting() throws InterruptedException{
		
		/*
		 * The below method stays on the initial app screen and tests the "Get Invited" function.
		 * 
		 * To do: In the case of network latency, the zap_text element may take a while to appear.
		 * I need to add a loop that re-checks for the zap_text element every X seconds for
		 * Y total seconds, if the element isn't found on first run.
		 */

		//click "Email" field and type in test email address
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys(new String[] {test_email});

		//click "Get Invited"
		driver.findElement(By.id("submit_button")).click();

		//ensure that the "invite sent" popup appears in order to pass the JUnit test
		boolean popupExists = driver.findElement(By.id("zap_text")).isDisplayed();
		Assert.assertTrue(popupExists); /* Actual Junit logic to make zap_text existence == test pass */
					
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
