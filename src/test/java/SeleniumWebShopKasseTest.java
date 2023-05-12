import java.time.Duration;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWebShopKasseTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//input[@class='promoCode']")
	private WebElement promoCodeEingeben;
	
	@FindBy(className = "promoBtn")
	private WebElement promoCodeBtn;
	
	@FindBy(className = "promoInfo")
	private WebElement promoInfo;
	
	@FindBy(className = "discountAmt")
	private WebElement summeKasse;
	
	@FindBy(xpath = "//button[normalize-space()='Bestellen']")
	private WebElement btnBestellen;
	
	@FindBy(xpath = "//div[@class='wrapperTwo']//div//select")
	private WebElement selectLand;
	
	@FindBy(className = "chkAgree")
	private WebElement btnAGB;
	
	@FindBy(xpath = "//button[normalize-space()='Weiter']")
	private WebElement btnWeiter;
	
	
	
	public SeleniumWebShopKasseTest(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}
	
	public void promoCodeEingeben(String promo) {
		wait.until(ExpectedConditions.elementToBeClickable(promoCodeEingeben));
		promoCodeEingeben.sendKeys(promo);
	}
	
	public void promoCodeAktivieren() {
		promoCodeBtn.click();
	}
	
	public String promoInfoAuslesen() {
		wait.until(ExpectedConditions.elementToBeClickable(promoInfo));
		return promoInfo.getText();
	}
	
	public String summe() {
		return summeKasse.getText();
	}
	
	public void bestellen() {
		btnBestellen.click();
	}
	
	public void selectLandDropDown(String land) {
		Select landSelect = new Select(selectLand);
		
		landSelect.selectByValue(land);
	}
	
	public void agbAkzeptieren() {
		btnAGB.click();
	}
	
	public void weiterKlick() {
		btnWeiter.click();
	}

}
