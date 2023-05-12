import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SeleniumTestFormPage {

	WebDriver driver;

	@FindBy(id = "form-widgets-betreff")
	private WebElement inputbetreff;
	
	@FindBy(id = "form-widgets-name")
	private WebElement inputName;
	
	@FindBy(id = "form-widgets-auswahl1")
	private WebElement selectKurs;
	
	@FindBy(id = "form-widgets-auswahl2-from")
	private WebElement selectFirma;
	
	@FindBy(name = "from2toButton")
	private WebElement btnFirmaAuswahl1;
	
	@FindBy(id = "form-widgets-auswahl2-to")
	private WebElement btnFirmaAuswahlObenBox2;
	
	@FindBy(name = "upButton")
	private WebElement upButton;
	
	@FindBy(name = "form.buttons.speichern")
	private WebElement btnSpeicher;
	
	@FindBy(id = "message")
	private WebElement statusMeldung;
	
	@FindBy(xpath = "//ul[@id='companies']//li[1]")
	private WebElement ersteElement;
	
	public SeleniumTestFormPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void betreffEingeben(String betreff) {
		inputbetreff.sendKeys(betreff);
	}
	
	public void nameEingeben(String name) {
	
		inputName.sendKeys(name);
	}

	public void kursAuswäheln(String kursName) {
		
		Select selectKurs = new Select(this.selectKurs);
		selectKurs.selectByValue(kursName);
	}
	
	public void firmaInBoxAuswählen(int[] auswahl) {
		Select selectFirma = new Select(this.selectFirma);
		for(int i : auswahl) {
			selectFirma.selectByIndex(i);
		}
	}
	
	public void firmenÜbernehmen() {
		btnFirmaAuswahl1.click();
	}
	
	public void firmaInBox2Auswählen(int auswahl) {
		Select selectFirma = new Select(this.btnFirmaAuswahlObenBox2);
		selectFirma.selectByIndex(auswahl);
	}
	
	public void ausgewählteFirmenVerschiben() {
		upButton.click();
	}
	
	public void formularSpeichern() {
		btnSpeicher.click();
	}
	
	public String statusMeldung() {
		return statusMeldung.getText();
	}
	
	public String erstesElemenAuslesen() {
		return ersteElement.getText();
	}
}
