package Merchandising;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ProductMaster_Activate_Deactivate extends TestBase {

	// Row to get the in active product
	String row = "1";

	String product_Code;

	String product_CodeAfterSearch;

	String inActiveproduct_CodeAfterSearch;

	@Test(priority = 15)
	public void navigateTo_ProductMaster() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		System.out.println("---Navigating To Product Master---");

		Thread.sleep(8000);
		driver.findElement(By.xpath("//*/em[@class='icon-grid']")).click();

		driver.findElement(By.partialLinkText("Merchandising")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//*/a[@title='Product']/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*/ul/li/a[@href='#/merchandising/product-master']//span")).click();
	}

	@Test(priority = 16)
	public void select_InActiveProduct() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*/div/label/span[text()='Inactive']")).click();

		Thread.sleep(3000);
		product_Code = driver.findElement(By.xpath("//*/table/tbody/tr[" + row + "]/td[@class='ng-binding'][1]")).getText().trim();
		System.out.println("Product Code from in-Active Tab = " + product_Code);

		// click on Activate Button
		driver.findElement(By.xpath("//*/tr[" + row + "]/td/button[@id='activateBtn'][text()='Activate']")).click();
	}

	@Test(priority = 17)
	public void ClickOn_activateProduct() throws InterruptedException {
		Thread.sleep(5000);
		// Accept from modal box pop-Up
		driver.findElement(By.xpath("//*/div[@data-timer='null']//button[text()='Yes']")).click();
		// success message pop-up
		driver.findElement(By.xpath("//*[@id='toast-container']/div")).isDisplayed();
	}

	@Test(priority = 18)
	public void Verify_productActivated() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys(product_Code);
		driver.findElement(By.xpath("//*/button/span[text()='Search']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*/div/label/span[text()='Active']")).click();
		Thread.sleep(2000);

		product_CodeAfterSearch = driver.findElement(By.xpath("//*/table/tbody/tr[1]/td[@class='ng-binding'][1]")).getText().trim();
		System.out.println("Product Code after Search Active Tab = " + product_CodeAfterSearch);

		if (product_Code == product_CodeAfterSearch) {
			System.out.println("Successfully activated Product");
		}

		else {
			System.out.println("Product NOT Successfully Activated");
		}
	}

	@Test(priority = 19)
	public void ClickOn_deactivateProduct() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*/tr[1]/td/button[@id='deactivateBtn'][text()='Deactivate']")).click();

		// Accept from modal box pop-Up
		driver.findElement(By.xpath("//*/div[@data-timer='null']//button[text()='Yes']")).click();
		// success message pop-up
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='toast-container']/div")).isDisplayed();
	}

	@Test(priority = 20)
	public void Verify_ProductDeactivated() throws InterruptedException {
		driver.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys(product_Code);
		driver.findElement(By.xpath("//*/button/span[text()='Search']")).click();
		driver.findElement(By.xpath("//*/div/label/span[text()='Active']")).click();
		Thread.sleep(2000);

		inActiveproduct_CodeAfterSearch = driver
				.findElement(By.xpath("//*/table/tbody/tr[1]/td[@class='ng-binding'][1]")).getText().trim();
		System.out.println("Product Code after Search Active Tab = " + inActiveproduct_CodeAfterSearch);

		if (inActiveproduct_CodeAfterSearch == product_CodeAfterSearch) {
			System.out.println("Successfully DEactivated Product");
		}

		else {
			System.out.println("Product NOT Successfully DE-Activated");
		}
	}
}
