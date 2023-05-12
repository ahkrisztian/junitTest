import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import de.codingsolo.selenium.configuration.Config;
import de.codingsolo.selenium.configuration.DriverHelper;
import de.codingsolo.selenium.pages.SeleniumLoginPage;

@RunWith(Parameterized.class)
public class SeleniumTestForm3Test {
	
	WebDriver driver;
	
	String browsername;
	String userName;
	String password;
	String bezeichnung;
	String kennNummer;
	String anschfrift;
	String telefon;
	String hausUndStrasse;
	String postleitzahl;
	String ort;
	String auswahl;
	String name;
	String vorname;
	String geburtsDatum;
	String telefon2;
	String hausUndStrasse2;
	String postleitzahl2;
	String ort2;
	
	String assert1;
	String assert2;

	
	
	public SeleniumTestForm3Test(String testName, String browsername, String userName, String password,
			String bezeichnung, String kennNummer,
			String anschfrift, String telefon, String hausUndStrasse, String postleitzahl, String ort, String auswahl,
			String name, String vorname, String geburtsDatum, String telefon2, String hausUndStrasse2,
			String postleitzahl2, String ort2, String assert1, String assert2) {
		this.browsername = browsername;
		this.userName = userName;
		this.password = password;
		this.bezeichnung = bezeichnung;
		this.kennNummer = kennNummer;
		this.anschfrift = anschfrift;
		this.telefon = telefon;
		this.hausUndStrasse = hausUndStrasse;
		this.postleitzahl = postleitzahl;
		this.ort = ort;
		this.auswahl = auswahl;
		this.name = name;
		this.vorname = vorname;
		this.geburtsDatum = geburtsDatum;
		this.telefon2 = telefon2;
		this.hausUndStrasse2 = hausUndStrasse2;
		this.postleitzahl2 = postleitzahl2;
		this.ort2 = ort2;
		
		this.assert1 = assert1;
		this.assert2 = assert2;
	}


	@Before
	public void initTests() throws Exception {
			
		driver = DriverHelper.getDriver(browsername);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.get(Config.getBaseUrl());
	}
	
	@After
	public void afterTests() {
		driver.close();
	}
	
	@Test
	public void testFormAusfüllen() {
		
		//Arrange
		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		loginPage.zugagnsdateEingeben(userName, password);				
		loginPage.loginBtnAnClicken();
				
		SeleniumHomePage homePage = new SeleniumHomePage(driver);
								
		homePage.linkAuswählen("Testform3 DataDriven");
				
		SeleniumTestForm3Page testForPage = new SeleniumTestForm3Page(driver);
				
		//Act
		testForPage.bezeichnungEingeben(bezeichnung);
		testForPage.kennNummerEingeben(kennNummer);
		testForPage.anschfriftEingeben(anschfrift);
		testForPage.telefonEingeben(telefon);
		testForPage.hausUndStrasseEingeben(hausUndStrasse);
		testForPage.postleitzahlEingeben(postleitzahl);
		testForPage.ortEingeben(ort);
		testForPage.auswahlEingeben(auswahl);
		testForPage.nameEingeben(name);
		testForPage.vornameEingeben(vorname);
		testForPage.geburtsDatumEingeben(geburtsDatum);
		testForPage.telefon2Eingeben(telefon2);
		testForPage.hausUndStrasse2Eingeben(hausUndStrasse2);
		testForPage.postleitzahl2Eingeben(postleitzahl2);
		testForPage.ort2Eingeben(ort2);
		
		testForPage.speicherAnklick();
		
		//Assert
		assertTrue(testForPage.statusAuslesen().contains(assert1));
		assertEquals(testForPage.formularNameAuslesen(), assert2);
	}
	
	@Parameters(name = "{0}")
	public static Collection<Object[]> providerTestData()throws Exception{
		
		Collection<Object[]> collection;
		
//		Object[][]daten = {
//				{
//					"Test From 1 Test 1 Firefox", "firefox", "selenium101", "codingsolo", "Testform3 Fragebogen",
//				    "0100", "AOK Hamburg","0144455566", "Berg Str. 1","30555","Hamburg","Arbeitnehmer",
//				    "Arbeiter","Hans","01.01.1900","01888999777","Mulx Str. 22","22222","Berlin"					
//				},
//				{
//					"Test From 2 Test 2 Firefox", "firefox", "selenium101", "codingsolo", "Testform3 Fragebogen",
//				    "0100", "AOK Hamburg","0144455566", "Berg Str. 1","30555","Hamburg","Arbeitnehmer",
//				    "Arbeiter","Hans","01.01.1900","01888999777","Mulx Str. 22","22222","Berlin"					
//				}
//		};
		
		//List<Object[]> listObject = Arrays.asList(daten);
		ApachePOI excelReader = new ApachePOI();
		
		List<Object[]> listObject = excelReader.getExcelData("C:\\Users\\lunar\\Downloads\\ApachePOIExample+Lektion+-+166\\datadriven-master\\TestCaseTestform3.xlsx");
		collection = new ArrayList<Object[]>(listObject);
		
		return collection;
	}

}
