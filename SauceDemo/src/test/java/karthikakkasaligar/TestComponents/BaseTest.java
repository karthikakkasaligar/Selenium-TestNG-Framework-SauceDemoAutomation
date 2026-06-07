package karthikakkasaligar.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import karthikakkasaligar.pageobjectmodel.LoginPage;

public class BaseTest {
	
	public WebDriver driver;
	public LoginPage login;

	public WebDriver intilizedriver() throws IOException {


		Properties properties = new Properties();
		FileInputStream inpuststream = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\karthikakkasaligar\\GlobalData\\GlobalProperties.properties");	
		properties.load(inpuststream);
		String browserName = properties.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--incognito");
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--incognito");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	
	@BeforeMethod
	public LoginPage lunchapplication() throws IOException
	{
		 driver= intilizedriver();
		 login = new LoginPage(driver);
		 login.goToURL();
		 return login;	 
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	

}
