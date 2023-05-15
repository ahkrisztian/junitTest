import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;
import de.codingsolo.selenium.configuration.Config;
import de.codingsolo.selenium.configuration.DriverHelper;
import de.codingsolo.selenium.pages.*;

@RunWith(Parameterized.class)
public class TestAufrufParameterizedFireFox2 {
	
	////div[not(contains(@class, 'AdHolder')) and @data-component-type='s-search-result']
	
	WebDriver driver;
	
	String browsername;
	String userName;
	String password;
	String betreff;
	String name;
	String kursTitel;
	String assert1;
	String assert2;
	
	
	
	public TestAufrufParameterizedFireFox2(String testName, String browserName, String userName, String password, String betreff,
			String name, String kursTitel, String assert1, String assert2) {

		this.browsername = browserName;
		this.userName = userName;
		this.password = password;
		this.betreff = betreff;
		this.name = name;
		this.kursTitel = kursTitel;
		this.assert1 = assert1;
		this.assert2 = assert2;
	}

	@Before
	public void initTests() throws Exception {
		
		driver = DriverHelper.getDriver(browsername);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get(Config.getBaseUrl());
	}
	
	@After
	public void afterTests() {
		driver.close();
	}
	
	@Test
	public void testLogin() {
		//Arrange
		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		loginPage.zugagnsdateEingeben(userName, password);
		
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
		
		assertEquals(expect, assert1);
	}
	
	@Test
	public void navigation_SeleniumTestForm1() {
		//Arrange
		Login();
		
		LinkHerstellenUndZumLinkNavigieren(kursTitel);
		
		//Act
		String expect = driver.findElement(By.className("documentFirstHeading")).getText();
		
		//Assert
		assertEquals(expect, kursTitel);
		
	}
	
	@Test
	public void seleniumTestForm1_Ausfullen() {
		//Arrange
		
		//Login
		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		loginPage.zugagnsdateEingeben(userName, password);
		
		//Act
		loginPage.loginBtnAnClicken();
		
		//Navigation zum Formular
		LinkHerstellenUndZumLinkNavigieren(kursTitel);
		
		SeleniumTestFormPage testFormPage = new SeleniumTestFormPage(driver);
		
		//Name eingeben		
		testFormPage.betreffEingeben(betreff);
		testFormPage.nameEingeben(name);
		
		//Kurs auswohlen
		testFormPage.kursAuswaheln(assert2);
		
		//Firmen auswohlen
		testFormPage.firmaInBoxAuswahlen(new int[] {2,4,6});
		
		
		//Firmen hinzufugen
		testFormPage.firmenUbernehmen();
		
		testFormPage.firmaInBox2Auswahlen(2);
		
		testFormPage.ausgewahlteFirmenVerschiben();
		
		//Act
		testFormPage.formularSpeichern();
		
		//Assert
		
		String expectMeldung = 	testFormPage.statusMeldung();
		assertTrue(expectMeldung.contains(assert2));
		
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
	
	@Parameters(name = "{0}")
	public static Collection<Object[]> providerTestData()throws Exception{
		
		Collection<Object[]> collection;
		
		Object[][]daten = {
				{"Test From 1 Test 1 Firefox", "firefox", "selenium101", "codingsolo", "Manuell", "Erik", "Selenium Test Form1",
					"Lokatoren Test Tabelle", "Java Grundlagen Kurs mit Dieter"},
				{"Test From 1 Test 2 Firefox", "firefox", "selenium101", "codingsolo", "Manuell", "Erik", "Selenium Test Form1",
						"Lokatoren Test Tabelle", "Python Grundlagen Kurs mit Dieter"}
		};
		
		List<Object[]> listObject = Arrays.asList(daten);
		collection = new ArrayList<Object[]>(listObject);
		
		return collection;
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
