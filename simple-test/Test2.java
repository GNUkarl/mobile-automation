/* Karl Chavarria
 * 9-22-14
 * Test 2: Navigate to Request Invite area and ensure that the "Request Invite" button doesn't
 * appear until name and email are populated.
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



public class Test2 {

	String test_name = "SomeTestNameHere";
	String test_email = "SomeTestEmailHere@site.test";
			
	static String appId = "com.banksimple:1.6.7";
	static String apk_location = "E:\\Dev\\Selendroid\\simple\\com.banksimple.apk";
	
	//logic necessary for Selendroid to track its running status  
	private static SelendroidLauncher selendroidServer = null;
	private static WebDriver driver = null;
      

	@Test
	public  void inviteTesting() throws InterruptedException{
		
		/*
    	 * The below loop is used to swipe past the 5 advert pages to the sixth page.
    	 * On the sixth page sign up info is input and the existence of the invite button
    	 * is tested
    	 */
    	      	  
		for(int x = 0; x < 5; x = x+1) {
			Thread.sleep(500);	/* Selendroid needs a tiny bit of time to recuperate after swipes */
        	  
			WebElement to_swipe = driver.findElement(By.id("pager"));
			TouchActions flick = new TouchActions(driver).flick(to_swipe, -100, 0, 0);
  
			flick.perform();
		}

		driver.findElement(By.id("name")).click();
		driver.findElement(By.id("name")).sendKeys(new String[] {test_name});

		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys(new String[] {test_email});

		boolean invButtonExists = driver.findElement(By.id("request_invite")).isDisplayed();
		Assert.assertTrue(invButtonExists); /* Actual Junit logic to make request_invite existence == test pass */

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
