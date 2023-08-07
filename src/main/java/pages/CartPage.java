package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	private WebDriver driver; // changed here

	@FindBy(id = "tbodyid")
	private WebElement parentRowTable;

	@FindBy(xpath = "//tbody[@id='tbodyid']/tr//td[2]")
	private List<WebElement> titleElements;

	@FindBy(xpath = "//tbody[@id='tbodyid']/tr//td[4]/a")
	private List<WebElement> deleteLinks;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public int countrows() throws InterruptedException {
		Thread.sleep(7000);
		WebElement rowNumber = parentRowTable;
		ArrayList<WebElement> items = (ArrayList<WebElement>) rowNumber.findElements(By.className("success"));
		return items.size();
	}

	public List<WebElement> getDeleteLinks() {
		return deleteLinks;
	}

	public List<WebElement> getTitleElements() {
		return titleElements;
	}
	
	public boolean isPurchaseMessageDisplayed() {
            WebElement purchaseMessageElement = driver.findElement(By.xpath("//h2[text()='Thank you for your purchase!']"));
            return purchaseMessageElement.isDisplayed();
       
    }


}
