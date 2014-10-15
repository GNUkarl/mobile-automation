/*
 * Karl Chavarria
 * 9-21-14
 * Small test setup/cleanup class that I hope to reference in future tests
 */


package simpletesting;

import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;
import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;

import org.openqa.selenium.WebDriver;



public class TestSetup {
	
	static String appId = "com.banksimple:1.6.7";
	static String apk_location = "E:\\Dev\\Selendroid\\simple\\com.banksimple.apk";

	//logic necessary for Selendroid to track its running status  
	public static SelendroidLauncher selendroidServer = null;
	public static WebDriver driver = null;

	
	public static void startSelendroidServer() throws Exception {
	 
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
	 
	public static void stopSelendroidServer() {
	 
		if (driver != null) {
			driver.quit();
		}
	
		if (selendroidServer != null) {
			selendroidServer.stopSelendroid();
		}
	 
	}
	
}
