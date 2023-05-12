import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWebShopTest {

	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//input[@class='search-keyword']")
	private WebElement inputSuche;
	
	@FindBy(xpath = "//button[@class='search-button']")
	private WebElement btnSuchen;
	
	@FindBy(xpath = "//*[text() = 'Querlenker Satz Spurstange Audi']/..//*[@class='increment']")
	private WebElement btnIncrement;
	
	@FindBy(xpath = "//*[text() = 'Querlenker Satz Spurstange Audi']/..//*[@class='product-action']")
	private WebElement btnInWarenkorb;
	
	@FindBy(xpath = "//a[@class='cart-icon']")
	private WebElement btnWarenkorb;
	
	@FindBy(xpath = "//div[@class='action-block']")
	private WebElement btnZurKasse;
	
	
	
	
	public SeleniumWebShopTest(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}
	
	public void inputSucheEingeben(String artikel) {
		inputSuche.sendKeys(artikel);
	}
	
	public void produktAnzhalAuswählen(String text, int anzahl) {
		WebElement btnPlus = driver.findElement(By.xpath(String.format("//*[text() = '%s']/..//*[@class='increment']", text)));
		
		for(int i = 1; i < anzahl; i++) {
			btnPlus.click();
		}
	}
	
	public void btnSucheKlick() {
		btnSuchen.click();
	}
		
	
	public void incrementArtikel() {
		btnIncrement.click();
	}
	
	public void inWarenkorn() {
		btnInWarenkorb.click();
	}
	
	public void warenkorbÖffnen() {
		btnWarenkorb.click();
	}
	
	public void zurKasseKlick() {
		btnZurKasse.click();
	}
}
