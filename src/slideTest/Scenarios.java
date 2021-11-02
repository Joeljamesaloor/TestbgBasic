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
	//To verify the Login page
	@Test(priority = 0)
	public static void logindisplay() throws MalformedURLException, InterruptedException {

		AndroidDriver<AndroidElement> driver = capLock();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		String username = driver.findElementById("com.test.news:id/editTextUserName").getText();
		assertEquals("User name", username);
		String password = driver.findElementByXPath("//android.widget.EditText[@text='Password']").getText();
		assertEquals("Password", password);
		String loginButton = driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").getText();
		assertEquals("LOGIN", loginButton);

	}
	//To verify the Login page by entering invalid username & valid password
	@Test(priority = 1)
	public static void loginerror() throws MalformedURLException, InterruptedException {
		AndroidDriver<AndroidElement> driver = capLock();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.findElementByXPath("//android.widget.EditText[@text='User name']").sendKeys("password");
		driver.findElementByXPath("//android.widget.EditText[@text='Password']").sendKeys("password");
		driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").click();
		driver.findElementById("com.test.news:id/editTextUserName").click();

	}
	//To verify the Login page  by entering valid username & valid password(TC08)
	@Test(priority = 2)
	public static void loginvalid() throws MalformedURLException, InterruptedException {

		AndroidDriver<AndroidElement> driver = capLock();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		// Thread.sleep(2000);
		driver.findElementByXPath("//android.widget.EditText[@text='User name']").sendKeys("user1");
		driver.findElementByXPath("//android.widget.EditText[@text='Password']").sendKeys("password");
		driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").click();
		AndroidElement newsImage = driver.findElementById("com.test.news:id/imageView");
		Assert.assertEquals(true, newsImage.isDisplayed());

	}
	//To verify the user relogin without entering credentials(TC09)
	@Test(priority = 3)
	public static void cachelogin() throws MalformedURLException, InterruptedException {

		AndroidDriver<AndroidElement> driver = capLock();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		AndroidElement newsImage = driver.findElementById("com.test.news:id/imageView");
		Assert.assertEquals(true, newsImage.isDisplayed());
	}
	//To verify the selected image is displayed in the external browser upon clicking image(TC13)
	@Test(priority = 4)
	public static void xternalbrowser() throws MalformedURLException, InterruptedException {

		AndroidDriver<AndroidElement> driver = capLock();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.findElementByXPath("//android.widget.EditText[@text='User name']").sendKeys("user1");
		driver.findElementByXPath("//android.widget.EditText[@text='Password']").sendKeys("password");
		driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").click();
		driver.findElements(By.className("androidx.recyclerview.widget.RecyclerView")).get(3).click();
		AndroidElement webImage = driver.findElementByClassName("android.widget.Image");
		Assert.assertEquals(true, webImage.isDisplayed());

	}
	//To verify the user login without Internet and with valid credentials(TC10)
	@Test(priority = 5)
	public static void nointernet() throws MalformedURLException, InterruptedException {

		
		Props f = new Props();
		f.wifiOff();
		Thread.sleep(3000);

		AndroidDriver<AndroidElement> driver = capLock();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElementByXPath("//android.widget.EditText[@text='User name']").sendKeys("user1");
		driver.findElementByXPath("//android.widget.EditText[@text='Password']").sendKeys("password");
		driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").click();
		
		AndroidElement errorMsg = driver.findElementById("com.test.news:id/textViewError");
		Assert.assertEquals(true, errorMsg.isDisplayed());
		
		f.wifiOn();

	}
}