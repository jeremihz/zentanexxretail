package Merchandising;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utility.Exceldata;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static FileInputStream fis;
	public static Exceldata excel = new Exceldata();
	public static WebDriverWait wait;

	public static String browser;
	public static String username;
	public static String password;

	@BeforeSuite
	public void setUp_openBrowser() throws InterruptedException, IOException {

		if (driver == null) {

			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\test resources\\properties\\Config.properties");

			config.load(fis);

			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");
			} else {

				browser = config.getProperty("browser");

			}

			config.setProperty("browser", browser);

			if (config.getProperty("browser").equals("firefox")) {

				System.setProperty("webdriver.gecko.driver", "\\test resources\\WebDriver\\chromedriver.exe");
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\test resources\\WebDrivers\\chromedriver.exe");
				driver = new ChromeDriver();

			} else if (config.getProperty("browser").equals("IE")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\test resources\\WebDriver\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();

			}

			driver.get(config.getProperty("baseURL"));

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
		}

		// set login credentials<username & password>

		username = config.getProperty("username");
		password = config.getProperty("password");

		driver.findElement(By.xpath("//*[@id='1item']")).sendKeys(username);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*/button[text()='Next']")).click();

		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id='password']")).sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*/button[text()='Login']")).click();
		Thread.sleep(2000);

		driver.navigate().refresh();

		Thread.sleep(3000);

	}

	@AfterSuite
	public void tearDown_closeBrowser() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a/em[@class='icon-logout']")).click();
		Thread.sleep(1000);
		driver.close();
	}

}
