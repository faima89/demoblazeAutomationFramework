package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

	private WebDriver driver;
	private Properties prop;  // changed here

	@FindBy(id = "tbodyid")
	private WebElement parentProductDiv;

	@FindBy(xpath = "//a[contains(@href, '?idp_=%s')]")
	private WebElement productLink;

	@FindBy(xpath = "//a[text() = 'Add to cart']")
	private WebElement addToCartButton;

	// the constructor
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public int countProduct() {
		WebElement inventory = parentProductDiv;
		ArrayList<WebElement> items = (ArrayList<WebElement>) inventory.findElements(By.className("col-lg-4"));
		return items.size();
	}

	public void addToCart() throws InterruptedException {
		addToCartButton.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		Alert alert = wait.withTimeout(Duration.ofSeconds(2)).until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

	public void openProduct() throws InterruptedException {

		for (int i = 1; i <= countProduct(); i++) {
			String xpathExpression = String.format("//a[contains(@href, '?idp_=%s')]", i);
			WebElement productLink = driver.findElement(By.xpath(xpathExpression));
			productLink.click();
			addToCart();
			driver.get(BaseTest.getProperty("url"));

		}

	}

}
