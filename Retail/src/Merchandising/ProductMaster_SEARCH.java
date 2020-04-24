package Merchandising;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ProductMaster_SEARCH extends TestBase {

	// Search Input parameters
	String productCode = "003-350120";
	String prodCodeAfterSearch;

	String productDesciption = "G/F COCONUT POWDER 1KG";
	String prodDescriptionAfterSearch;

	@Test
	public void a_navigateTo_ProductMaster() throws InterruptedException {
		System.out.println("--------------------------------");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Thread.sleep(5000);
		driver.findElement(By.xpath("//*/em[@class='icon-grid']")).click();

		driver.findElement(By.partialLinkText("Merchandising")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*/a[@title='Product']/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*/ul/li/a[@href='#/merchandising/product-master']//span")).click();
	}

	@Test
	public void b_TestSearchBYcode() throws InterruptedException {
		System.out.println("Search by Product Code = " + productCode);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys(productCode);
		driver.findElement(By.xpath("//*/button/span[text()='Search']")).click();
		Thread.sleep(2000);
		prodCodeAfterSearch = driver.findElement(By.xpath("//*/table/tbody/tr[1]/td[@class='ng-binding'][1]")).getText()
				.trim();
		assertEquals(productCode, prodCodeAfterSearch);
	}

	@Test
	public void c_TestSearchBYdescription() throws InterruptedException {
		System.out.println("--------------------------------");
		driver.findElement(By.xpath("//*[@placeholder='Search']")).clear();
		System.out.println("Search by Product Description = " + productDesciption);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys(productDesciption);
		driver.findElement(By.xpath("//*/button/span[text()='Search']")).click();
		Thread.sleep(2000);
		prodDescriptionAfterSearch = driver.findElement(By.xpath("//*/table/tbody/tr[1]/td[@class='ng-binding'][2]"))
				.getText().trim();
		assertEquals(productDesciption, prodDescriptionAfterSearch);
		
		System.out.println("--------------------------------");

	}
}
