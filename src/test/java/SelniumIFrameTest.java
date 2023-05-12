import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelniumIFrameTest {
	
	private WebDriver driver;
	
	@FindBy(id = "name")
	private WebElement inputName;
	
	@FindBy(id = "alertbtn")
	private WebElement btnAlert;
	
	public SelniumIFrameTest(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void nameundClickBtnAlert(String text) {
		inputName.sendKeys(text);
		btnAlert.click();
	}
	
	public void wechselFrame() {
		driver.switchTo().frame("iframe");
	}
	
	public String alarmnachricht() {
		return driver.switchTo().alert().getText();
	}
	
	public void alarmNachrichtSchliessen() {
		driver.switchTo().alert().accept();
	}

}
