package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {

	private static WebDriver driver;
	private static Properties prop;

	public static WebDriver getDriver() {

		/*
		 * checks if the driver instance is null, it creates a new WebDriver instance.
		 */

		if (driver == null) {
			prop = new Properties();
			try {
				prop.load(new FileInputStream("src/main/resources/config.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			String browser = prop.getProperty("browser");

			// Using webdriver according to the property file value

			switch (browser) {
			case "chrome":
				driver = new ChromeDriver();
				break;

			case "firefox":
				driver = new FirefoxDriver();
				break;

			case "edge":
				driver = new EdgeDriver();
				break;

			default:
				throw new IllegalArgumentException("Invalid browser name in config.properties.");
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

			String url = prop.getProperty("url");
			driver.get(url);

		}

		return driver;

	}

	public static void takeScreenshot(String screenshotName) {
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// lets wait for sometime.. why so hurry
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().fullscreen();

		try {
			FileUtils.copyFile(screenshotFile, new File("screenshot\\" + screenshotName + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// adding the read property method if needed for future use

	public static void loadProperties() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("src/main/resources/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Get the URL from the property file
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

}
