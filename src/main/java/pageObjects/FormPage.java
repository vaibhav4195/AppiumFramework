package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {
	
	public FormPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Mona");
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	//driver.findElementByXPath("//*[@text='Female']").click();
	@AndroidFindBy(xpath="//*[@text='Female']")
	public WebElement femaleOption;
	
	//driver.findElementById("android:id/text1").click();
	@AndroidFindBy(id="android:id/text1")
	private WebElement countrySelection;
	
	public WebElement getNameField() {
		System.out.println("Trying to find the name field");
		return nameField;
	}
	
	public WebElement getCountryField() {
		System.out.println("Trying to get the name of country");
		return countrySelection;
	}
}
