package de.codingsolo.selenium.configuration;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public enum AvailableBrowser {

	FIREFOX{
		
		@Override
		public WebDriver createDriver() {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			//WebDriver driver = new FirefoxDriver();
			
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			WebDriver driver = null;
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return driver;
		}
	},
	
	CHROME{
		
		@Override
		public WebDriver createDriver() {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			//WebDriver driver = new FirefoxDriver();
			
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			WebDriver driver = null;
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
				
				return driver;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return driver;
		}
	};
	
	public abstract WebDriver createDriver();
}
