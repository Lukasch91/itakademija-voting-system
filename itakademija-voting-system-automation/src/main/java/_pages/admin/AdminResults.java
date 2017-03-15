package _pages.admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import _base.BasePage;

public class AdminResults extends BasePage{

	private String deleteVotesButtonText = "//tr[1]/td[4]/button";
	
	//---------ELEMENTS---------

	@FindBy(xpath = "//*[text()='Apylinkių sąrašas']")
	private WebElement button_DistrictList;
	
	//confiramtion-registration-deletion
	
	@FindBy(xpath = "//tr[1]/td[2]/button")
	private WebElement button_ReviewVotesSingle;
	
	@FindBy(xpath = "//tr[1]/td[3]/button")
	private WebElement button_PublicizeVotesSingle;
	
	@FindBy(xpath = "//tr[1]/td[4]/button")
	private WebElement button_DeleteVotesSingle;
	
	@FindBy(xpath = "//tr[1]/td[5]/button")
	private WebElement button_ReviewVotesMulti;
	
	@FindBy(xpath = "//tr[1]/td[6]/button")
	private WebElement button_PublicizeVotesMulti;
	
	@FindBy(xpath = "//tr[1]/td[7]/button")
	private WebElement button_DeleteVotesMulti;
	
	//modal
	@FindBy(xpath = "//button[text()='Uždaryti']")
	private WebElement button_CloseModal;
	
	//--CONSTRUCTOR--
	public AdminResults(WebDriver webDriver) {
		super(webDriver);
		this.PAGE_TITLE = "Rinkimai";
		this.PAGE_URL = "http://localhost:8080/admin#/publish-delete-votes";
	}

	
	// ---------METHODS-----------
	
	
	// ---------ASSERTS-----------
	
	public void assertCorrectResultsPage() {
		assertTrue(button_DistrictList.isDisplayed(), "You are not in Results Page");
	}
	
	public void assertCorrectResultsPageInSelectedConstituency() {
		clickElement(button_DistrictList);
		assertEquals(getDeleteVotesButtonText(), "Trinti balsus", "You are not in Resulsts Page for selected constituency");
	}
	
	// Nezinau kaip paspaust mygtuka
	public void assertCanPreviewRegisterdVotesForSingle() {
		clickElement(button_ReviewVotesSingle);
		waitForJavascript();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitUntilElementToBeClickable(By.xpath("//*[text()='Uždaryti']"));
		assertTrue(button_CloseModal.isEnabled(), "Can`t preview Votes");
		waitUntilElementToBeClickable(By.xpath("//*[text()='Uždaryti']"));
		clickElement(button_CloseModal);
		waitForJavascript();
		
	}
	
	public void assertCanPreviewRegisterdVotesForMulti() {
		clickElement(button_ReviewVotesMulti);
		waitForJavascript();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitUntilElementToBeClickable(By.xpath("//*[text()='Uždaryti']"));
		assertTrue(button_CloseModal.isEnabled(), "Can`t preview Votes");
		waitUntilElementToBeClickable(By.xpath("//*[text()='Uždaryti']"));
		clickElement(button_CloseModal);
		waitForJavascript();
	}
	
	public void assertCanPublisizeRegisterdVotesForMulti() {
		assertTrue(button_PublicizeVotesSingle.isEnabled());
		clickElement(button_PublicizeVotesSingle);
		waitForJavascript();
		confirmPopUp();
		waitForJavascript();
		assertTrue(!button_PublicizeVotesSingle.isEnabled());
	}


	public void assertCanPublisizeRegisterdVotesForSingle() {
		assertTrue(button_PublicizeVotesMulti.isEnabled());
		clickElement(button_PublicizeVotesMulti);
		waitForJavascript();
		confirmPopUp();
		waitForJavascript();
		assertTrue(!button_PublicizeVotesMulti.isEnabled());
	}
	
	public void assertCanDeleteRegisterdVotesForMulti() {
		assertTrue(button_DeleteVotesSingle.isEnabled());
		clickElement(button_DeleteVotesSingle);
		waitForJavascript();
		confirmPopUp();
		waitForJavascript();
		assertTrue(!button_DeleteVotesSingle.isEnabled());
	}


	public void assertCanDeleteRegisterdVotesForSingle() {
		assertTrue(button_DeleteVotesMulti.isEnabled());
		clickElement(button_DeleteVotesMulti);
		waitForJavascript();
		confirmPopUp();
		waitForJavascript();
		assertTrue(!button_DeleteVotesMulti.isEnabled());
		
	}
	
	
	
	//----------GETTERS-----------
	
	public String getDeleteVotesButtonText() {
		return webDriver.findElement(By.xpath(deleteVotesButtonText)).getText();
	}









	
	
}
