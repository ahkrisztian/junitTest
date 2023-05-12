package de.codingsolo.selenium.configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum AvailableBrowser {

	FIREFOX{
		
		@Override
		public WebDriver createDriver() {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			WebDriver driver = new FirefoxDriver();
			
			return driver;
		}
	},
	
	CHROME{
		
		@Override
		public WebDriver createDriver() {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			WebDriver driver = new FirefoxDriver();
			
			return driver;
		}
	};
	
	public abstract WebDriver createDriver();
}
