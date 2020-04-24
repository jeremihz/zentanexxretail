package LoginTest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Login_DataDriven {

public static Properties config = new Properties();
public static FileInputStream fis;

public static WebDriverWait wait;
public static String browser;
public static String username;
public static String password;

	WebDriver driver;
	HSSFWorkbook Workbook;
	HSSFSheet Sheet;
	HSSFCell Cell;

	@Test(priority=1)
	public void openBrowser() throws InterruptedException, IOException {

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

	}


	@SuppressWarnings("deprecation")
	@Test(priority=2)
	public void readLoginData() throws IOException, InterruptedException {
		// import excell sheet
		File src = new File(System.getProperty("user.dir") + "\\test resources\\excel\\TestData.xls");

		// Load the file.
		FileInputStream fis = new FileInputStream(src);

		// Load the workbook.
		Workbook = new HSSFWorkbook(fis);

		// Load the sheet in which data is stored.
		Sheet = Workbook.getSheetAt(0);

		for (int i = 1; i <= Sheet.getLastRowNum(); i++) {
			Thread.sleep(2000);
			// Import data for username
			Cell = Sheet.getRow(i).getCell(1);
			//Cell.setCellType(Cell.CELL_TYPE_STRING);
			Cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id='1item']")).sendKeys(Cell.getStringCellValue());
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*/button[text()='Next']")).click();
			Thread.sleep(2000);
			
			// Import data for password
			Cell = Sheet.getRow(i).getCell(2);
			Cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id='password']")).sendKeys(Cell.getStringCellValue());
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*/button[text()='Login']")).click();
			Thread.sleep(2000);

		}
	}

	@Test(priority=3)
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a/em[@class='icon-logout']")).click();
		Thread.sleep(1000);
		driver.close();
	}

}
