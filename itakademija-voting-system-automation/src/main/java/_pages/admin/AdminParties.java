package _pages.admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import _base.BasePage;

public class AdminParties extends BasePage {

	private By rows = By.xpath("//tr");
	private By partyName = By.xpath("//*[text()='Partijos pavadinimas']");
	private By addNew = By.id("addNew");

	// --ELEMENTS--

	@FindBy(id = "addNew")
	@CacheLookup
	private WebElement button_AddNewParty;

	@FindBy(xpath = "//*[@class='glyphicon glyphicon-remove']")
	@CacheLookup
	private WebElement button_RemoveParty;

	// register

	@FindBy(id = "partyName")
	@CacheLookup
	private WebElement fields_PartyName;

	@FindBy(id = "partyAbr")
	@CacheLookup
	private WebElement fields_PartyAbbreviation;

	@FindBy(id = "addParty")
	@CacheLookup
	private WebElement button_AddParty;

	@FindBy(id = "cancelParty")
	@CacheLookup
	private WebElement button_CancelAddingParty;

	// --CONSTRUCTOR--
	public AdminParties(WebDriver webDriver) {
		super(webDriver);
		this.PAGE_TITLE = "RinkSis";
		this.PAGE_URL = "http://localhost:8080/admin#/parties";
	}

	// --METHODS--
	public void clickAddNew() {
		clickElement(button_AddNewParty);
	}

	public void clickRemove() {
		clickElement(button_RemoveParty);
	}

	private int partyCount() {
		return webDriver.findElements(rows).size() - 1;
	}

	// ---------ASSERTS----------

	public void assertCorrectPage() {
		assertTrue(webDriver.findElement(partyName).isDisplayed(), "You are not in Parties page");

	}

	public void assertAddNewPartyButtonWorks() {
		waitForJavascript();
		waitForElementToBeInDOM(button_AddNewParty);
		waitUntilElementToBeClickable(addNew);
		clickAddNew();
		waitForElementToBeInDOM(fields_PartyName);
		assertTrue(fields_PartyName.isDisplayed(), "You are not in Party registration page");

	}

	public void assertAddNewPartyWorks(String partyName, String partyAbbreviation) {
		waitForElementToBeInDOM(fields_PartyName);
		setElementText(fields_PartyName, partyName);
		setElementText(fields_PartyAbbreviation, partyAbbreviation);
		clickElement(button_AddParty);
		waitForJavascript();
		waitForElementToBeInDOM(button_RemoveParty);
		assertEquals(partyCount(), 3);

	}

	public void assertPartyRemoveWorks() throws InterruptedException {
		waitForElementToBeInDOM(button_RemoveParty);
		clickRemove();
		confirmPopUp();
		waitUntilElementToBeClickable(addNew);
		waitOrNotToWait(500);
		assertEquals(partyCount(), 1);

	}

}
