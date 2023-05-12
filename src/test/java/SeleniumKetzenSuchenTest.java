import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumKetzenSuchenTest {

	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//p[@class='lead']")
	private WebElement leadText;
	
	@FindBy(id = "ECqe13G5B")
	private WebElement imgKatze1;
	
	@FindBy(id = "5qNv0jHXW")
	private WebElement imgKatze2;
	
	@FindBy(id = "gt")
	private WebElement imgKatze3;
	
	@FindBy(linkText = "Previous")
	private WebElement linkPrevious;
	
	@FindBy(linkText = "Next")
	private WebElement linkNext;
	
	@FindBy(id = "anzahlSelect")
	private WebElement selectAnzahl;
	
	@FindBy(id = "sortSelect")
	private WebElement selectSortierung;
	
	
	public SeleniumKetzenSuchenTest(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}
	
	public String beschreibungAuslesen() {
		return leadText.getText();
	}
	
	public String srcLinkImgKatze1() {
		return imgKatze1.getAttribute("src");
	}
	
	public String srcLinkImgKatzeh5() {
		wait.until(ExpectedConditions.elementToBeClickable(imgKatze3));
		return imgKatze3.getAttribute("src");
	}
	
	public String srcLinkImgKatze2() {
		return imgKatze2.getAttribute("src");
	}
	
	public void nextPage() {
		linkNext.click();
	}
	
	public void previousPage() {
		linkPrevious.click();
	}
	
	public void selecAnzahlEingabe(String anzhalWert) {
		Select anzhal = new Select(selectAnzahl);
		
		anzhal.selectByValue(anzhalWert);
	}
	
	public void sortierungEingabe(String sortierWert) {
		Select anzhal = new Select(selectSortierung);
		
		anzhal.selectByValue(sortierWert);
	}
}
