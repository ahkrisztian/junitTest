import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumDragAndDrop {

	private WebDriver driver;
	
	@FindBy(id = "white-logo")
	private WebElement whiteLogo;
	
	@FindBy(id = "blue-logo")
	private WebElement blueLogo;
	
	@FindBy(id = "droppable")
	private WebElement dragBox;
	
	@FindBy(css = "#droppable > p")
	private WebElement meldung;

	public SeleniumDragAndDrop(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void dragAndDropLogos() {
		
		Actions action = new Actions(driver);
		
		action.dragAndDrop(whiteLogo, dragBox).build().perform();
		action.dragAndDrop(blueLogo, dragBox).build().perform();
	}
	
	public String Meldung() {
		return meldung.getText();
	}
}
