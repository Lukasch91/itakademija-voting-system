package _pages.representative;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import _base.BasePage;

public class VoteRegistrationSingle extends BasePage {

	private By pageTitle = By.xpath("//h3");
	private By enteredVotes = By.xpath("//tr[1]/td[2]");
	


	// --ELEMENTS--
	@FindBy(xpath = "//tbody[1]//td[2]/../td[2]/input")
	private WebElement field_VotesNumberFirst;
	
	@FindBy(xpath = "//tbody[2]//td[2]/../td[2]/input")
	private WebElement field_VotesNumberSecond;

	@FindBy(xpath = "//*[text()='Siųsti rezultatus']")
	private WebElement button_SendResults;

	// --CONSTRUCTOR--
	public VoteRegistrationSingle(WebDriver webDriver) {
		super(webDriver);
		this.PAGE_TITLE = "RinkSis";
		this.PAGE_URL = "http://localhost:8080/rep#/reg-votes-single";
	}
	
	// --METHODS--

	public void clickAddNew() {
		clickElement(button_SendResults);
	}

	//--------------ASSERTS-------------
	public void assertCorrectSingleVoteRegistrationPage() {
		waitForElementToBeInDOM(button_SendResults);
		assertEquals(getPageTitle(), "Balsavimo rezultatai vienmandatėse apygardose", "You are not in Single Votes Registration page");
	}
	
	public void assertVoteRegistrationForSingleWorks(String firstField, String secondField) {
		waitForElementToBeInDOM(field_VotesNumberFirst);
		setElementTextNoClear(field_VotesNumberFirst, firstField);
		setElementTextNoClear(field_VotesNumberSecond, secondField);
		clickElement(button_SendResults);
		webDriver.navigate().refresh();
		waitForJavascript();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
		assertEquals(getEnteredVotes(), firstField, "Votes are not registred");

}
	
	//--------GETTERS------
	
	public String getPageTitle() {
		return webDriver.findElement(pageTitle).getText();
	}
	
	public String getEnteredVotes() {
		return webDriver.findElement(enteredVotes).getText();
	}



}
