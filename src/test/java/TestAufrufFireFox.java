import static org.junit.Assert.*;
import java.net.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import de.codingsolo.selenium.configuration.Config;
import de.codingsolo.selenium.configuration.DriverHelper;
import de.codingsolo.selenium.pages.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;
import org.openqa.selenium.remote.*;

public class TestAufrufFireFox {
	
	////div[not(contains(@class, 'AdHolder')) and @data-component-type='s-search-result']
	
	WebDriver driver;
	
	@Before
	public void initTests() throws Exception {
		
		//System.out.println("Initialisiere Webdriver");
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		
		//docker run -d -p 4444:4444 --shm-size="2g" selenium/standalone-firefox
		
		//FirefoxOptions firefoxOptions = new FirefoxOptions();
		
		//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
		
		//driver = new FirefoxDriver();
		//river.get("https://seleniumkurs.codingsolo.de");
		
		driver = DriverHelper.getDriver("firefox");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get(Config.getBaseUrl());
	}
	
	@After
	public void afterTests() throws Exception {
		if(driver != null) {
			driver.close();
		}
	}
	
	@Test
	public void testLogin() {
		//Arrange
		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		loginPage.zugagnsdateEingeben("selenium101", "codingsolo");
		
		//Act
		loginPage.loginBtnAnClicken();
		
		//Assert
		String statusMeldung = loginPage.statusMeldungAuslesen();
		assertTrue(statusMeldung.contains("Willkommen"));
		
	}

	@Test
	public void navigation_LokatorenTestTablle() {
		//Arrange
		
		Login();
		
		//Act
		
		LinkHerstellenUndZumLinkNavigieren("Lokatoren Test Tabelle");
		
		//Assert
		
		String expect = driver.findElement(By.className("documentFirstHeading")).getText();
		
		assertEquals(expect, "Lokatoren Test Tabelle");
	}
	
	@Test
	public void navigation_SeleniumTestForm1() {
		//Arrange
		Login();
		
		LinkHerstellenUndZumLinkNavigieren("Selenium Test Form1");
		
		//Act
		String expect = driver.findElement(By.className("documentFirstHeading")).getText();
		
		//Assert
		assertEquals(expect, "Selenium Test Form1");
		
	}
	
	@Test
	public void seleniumTestForm1_Ausfullen() {
		//Arrange
		
		//Login
		Login();
		
		//Navigation zum Formular
		LinkHerstellenUndZumLinkNavigieren("Selenium Test Form1");
		
		SeleniumTestFormPage testFormPage = new SeleniumTestFormPage(driver);
		
		//Name eingeben		
		testFormPage.betreffEingeben("Manuell");
		testFormPage.nameEingeben("Erik");
		
		//Kurs auswahlen
		testFormPage.kursAuswaheln("Java Grundlagen Kurs mit Dieter");
		
		//Firmen auswahlen
		testFormPage.firmaInBoxAuswahlen(new int[] {2,4,6});
		
		
		//Firmen hinzufugen
		testFormPage.firmenUbernehmen();
		
		
		testFormPage.firmaInBox2Auswahlen(2);
		
		testFormPage.ausgewahlteFirmenVerschiben();
		
		//Act
		testFormPage.formularSpeichern();
		
		//Assert
		
		String expectMeldung = 	testFormPage.statusMeldung();
		assertTrue(expectMeldung.contains("Java Grundlagen"));
		
		String erstesElement = testFormPage.erstesElemenAuslesen();
		assertEquals(erstesElement, "Magazzini Alimentari Riuniti");
	}
	
	@Test
	public void dragAndDropTest() {
		//Arrange
		
		//Login und Navigation zum Drag and Drop Test
		Login();
		LinkHerstellenUndZumLinkNavigieren("Drag and Drop Beispiel");
		
		//Act
		SeleniumDragAndDrop draganddrop = new SeleniumDragAndDrop(driver);
		draganddrop.dragAndDropLogos();
		
		//Assert
		String expectMeldung = draganddrop.Meldung();		
		assertEquals(expectMeldung, "blue-logo abgelegt!");
	}
	
	@Test
	public void testRadioButton() {
		
		//Arrange
		Login();
		LinkHerstellenUndZumLinkNavigieren("Web Elemente");
		
		SeleniumRadioButtonTest radioBtnTest = new SeleniumRadioButtonTest(driver);
			
		//Arrange
		
		radioBtnTest.checkBox1Click();		
		String checkedRadioButton1 = radioBtnTest.radioBtn1Attribute();
		assertEquals(checkedRadioButton1, "radio1");
		
		radioBtnTest.checkBox2Click();	
		String checkedRadioButton2 = radioBtnTest.radioBtn2Attribute();
		assertEquals(checkedRadioButton2, "radio2");
		
		radioBtnTest.checkBox3Click();	
		String checkedRadioButton3 = radioBtnTest.radioBtn3Attribute();
		assertEquals(checkedRadioButton3, "radio3");
		
	}
	
	@Test
	public void checkBoxTest() {
		//Arrange
		Login();
		LinkHerstellenUndZumLinkNavigieren("Web Elemente");
		
		SeleniumCheckBoxTest checkBoxTest = new SeleniumCheckBoxTest(driver);
		
		//Act
		checkBoxTest.checkBox1Click();
		checkBoxTest.checkBox1Click();
		
		checkBoxTest.checkBox2Click();
		
		checkBoxTest.checkBox3Click();
		
		//Assert
		assertEquals(checkBoxTest.checkBox1Status(), false);
		assertEquals(checkBoxTest.checkBox2Status(), true);
		assertEquals(checkBoxTest.checkBox3Status(), true);
	}
	
	@Test
	public void iFrameTest() {
		//Arrange
		Login();
		LinkHerstellenUndZumLinkNavigieren("IFrame Beispiel");
		
		SelniumIFrameTest iframetest = new SelniumIFrameTest(driver);
		
		//Act
		
		iframetest.wechselFrame();
		iframetest.nameundClickBtnAlert("Krisztian");
		
		//
		assertTrue(iframetest.alarmnachricht().contains("Krisztian"));
		
		iframetest.alarmNachrichtSchliessen();
				
	}
	
	private void Login() {
		
		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		loginPage.zugagnsdateEingeben("selenium101", "codingsolo");
		
		loginPage.loginBtnAnClicken();
		
	}
	
	private void LinkHerstellenUndZumLinkNavigieren(String linkName) {
		
		SeleniumHomePage homePage = new SeleniumHomePage(driver);
		
		
		homePage.linkAuswahlen(linkName);
	}

}
