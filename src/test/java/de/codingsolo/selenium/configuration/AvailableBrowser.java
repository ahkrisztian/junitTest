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
		    FirefoxOptions firefoxOptions = new FirefoxOptions();

		    try {
		        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
		        //return new FirefoxDriver();
		    } catch (Exception e) {
		    	
		        e.printStackTrace();
		    }

		    throw new RuntimeException("Failed to create RemoteWebDriver");
		}
	},
	
	CHROME{
		
		@Override
		public WebDriver createDriver() {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		    FirefoxOptions firefoxOptions = new FirefoxOptions();

		    try {
		        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
		    	//return new FirefoxDriver();

		    } catch (Exception e) {
		    	
		        e.printStackTrace();
		    }

		    
		    throw new RuntimeException("Failed to create RemoteWebDriver");
		}
	};
	
	public abstract WebDriver createDriver();
}
