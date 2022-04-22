package tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.Dependencies;
import pageObjects.HomePage;
import pageObjects.Preferances;
import practise.AppiumFramework.TestData;
import practise.AppiumFramework.base;

public class ApiDemoTest extends base {
	@Test(dataProvider="InputData",dataProviderClass=TestData.class)
	public void apiDemo(String input) throws IOException  {
			service = startServer();
		
			AndroidDriver<AndroidElement> driver = capabilities("apiDemo");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Application Started....");
			
			HomePage h =new HomePage(driver);
			//Constructor of class will be invoked when yo create object for the class
			//Default constructor is called
			//Constructor can be defined with arguments
			//You can call the methods or variables of the class with class object
			h.Preferences.click();
			
			Preferances p = new Preferances(driver);

			p.Dependencies.click();
			
			Dependencies d = new Dependencies(driver);
			d.Wifi.click();
	
			d.WifiSetting.click();

			
			d.EditBox.sendKeys(input);
			p.Btn.get(1).click();
		
			service.stop();
	}

}
