import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumHomePage {
	
	WebDriver driver;

	//StatusMeldung
	@FindBy(className = "documentFirstHeading")
	private WebElement statusMeldung;
	
	//Side Menu Btn
	@FindBy(id = "portaltab-burger-menu")
	private WebElement btnMenu;
	
	//Side Menu
	@FindBy(xpath = "//a[@title='Selenium Testapplikationen']")
	private WebElement sideMenuTestAppClick;

	
	public SeleniumHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String statusMeldung() {
		return statusMeldung.getText();
	}
	
	public void menuAusklappen() {
		btnMenu.click();
	}
	
	public void seleniumTestAppAnklicken() {
		sideMenuTestAppClick.click();
	}
	
	public void linkAuswählen(String linkName) {
		
		statusMeldung();
		menuAusklappen();
		seleniumTestAppAnklicken();
		
		WebElement linkLokatorenTestTabelle = driver.findElement(By.xpath(String.format(("//a[contains(text(),'%s')]"), linkName)));
		linkLokatorenTestTabelle.click();
	}
	
}
