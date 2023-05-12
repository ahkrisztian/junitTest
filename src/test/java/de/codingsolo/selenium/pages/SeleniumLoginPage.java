package de.codingsolo.selenium.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumLoginPage {
	
	private WebDriver driver;
	
	//private By inputBenutzerName = By.id("__ac_name");
	@FindBy(id = "__ac_name")
	private WebElement inputBenutzerName;
	
	//private By inputPassWord = By.name("__ac_password");
	@FindBy(id = "__ac_password")
	private WebElement inputPassWord;
	
	//private By btnAnmeldung = By.xpath("//input[@name='buttons.login']");
	@FindBy(xpath = "//input[@name='buttons.login']")
	private WebElement btnAnmeldung;
	
	//private By result =  By.className("documentFirstHeading");
	@FindBy(className = "documentFirstHeading")
	private WebElement result;

	public SeleniumLoginPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void zugagnsdateEingeben(String name, String password) {
		inputBenutzerName.sendKeys(name);
		inputPassWord.sendKeys(password);
		
	}
	
	public void loginBtnAnClicken() {
		btnAnmeldung.click();
	}
	
	public String statusMeldungAuslesen() {
		return result.getText();
	}

}
