package Merchandising;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ProductMaster_Pagination extends TestBase {

	int TotalRows;
	String prodDescrPage1;
	String prodDescrPage3;
	String prodDescrReturnPage1;

	@Test
	public void a_NavigateTo_productMaster() throws InterruptedException {
		System.out.println("--------------------------------");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Thread.sleep(7000);
		driver.findElement(By.xpath("//*/em[@class='icon-grid']")).click();

		driver.findElement(By.partialLinkText("Merchandising")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//*/a[@title='Product']/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*/ul/li/a[@href='#/merchandising/product-master']//span")).click();
	}

	@Test
	public void b_countAvailableRowsOnTable() throws InterruptedException {
		System.out.println("--------------------------------");
		Thread.sleep(5000);
		List<WebElement> rows = driver.findElements(By.xpath("//*/div[@class='table-responsive']/table/tbody/tr"));

		TotalRows = rows.size();

		System.out.println("Available Rows on First Page = " + TotalRows);
	}

	@Test
	public void c_ExecutePagination() throws InterruptedException {
		System.out.println("--------------------------------");
		if (TotalRows >= 50) {

			// Get product description frm Page 1->click to get to Page 3
			prodDescrPage1 = driver.findElement(By.xpath("//*/div[@class='table-responsive']/table/tbody/tr[1]/td[3]"))
					.getText();

			driver.findElement(By.xpath("//*/ul/li/a[text()='3']")).click();

			Thread.sleep(3000);

			// get product description frm Page 3, compare to Page 1 description
			prodDescrPage3 = driver.findElement(By.xpath("//*/div[@class='table-responsive']/table/tbody/tr[1]/td[3]"))
					.getText();

			// Click->get back Page 1 & get product description again frm Page 1 &
			// compare to Description frm Page 3
			driver.findElement(By.xpath("//*/ul/li/a[text()='1']")).click();

			Thread.sleep(2000);
			prodDescrReturnPage1 = driver
					.findElement(By.xpath("//*/div[@class='table-responsive']/table/tbody/tr[1]/td[3]")).getText();

		}

	}

	@Test
	public void d_Verify_Table_Pagination() {
		System.out.println("--------------------------------");
		if (prodDescrPage1 != prodDescrPage3 && prodDescrPage3 != prodDescrReturnPage1) {
			System.out.println("Product description Page 1 = " + prodDescrPage1);
			System.out.println("Product description Page 3 = " + prodDescrPage3);
			System.out.println("Product description Return Page 1 = " + prodDescrReturnPage1);
			System.out.println("Pagination Works Fine");
		}

		else {
			System.out.println("Product description Page 1 = " + prodDescrPage1);
			System.out.println("Product description Page 3 = " + prodDescrPage3);
			System.out.println("Product description Return Page 1 = " + prodDescrReturnPage1);
			System.out.println("Error! pagination NOT working");
		}

		System.out.println("--------------------------------");
	}

}
