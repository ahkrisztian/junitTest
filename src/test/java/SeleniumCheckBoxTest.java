import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumCheckBoxTest {
	
	private WebDriver driver;
	
	@FindBy(xpath = "//input[@id='checkBoxOption1']")
	private WebElement checkBox1;
	
	@FindBy(xpath = "//input[@id='checkBoxOption2']")
	private WebElement checkBox2;
	
	@FindBy(xpath = "//input[@id='checkBoxOption3']")
	private WebElement checkBox3;
	
	public SeleniumCheckBoxTest(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void checkBox1Click() {
		checkBox1.click();
	}
	
	public void checkBox2Click() {
		checkBox2.click();
	}
	
	public void checkBox3Click() {
		checkBox3.click();
	}
	
	public boolean checkBox1Status() {
		return checkBox1.isSelected();
	}
	
	public boolean checkBox2Status() {
		return checkBox2.isSelected();
	}
	
	public boolean checkBox3Status() {
		return checkBox3.isSelected();
	}

}
