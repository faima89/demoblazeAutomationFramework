package steps;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import io.cucumber.java.en.*;
import pages.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductPage;

public class StepDefination {

	private WebDriver driver;
	private ProductPage productPage;
	private CartPage cartPage;
	private CheckoutPage checkoutPage;
	

	@Given("I am on the product page")
	public void iAmOnTheProductPage() {
		driver = BaseTest.getDriver();
		productPage = new ProductPage(driver);

	}

	@Then("I add all the product to the cart")
	public void addingProductToCart() {
		productPage.countProduct();
		try {
			productPage.openProduct();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Then("The cart should contain the added products")
    public void theCartShouldContainTheAddedProducts() throws InterruptedException {
        // Verify that the products are added to the cart
        cartPage = new CartPage(driver);
        int productCount = cartPage.countrows();

        Assert.assertTrue(productCount > 0, "Products have been successfully added to the cart.");
        driver.quit();
    }

	@Given("I am on the cart page")
	public void iAmOnTheCartPage() {
		driver = BaseTest.getDriver();
		cartPage = new CartPage(driver);
		driver.get(BaseTest.getProperty("cartPage"));
	}

	@When("I delete all products from the cart except {string}")
	public void iDeleteAllProductsFromTheCartExcept(String productTitle) throws InterruptedException {
		List<WebElement> titleElements = cartPage.getTitleElements();
		List<WebElement> deleteLinks = cartPage.getDeleteLinks();
		int rowCount = cartPage.countrows();

		int i = 0;
		while (i < rowCount) {
			String title = titleElements.get(i).getText();

			if (!title.equals(productTitle)) {
				deleteLinks.get(i).click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				rowCount--;
			} else {
				i++;
			}
		}
	}

	@Then("Only {string} should be left in the cart")
	public void onlyShouldBeLeftInTheCart(String expectedProduct) throws InterruptedException {
		boolean allProductsDeleted = true;
		List<WebElement> titleElements = cartPage.getTitleElements();
		int rowCount = cartPage.countrows();

		for (int i = 0; i < rowCount; i++) {
			String productTitle = titleElements.get(i).getText();
			if (!productTitle.equals(expectedProduct)) {
				allProductsDeleted = false;
				break;
			}
		}

		if (allProductsDeleted) {
			System.out.println("All products except '" + expectedProduct + "' have been successfully deleted.");
		} else {
			System.out.println("Failed to delete all products except '" + expectedProduct + "'.");
		}

	}

	@When("I place an order with {string}, {string}, {string}, {string}, {string}, and {string}")
	public void iPlaceAnOrderWithAnd(String name, String country, String city, String cardNumber, String year,
			String month) {
		checkoutPage = new CheckoutPage(driver);

		checkoutPage.placeOrder();
		checkoutPage.enterName(name);
		checkoutPage.enterCountry(country);
		checkoutPage.enterCity(city);
		checkoutPage.enterCardNumber(cardNumber);
		checkoutPage.enterYear(year);
		checkoutPage.enterMonth(month);
		checkoutPage.clickPurchaseButton();

		BaseTest.takeScreenshot("CheckOut");
		
		//System.out.println(purchaseSuccess);
	}
	
	
	
	@Then("I validate the output with {string}")
    public void datavalidation(String validData) {
       
		boolean expectedSuccess = Boolean.parseBoolean(validData);
		boolean purchaseSuccess = cartPage.isPurchaseMessageDisplayed(); 
		Assert.assertEquals(purchaseSuccess, expectedSuccess);
		

    }
	
	
	

}
