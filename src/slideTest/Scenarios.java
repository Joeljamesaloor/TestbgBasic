package slideTest;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Scenarios extends Props {

	@Test(priority = 0)
	public static void one() throws MalformedURLException, InterruptedException {

		AndroidDriver<AndroidElement> driver = capLock();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		String username = driver.findElementById("com.test.news:id/editTextUserName").getText();
		assertEquals("User name", username);
		String password = driver.findElementByXPath("//android.widget.EditText[@text='Password']").getText();
		assertEquals("Password", password);
		String loginButton = driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").getText();
		assertEquals("LOGIN", loginButton);
		
				
	}

	// @Parameters({"username1","password1"})
	@Test(priority = 1)
	public static void two() throws MalformedURLException, InterruptedException {
		AndroidDriver<AndroidElement> driver = capLock();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.findElementByXPath("//android.widget.EditText[@text='User name']").sendKeys("password");
		driver.findElementByXPath("//android.widget.EditText[@text='Password']").sendKeys("password");
		driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").click();
		driver.findElementById("com.test.news:id/editTextUserName").click();
		
		
	}

	// @Parameters({"password1"})
	@Test(priority = 2)
	public static void three() throws MalformedURLException, InterruptedException {

		AndroidDriver<AndroidElement> driver = capLock();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		//Thread.sleep(2000);
		driver.findElementByXPath("//android.widget.EditText[@text='User name']").sendKeys("user1");
		driver.findElementByXPath("//android.widget.EditText[@text='Password']").sendKeys("password");
		driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").click();
		AndroidElement newsImage = driver.findElementById("com.test.news:id/imageView");
		Assert.assertEquals(true, newsImage.isDisplayed());
		
		
	}

	// @Parameters({"username1"})
	@Test(priority = 3)
	public static void four() throws MalformedURLException, InterruptedException {

		AndroidDriver<AndroidElement> driver = capLock();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.findElementByXPath("//android.widget.EditText[@text='User name']").sendKeys("user1");
		driver.findElementByXPath("//android.widget.EditText[@text='Password']").sendKeys("password");
		driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").click();
		
		//System.out.println(driver.findElements(By.className("androidx.recyclerview.widget.RecyclerView")).size());
		driver.findElements(By.className("androidx.recyclerview.widget.RecyclerView")).get(3).click();
		AndroidElement webImage = driver.findElementByClassName("android.widget.Image");
		Assert.assertEquals(true, webImage.isDisplayed());
		
	}

	// @Parameters({"username1"})
	@Test(priority = 4)
	public static void five() throws MalformedURLException, InterruptedException {

		Props f = new Props();
		f.wifiOff();
		
		AndroidDriver<AndroidElement> driver = capLock();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElementByXPath("//android.widget.EditText[@text='User name']").sendKeys("user1");
		driver.findElementByXPath("//android.widget.EditText[@text='Password']").sendKeys("password");
		driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").click();
		//Thread.sleep(4000);

		AndroidElement errorMsg = driver.findElementById("com.test.news:id/textViewError");
		Assert.assertEquals(true, errorMsg.isDisplayed());
		//Thread.sleep(3000);
		f.wifiOn();
		

	}
}