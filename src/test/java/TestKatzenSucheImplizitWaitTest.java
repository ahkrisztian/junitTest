import static org.junit.Assert.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.codingsolo.selenium.pages.SeleniumLoginPage;

public class TestKatzenSucheImplizitWaitTest {

	WebDriver driver;
	
	@Before
	public void initTests() {
		
		System.out.println("Initialisiere Webdriver");
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		driver.get("https://seleniumkurs.codingsolo.de");
	}
	
	@After
	public void afterTests() {
		//driver.close();
	}
	
	@Test
	public void testImplizitWait() {
		
		//Arrange
		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		loginPage.zugagnsdateEingeben("selenium101", "codingsolo");
		
		loginPage.loginBtnAnClicken();
		
		SeleniumHomePage homePage = new SeleniumHomePage(driver);				
		homePage.linkAuswählen("Katzensuche Testseite (AJAX)");
		
		SeleniumKetzenSuchenTest katzenSuche = new SeleniumKetzenSuchenTest(driver);
		String beschreibung = katzenSuche.beschreibungAuslesen();
		
		String srcImgLinkKatze1 = katzenSuche.srcLinkImgKatze1();
		
		//Act
		katzenSuche.nextPage();
		katzenSuche.selecAnzahlEingabe("6");
		
		String srcImgLinkKatze2 = katzenSuche.srcLinkImgKatze2();
		
		//Arrange
		assertTrue(beschreibung.contains("Lieblingskatze"));
		assertTrue(srcImgLinkKatze1.contains("ECqe13G5B"));
		assertTrue(srcImgLinkKatze2.contains("5qNv0jHXW"));
	}
	
	@Test
	public void testExplizitWait() {
		
		//Arrange
		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		loginPage.zugagnsdateEingeben("selenium101", "codingsolo");
		
		loginPage.loginBtnAnClicken();
		
		
		SeleniumHomePage homePage = new SeleniumHomePage(driver);				
		homePage.linkAuswählen("Katzensuche Testseite (AJAX)");
		
		SeleniumKetzenSuchenTest katzenSuche = new SeleniumKetzenSuchenTest(driver);
		String beschreibung = katzenSuche.beschreibungAuslesen();
		
		//Act
		katzenSuche.sortierungEingabe("Asc");
		String srcLinkKatze3 = katzenSuche.srcLinkImgKatzeh5();
		
		//Arrange
		assertTrue(beschreibung.contains("Lieblingskatze"));
		assertTrue(srcLinkKatze3.contains("gt"));
	}
}
