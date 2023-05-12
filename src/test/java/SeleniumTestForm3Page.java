import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTestForm3Page {

	WebDriver driver;
	
	@FindBy(id = "form-widgets-bezeichnung")
	private WebElement bezeichnung;
	
	@FindBy(id = "form-widgets-kennung")
	private WebElement kennNummer;
	
	@FindBy(id = "form-widgets-anschrift")
	private WebElement anschfrift;
	
	@FindBy(id = "form-widgets-telefon")
	private WebElement telefon;
	
	@FindBy(id = "form-widgets-str")
	private WebElement hausUndStrasse;
	
	@FindBy(id = "form-widgets-plz")
	private WebElement postleitzahl;
	
	@FindBy(id = "form-widgets-ort")
	private WebElement ort;
	
	@FindBy(id = "form-widgets-auswahl1")
	private WebElement auswahl;
	
	@FindBy(id = "form-widgets-name")
	private WebElement name;
	
	@FindBy(id = "form-widgets-vorname")
	private WebElement vorname;
	
	@FindBy(id = "form-widgets-geburt")
	private WebElement geburtsDatum;
	
	@FindBy(id = "form-widgets-telefonv")
	private WebElement telefon2;
	
	@FindBy(id = "form-widgets-strv")
	private WebElement hausUndStrasse2;
	
	@FindBy(id = "form-widgets-plzv")
	private WebElement postleitzahl2;
	
	@FindBy(id = "form-widgets-ortv")
	private WebElement ort2;
	
	@FindBy(name = "form.buttons.speichern")
	private WebElement btnSpeichern;
	
	@FindBy(id = "message")
	private WebElement textStatus;
	
	@FindBy(xpath = "//*[@id='auswahl']//li[1]")
	private WebElement textErsteElement;

	public SeleniumTestForm3Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void bezeichnungEingeben(String bezeichnung) {
		this.bezeichnung.sendKeys(bezeichnung);
	}
	
	public void kennNummerEingeben(String kennNummer) {
		this.kennNummer.sendKeys(kennNummer);
	}
	
	public void anschfriftEingeben(String anschfrift) {
		this.anschfrift.sendKeys(anschfrift);
	}
	
	public void telefonEingeben(String telefon) {
		this.telefon.sendKeys(telefon);
	}
	
	public void hausUndStrasseEingeben(String hausUndStrasse) {
		this.hausUndStrasse.sendKeys(hausUndStrasse);
	}
	
	public void postleitzahlEingeben(String postleitzahl) {
		this.postleitzahl.sendKeys(postleitzahl);
	}
	
	public void ortEingeben(String ort) {
		this.ort.sendKeys(ort);
	}
	
	public void auswahlEingeben(String auswahl1) {
		Select statusAuswählen = new Select(auswahl);
		statusAuswählen.selectByValue(auswahl1);
		
	}
	
	
	public void nameEingeben(String name) {
		this.name.sendKeys(name);
	}
	
	public void vornameEingeben(String vorname) {
		this.vorname.sendKeys(vorname);
	}
	
	public void geburtsDatumEingeben(String geburtsDatum) {
		this.geburtsDatum.sendKeys(geburtsDatum);
	}
	
	public void telefon2Eingeben(String telefon2) {
		this.telefon2.sendKeys(telefon2);
	}
	
	public void hausUndStrasse2Eingeben(String hausUndStrasse2) {
		this.hausUndStrasse2.sendKeys(hausUndStrasse2);
	}
	
	public void postleitzahl2Eingeben(String postleitzahl2) {
		this.postleitzahl2.sendKeys(postleitzahl2);
	}
	
	public void ort2Eingeben(String ort2) {
		this.ort2.sendKeys(ort2);
	}
	
	public void speicherAnklick() {
		btnSpeichern.click();
	}
	
	public String statusAuslesen() {
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(textStatus));

		return textStatus.getText();
	}
	
	public String formularNameAuslesen() {
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(textErsteElement));
		return textErsteElement.getText();
	}
}
