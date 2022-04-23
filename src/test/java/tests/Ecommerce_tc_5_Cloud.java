package tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.CheckoutPage;
import pageObjects.FormPage;
import practise.AppiumFramework.Utilities;
import practise.AppiumFramework.base;

public class Ecommerce_tc_5_Cloud extends base{

	public static double getAmount(String value) {
		value = value.substring(1);
		double valueInDouble = Double.parseDouble(value);
		return valueInDouble;
	}

	@Test
	public void totalValidation() throws IOException, InterruptedException {
//		service = startServer();
		AndroidDriver<AndroidElement> driver = cloudCapabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		FormPage formPage = new FormPage(driver);
//		driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Mona");
//		formPage.nameField.sendKeys("Mona");
		
		//when we make webelement as private we have to call getNameField method to access webelements
		formPage.getNameField().sendKeys("Mona");
		driver.hideKeyboard();
//		driver.findElementByXPath("//*[@text='Female']").click();
		formPage.femaleOption.click();		
//		driver.findElementById("android:id/text1").click();
		formPage.getCountryField().click();
		Utilities util = new Utilities(driver);
//		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		util.scrollToText("Argentina");
		driver.findElementByXPath("//*[@text='Argentina']").click();
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(4000);
		
		//validating values
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		
//		int count = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
		int count = checkoutPage.productList.size();
		double sum = 0;
		for(int i = 0;i < count; i++)
		{
//			String amount = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
			String amount = checkoutPage.productList.get(i).getText();
			double amount1 =getAmount(amount);
			sum = sum + amount1;
		}
		System.out.println("Sum of products is: "+sum);
		
//		String total = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		String total = checkoutPage.totalAmount.getText();
		double total1 =getAmount(total);
		System.out.println("Total Value of products is: "+total1);
		
		Assert.assertEquals(sum, total1);
//		service.stop();
	}


}
