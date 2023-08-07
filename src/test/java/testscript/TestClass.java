package testscript;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductPage;

public class TestClass {

	public static void main(String[] args) throws InterruptedException {


		WebDriver driver;
		ProductPage productPage;
		CheckoutPage checkoutPage;
		CartPage cartPage;

		driver = BaseTest.getDriver();
		productPage = new ProductPage(driver);
		
		productPage.countProduct();
		productPage.openProduct();

		// Navigate to the cart page
		driver.get("https://www.demoblaze.com/cart.html");

		// Create a CartPage instance using Page Factory
		cartPage = new CartPage(driver);

		List<WebElement> titleElements = cartPage.getTitleElements();
		List<WebElement> deleteLinks = cartPage.getDeleteLinks();

		// Get initial row count
		int rowCount = cartPage.countrows();
		System.out.println("Number of product rows: " + rowCount);

		// Loop through the products and delete all except "Samsung galaxy s6"
		int i = 0;
		while (i < rowCount) {
			// String productTitle = cartPage.getTitleElement(i).getText();
			String productTitle = titleElements.get(i).getText();
			System.out.println(i + ": " + productTitle);

			if (!productTitle.equals("Samsung galaxy s6")) {
				// cartPage.getDeleteLink(i).click();
				deleteLinks.get(i).click();
				// Pause briefly to allow the cart to update
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				rowCount--;
			} else {
				i++;
			}
		}

		// Verify that only "Samsung galaxy s6" is left in the cart
		boolean allProductsDeleted = true;
		rowCount = cartPage.countrows();
		for (i = 0; i < rowCount; i++) {
			// String productTitle = cartPage.getTitleElement(i).getText();
			String productTitle = titleElements.get(i).getText();
			System.out.println(i + ": " + productTitle);

			if (!productTitle.equals("Samsung galaxy s6")) {
				allProductsDeleted = false;
				break;
			}
		}

		if (allProductsDeleted) {
			System.out.println("All products except 'Samsung galaxy s6' have been successfully deleted.");
		} else {
			System.out.println("Failed to delete all products except 'Samsung galaxy s6'.");
		}

		// placing order

		// Create a CheckoutPage instance using Page Factory
		checkoutPage = new CheckoutPage(driver);

		checkoutPage.placeOrder();

		// Input valid data for checkout
		checkoutPage.enterName("John Doe");
		checkoutPage.enterCountry("United States");
		checkoutPage.enterCity("New York");
		checkoutPage.enterCardNumber("1234567890123456");
		checkoutPage.enterYear("2025");
		checkoutPage.enterMonth("12");

		checkoutPage.clickPurchaseButton();

		BaseTest.takeScreenshot("TestScreenShot");

		driver.close();

	}

}
