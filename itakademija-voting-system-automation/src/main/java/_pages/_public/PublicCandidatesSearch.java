package _pages._public;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import _base.BasePage;

public class PublicCandidatesSearch extends BasePage{
	
	private static String searchResultTableRows = "//tr";
	private static String searchResultFoundPersonName = "//td[1]";
	private static String searchResultFoundPersonSurname = "//td[2]";
	private static String searchResultFoundPersonParty = "//td[3]";
	private static String searchResultFoundPersonAbbreviation = "//td[4]";
	private static String searchResultFoundPersonConstituency = "//td[5]";
	
	//--ELEMENTS--
	@FindBy(id = "search")
	@CacheLookup
	private WebElement field_Search;

	@FindBy(css = ".btn.btn-xs.btn-success")
	@CacheLookup
	private WebElement button_PreviewCandidatesList;
	
	//--CONSTRUCTOR--
	public PublicCandidatesSearch(WebDriver webDriver) {
		super(webDriver);
		this.PAGE_TITLE = "RinkSis";
		this.PAGE_URL = "http://localhost:8080/#/candidates";
	}

	// ----------METHODS---------
	
	
	// ---------ASSERTS----------
	
	public void assertCorrectPage() {
		waitForElementToBeInDOM(field_Search);
		assertTrue(field_Search.isDisplayed(), "You are not in Candidate Search page");	
	}

	public void assertCandidateSearchByNameWorks(String name) {
		setElementText(field_Search, name);
		waitForJavascript();
		assertTrue(getSearchResultTableRows() == 2);
		assertEquals(getSearchResultFoundPersonName(), name);
	}
	
	public void assertCandidateSearchBySurnameWorks(String surname) {
		setElementText(field_Search, surname);
		waitForJavascript();
		assertTrue(getSearchResultTableRows() == 2);
		assertEquals(getSearchResultFoundPersonSurname(), surname);
		
	}

	public void assertCandidateSearchByPartyWorks(String party) {
		setElementText(field_Search, party);
		waitForJavascript();
		assertTrue(getSearchResultTableRows() == 3);
		assertEquals(getSearchResultFoundPersonParty(), party);
		
	}

	public void assertCandidateSearchByAbbreviationWorks(String abbreviation) {
		setElementText(field_Search, abbreviation);
		waitForJavascript();
		assertTrue(getSearchResultTableRows() == 2);
		assertEquals(getSearchResultFoundPersonAbbreviation(), abbreviation);
		
	}

	public void assertCandidateSearchByConstituencyWorks(String city) {
		setElementText(field_Search, city);
		waitForJavascript();
		assertTrue(getSearchResultTableRows() == 3);
		assertEquals(getSearchResultFoundPersonConstituency(), city);
		
	}
	
	//-----GETTERS----
	
	public int getSearchResultTableRows() {
		return webDriver.findElements(By.xpath(searchResultTableRows)).size();
	}
	public String getSearchResultFoundPersonName() {
		return webDriver.findElement(By.xpath(searchResultFoundPersonName)).getText();
	}
	
	
	public String getSearchResultFoundPersonSurname() {
		return webDriver.findElement(By.xpath(searchResultFoundPersonSurname)).getText();
	}

	public String getSearchResultFoundPersonParty() {
		return webDriver.findElement(By.xpath(searchResultFoundPersonParty)).getText();
	}

	public String getSearchResultFoundPersonAbbreviation() {
		return webDriver.findElement(By.xpath(searchResultFoundPersonAbbreviation)).getText();
	}

	public String getSearchResultFoundPersonConstituency() {
		return webDriver.findElement(By.xpath(searchResultFoundPersonConstituency)).getText();
	}


}
