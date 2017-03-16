package _pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import _base.BasePage;

public class AdminHomePage extends BasePage {

	// --ELEMENTS--

	@FindBy(xpath = "//h5")
	@CacheLookup
	private WebElement text_Welcome;

	// --CONSTRUCTOR--
	public AdminHomePage(WebDriver webDriver) {
		super(webDriver);
		this.PAGE_TITLE = "RinSis";
		this.PAGE_URL = "http://localhost:8080/admin";
	}

	// --ASSERTS--

	// --METHODS--
	public String getWelcomeText() {
		return text_Welcome.getText();
	}

}
