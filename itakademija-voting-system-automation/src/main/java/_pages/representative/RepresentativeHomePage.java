package _pages.representative;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import _base.BasePage;

public class RepresentativeHomePage extends BasePage{

	
	//--ELEMENTS--

	@FindBy(xpath = "//div/div/div")
	@CacheLookup
	private WebElement text_Welcome;

	
	//--CONSTRUCTOR--
	public RepresentativeHomePage(WebDriver webDriver) {
		super(webDriver);
		this.PAGE_TITLE = "RinkSis";
		this.PAGE_URL = "http://localhost:8080/rep#/home";
	}
	
	//--ASSERTS--
	
	
	//--METHODS--
	public String getWelcomeText() {
		return text_Welcome.getText();
	}

	
	

}
