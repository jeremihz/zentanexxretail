package Merchandising;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ProductMaster_NEW extends TestBase {

	@Test(priority = 3)
	public void navigateTo_ProductMaster() throws InterruptedException {
		System.out.println("--------------------------------");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Thread.sleep(10000);
		driver.findElement(By.xpath("//*/em[@class='icon-grid']")).click();

		driver.findElement(By.partialLinkText("Merchandising")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*/a[@title='Product']/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*/ul/li/a[@href='#/merchandising/product-master']//span")).click();

	}

	@Test(priority = 4)
	public void clickOn_NewButton() throws InterruptedException {
		System.out.println("--------------------------------");

		Thread.sleep(5000);
		System.out.println("---Click on NEW Button->Navigate To create New Product---");
		driver.findElement(By.xpath("//button[@type='button']/span[text()='New']")).click();
		Thread.sleep(3000);
		var pageText = driver.findElement(By.xpath("//h3/small[@class='ng-binding']")).getText();

		if (pageText.equals("Create New Product")) {
			System.out.println("Page Text is -> " + pageText);
			System.out.println("upon click, New BUTTON works fine");

		}

		else {
			System.out.println("Page Text is -> " + pageText);
			System.out.println("*NEW* button Not working");
		}

	}

	@Test(priority = 5)
	public void GetAvailable_productDetailsAccordions() {
		System.out.println("--------------------------------");

		driver.findElement(By.xpath("//label[@class='ng-binding']//span[@class='fa fa-check']")).click();
		List<WebElement> productDetails = driver.findElements(By.xpath("//*/div[@class='panel-group']/div/div[1]"));

		for (int i = 0; i < productDetails.size(); i++) {
			var tempText = productDetails.get(i).getText().replaceAll("\\(", "-");
			var accordionText = tempText.split("-")[0];
			System.out.println("Option " + i + " = " + accordionText);

		}

	}

	@Test(priority = 6)
	public void Verify_AccordionsOpen_OneAt_Atime() throws InterruptedException {
		System.out.println("--------------------------------");

		System.out.println("->Verify Click Action to close/open Accordion(s)");
		for (int j = 1; j <= 4; j++) {
			Thread.sleep(2000);
			var tempText = driver.findElement(By.xpath("//*/div[@class='panel-group']/div[" + j + "]/div[1]/h4/a/span"))
					.getText().replaceAll("\\(", "-");

			var AccordionName = tempText.split("-")[0];

			System.out.println("--Executing for Accordion -> " + AccordionName);

			driver.findElement(By.xpath("//*/div[@class='panel-group']/div[" + j + "]/div[1]/h4/a/span")).click();

			if (j == 1) {
				driver.findElement(By.xpath("//*/div[@class='panel-group']/div[1]/div[1]/h4/a/span")).click();
				var elementVisible = driver.findElement(
						By.xpath("//*/div[@class='panel-group']//button[contains(normalize-space(),'Cost')]"));
				elementVisible.isDisplayed();
			}

			if (j == 2) {
				var elementVisible = driver.findElement(By.xpath("//*/label/span[text()='Multiples Of']"));
				elementVisible.isDisplayed();
			}

			if (j == 3) {
				var elementVisible = driver.findElement(
						By.xpath("//*/label/span[contains(normalize-space(),'Select Activation Option')]"));
				elementVisible.isDisplayed();
			}

			if (j == 4) {
				var elementVisible = driver
						.findElement(By.xpath("//*/div[@class=\"panel-body\"]//div[@id=\"fileupload\"]"));
				elementVisible.isDisplayed();
			}

		}

		// close product master ribbon
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*/a[@title='Product']/span")).click();
	}

}
