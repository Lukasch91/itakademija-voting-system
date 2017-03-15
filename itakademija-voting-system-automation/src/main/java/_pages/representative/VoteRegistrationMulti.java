package _pages.representative;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import _base.BasePage;

public class VoteRegistrationMulti extends BasePage{
	
	private String pageTitle = "//h3";
	private String enteredVotes = "//tbody[1]//td[3]";
	
	// --ELEMENTS--
	@FindBy(xpath = "//tbody[1]//td[2]/../td[3]/input")
	private WebElement field_VotesNumberFirst;
	
	@FindBy(xpath = "//tbody[2]//td[2]/../td[3]/input")
	private WebElement field_VotesNumberSecond;

	@FindBy(xpath = "//*[text()='Siųsti rezultatus']")
	private WebElement button_SendResults;

	// --CONSTRUCTOR--
	public VoteRegistrationMulti(WebDriver webDriver) {
		super(webDriver);
		this.PAGE_TITLE = "RinkSis";
		this.PAGE_URL = "http://localhost:8080/rep#/reg-votes-multi";
	}
	
	// --METHODS--
	public void clickAddNew() {
		clickElement(button_SendResults);
	}
	

	//--------------ASSERTS-------------
	public void assertCorrectMultiVoteRegistrationPage() {
		waitForElementToBeInDOM(button_SendResults);
		assertEquals(getPageTitle(), "Balsavimo rezultatai daugiamandatėse apygardose", "You are not in Single Votes Registration page");
	}
	
	public void assertVoteRegistrationForMultiWorks(String firstField, String secondField) {
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
		return webDriver.findElement(By.xpath(pageTitle)).getText();
	}
	
	public String getEnteredVotes() {
		return webDriver.findElement(By.xpath(enteredVotes)).getText();
	}
}
