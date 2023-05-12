import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import de.codingsolo.selenium.configuration.Config;
import de.codingsolo.selenium.configuration.DriverHelper;
import de.codingsolo.selenium.pages.*;

public class TestWebShop {

	WebDriver driver;
	
	@Before
	public void initTests() throws Exception {
		
		//System.out.println("Initialisiere Webdriver");
		//System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		
		
		//FirefoxOptions firefoxOptions = new FirefoxOptions();
		//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
		
		//driver = new FirefoxDriver();
		
		//driver.get("https://seleniumkurs.codingsolo.de");
		driver = DriverHelper.getDriver("firefox");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get(Config.getBaseUrl());
	}
	
	@After
	public void afterTests() {
		driver.quit();
	}
	
	@Test
	public void BestellVomWebShop() {
		
		//Login
		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		loginPage.zugagnsdateEingeben("selenium101", "codingsolo");
		
		loginPage.loginBtnAnClicken();
		
		//Navigation zum Webshop
		SeleniumHomePage homePage = new SeleniumHomePage(driver);			
		homePage.linkAuswahlen("Webshop Testseite");
		
		SeleniumWebShopTest webShopTest = new SeleniumWebShopTest(driver);
		
		//Suche nach Querlenker
		webShopTest.inputSucheEingeben("Querlenker");
		
		//Suchen button Klick
		webShopTest.btnSucheKlick();
		
		//Increment stück
		webShopTest.produktAnzhalAuswahlen("Querlenker Satz Spurstange Audi", 3);
		
		//In Warenkorb	
		webShopTest.inWarenkorn();
		
		//Warenkorb öffnen
		webShopTest.warenkorbOffnen();
		
		//Zur Kasse Klick
		webShopTest.zurKasseKlick();
		
		//PromoCode eingeben und Aktivieren
		
		SeleniumWebShopKasseTest kasseTest = new SeleniumWebShopKasseTest(driver);
		
		kasseTest.promoCodeEingeben("codingsolo");
		kasseTest.promoCodeAktivieren();
		
		String expectPromo = kasseTest.promoInfoAuslesen();
		String expectSumme = kasseTest.summe();
		
		kasseTest.bestellen();
		
		kasseTest.selectLandDropDown("Togo");
		kasseTest.agbAkzeptieren();
		kasseTest.weiterKlick();
		
		String danke = driver.findElement(By.className("wrapperTwo")).getText();
		
		//Assert
		assertTrue(danke.contains("Dank"));
		assertTrue(expectPromo.contains("aktiviert"));
		assertEquals(expectSumme, "215.1");
	}
}
