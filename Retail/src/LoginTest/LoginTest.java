package LoginTest;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import utility.Exceldata;

public class LoginTest {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Exceldata excel = new Exceldata();
	public static WebDriverWait wait;

	@Test
	public void openBrowser() throws InterruptedException {
		
		
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\test resources\\WebDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver.get("http://197.220.114.46:9633/#/merchandising/dashboard");
		driver.get("http://vmmain-pc:9330/NexxRetail/#/page/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(dependsOnMethods="openBrowser")
	public void setloginCredentials() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id='1item']")).sendKeys("sammy");
		//driver.findElement(By.xpath("//*[@id='1item']")).sendKeys("sysadmin");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*/button[text()='Next']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("123@Asd");
		// driver.findElement(By.xpath("//*[@id='password']")).sendKeys("admin12347");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*/button[text()='Login']")).click();
		Thread.sleep(2000);
	}

	@Test(dependsOnMethods="setloginCredentials")
	public void verifyLogin() throws InterruptedException {

		Thread.sleep(2000);
		var buttonLogout = driver.findElement(By.xpath("//*/ul/li/a/em[@class='icon-logout']"));

		if (buttonLogout.isDisplayed()) {
			System.out.println("Login Successful");

		} else {
			System.out.println("Login Failed");
		}

	}

	@Test(dependsOnMethods="verifyLogin")
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a/em[@class='icon-logout']")).click();
		Thread.sleep(1000);
		driver.close();
	}

}
