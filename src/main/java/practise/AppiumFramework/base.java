package practise.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class base {

		public static AppiumDriverLocalService service;
		public static AndroidDriver<AndroidElement> driver;
		
		public AppiumDriverLocalService startServer() {
			boolean flag = checkIfServerIsRunning(4723);
			if(!flag)
			{
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
			}
			return service;
		}
		
		public static boolean checkIfServerIsRunning(int port) {
			boolean isServerRunning = false;
			ServerSocket serverSocket;
			try {
				serverSocket = new ServerSocket(port);
				serverSocket.close();
			} catch (IOException e) {
				//If a control comes here, then it means that the port is in use
				isServerRunning = true;
			}finally {
				serverSocket = null;
			}
			return isServerRunning;
		}
		
		public static void getScreenshot(String s) throws IOException {
			TakesScreenshot tc = (TakesScreenshot)driver;
			File srcFile = tc.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File("D:\\Users\\Temp\\Desktop\\AppiumPractise\\AppiumFramework\\test-output\\Screenshots\\"+s+".png"));
			}
		
		
		@BeforeTest
		public void killAllNodes() throws Exception {
			//to kill a appium server if it already running
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			Thread.sleep(3000);
		}
	
		public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException{
			
			
			FileInputStream file =new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\practise\\AppiumFramework\\global.properties");
			Properties prop = new Properties();
			prop.load(file);
			
			
			
			File appDir = new File("src");
			File app = new File(appDir,(String) prop.get(appName));
			
			DesiredCapabilities cap = new DesiredCapabilities();
//			String device = (String) prop.get("device");
			//To give a device name from command promt (Must give a device name in single word)
			String device = System.getProperty("deviceName");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
			cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
			driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
			return driver;
			
//			File appDir = new File("D:\\Users\\Temp\\Desktop\\AppiumPractise\\AppiumStudy\\src\\test\\resources");
//			File app = new File(appDir,"General-Store.apk");
//			
//			DesiredCapabilities cap = new DesiredCapabilities();
//			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
//			cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
//			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
//			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,14);
//			AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
//			return driver;
			
			
		}
		
	
}
