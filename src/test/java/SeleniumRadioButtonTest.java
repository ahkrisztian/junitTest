import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumRadioButtonTest {

private WebDriver driver;
	
	@FindBy(xpath = "//input[@value='radio1']")
	private WebElement radiobtn1;
	
	@FindBy(xpath = "//input[@value='radio2']")
	private WebElement radiobtn2;
	
	@FindBy(xpath = "//input[@value='radio3']")
	private WebElement radiobtn3;
	
	public SeleniumRadioButtonTest(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void checkBox1Click() {
		radiobtn1.click();
	}
	
	public void checkBox2Click() {
		radiobtn2.click();
	}
	
	public void checkBox3Click() {
		radiobtn3.click();
	}
	
	public String radioBtn1Attribute() {
		return radiobtn1.getAttribute("value");
	}
	
	public String radioBtn2Attribute() {
		return radiobtn2.getAttribute("value");
	}
	
	public String radioBtn3Attribute() {
		return radiobtn3.getAttribute("value");
	}
}
