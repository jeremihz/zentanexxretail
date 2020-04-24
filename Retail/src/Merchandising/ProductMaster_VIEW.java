package Merchandising;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ProductMaster_VIEW extends TestBase {

	String RowNumber;
	String productCode;
	String productDescription;
	String ViewProductCode;
	String ViewProductDescription;
	String productDescriptionAfterList;
	Random rnd = new Random();

	@Test(priority = 12)
	public void navigateTo_ProductMaster() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		System.out.println("--------------------------------");

		Thread.sleep(7000);
		driver.findElement(By.xpath("//*/em[@class='icon-grid']")).click();

		driver.findElement(By.partialLinkText("Merchandising")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//*/a[@title='Product']/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*/ul/li/a[@href='#/merchandising/product-master']//span")).click();

	}

	@Test(priority = 13)
	public void select_ProductToView() throws InterruptedException {
		System.out.println("--------------------------------");
		// Row Number of product to be selected for edit
		RowNumber = "4";
		Thread.sleep(4000);
		// get product code & product descripton from Set Row
		productCode = driver.findElement(By.xpath("//*/table/tbody/tr[" + RowNumber + "]/td[2]")).getText().trim();
		productDescription = driver.findElement(By.xpath("//*/table/tbody/tr[" + RowNumber + "]/td[3]")).getText()
				.trim();
		System.out.println("Selected product code = " + productCode + " | product description = " + productDescription);

		driver.findElement(By.xpath("//*/table/tbody/tr[" + RowNumber + "]/td[5]//button[text()='View']")).click();

	}

	@Test(priority = 14)
	public void verify_viewProductButtonWorks() throws InterruptedException {
		System.out.println("--------------------------------");
		Thread.sleep(3000);
		// verify edit button takes user to selected product edit page

		
		if (driver.findElement(By.xpath("//*/input[@placeholder='Product Code']")).isDisplayed()) {
			ViewProductCode = driver.findElement(By.xpath("//*/input[@placeholder='Product Code']"))
					.getAttribute("value");
			ViewProductDescription = driver.findElement(By.xpath("//*/input[@placeholder='Description']"))
					.getAttribute("value");
			System.out.println("On Edit page-> product code = " + ViewProductCode + " | product description = "
					+ ViewProductDescription);

			assertEquals(productCode, ViewProductCode);
			assertEquals(productDescription, ViewProductDescription);
		}
		
		else if (driver.findElement(By.xpath("//*/input[@placeholder='Auto Generate']")).isDisplayed()) {

			ViewProductCode = driver.findElement(By.xpath("//*/input[@placeholder='Auto Generate']"))
					.getAttribute("value");
			ViewProductDescription = driver.findElement(By.xpath("//*/input[@placeholder='Description']"))
					.getAttribute("value");
			System.out.println("On Edit page-> product code = " + ViewProductCode + " | product description = "
					+ ViewProductDescription);

			assertEquals(productCode, ViewProductCode);
			assertEquals(productDescription, ViewProductDescription);
		}


		Thread.sleep(2000);

		driver.findElement(By.xpath("//*/div/button/span[text()='List']")).click();

	}

	@Test(dependsOnMethods = "verify_viewProductButtonWorks")
	public void Verify_ListButtonWorks() throws InterruptedException {
		System.out.println("--------------------------------");
		Thread.sleep(3000);
		productDescriptionAfterList = driver.findElement(By.xpath("//*/table/tbody/tr[" + RowNumber + "]/td[3]"))
				.getText().trim();
		System.out.println("Product description after Click on LIST = " + productDescriptionAfterList);
		if (productDescriptionAfterList != productDescription) {
			System.out.println("Description After LIST = " + productDescriptionAfterList);
			System.out.println("--VIEW Product Successfull--");
		}

		else {
			System.out.println("--VIEW Product FAILED--");
		}

		// close product master ribbon
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*/a[@title='Product']/span")).click();
	}

}
