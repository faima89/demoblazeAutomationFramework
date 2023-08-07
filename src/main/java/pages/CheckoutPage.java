package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

	private WebDriver driver; // changed here

	@FindBy(xpath = "//button[contains(text(), 'Place Order')]")
	private WebElement placeOrder;

	@FindBy(id = "name")
	private WebElement nameInput;

	@FindBy(id = "country")
	private WebElement countryInput;

	@FindBy(id = "city")
	private WebElement cityInput;

	@FindBy(id = "card")
	private WebElement cardNumberInput;

	@FindBy(id = "year")
	private WebElement yearInput;

	@FindBy(id = "month")
	private WebElement monthInput;

	@FindBy(xpath = "//button[contains(text(), 'Purchase')]")
	private WebElement purchaseButton;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void placeOrder() {
		placeOrder.click();
	}

	public void enterName(String name) {
		nameInput.sendKeys(name);
	}

	public void enterCountry(String country) {
		countryInput.sendKeys(country);
	}

	public void enterCity(String city) {
		cityInput.sendKeys(city);
	}

	public void enterCardNumber(String cardNumber) {
		cardNumberInput.sendKeys(cardNumber);
	}

	public void enterYear(String year) {
		yearInput.sendKeys(year);
	}

	public void enterMonth(String month) {
		monthInput.sendKeys(month);
	}

	public void clickPurchaseButton() {
		purchaseButton.click();
	}
}
