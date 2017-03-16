package _pages.navigation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import _base.BasePage;

public class PublicNavigation extends BasePage {

	private String publicPageWelcomeMessage = "//p";
	private String publicPageFinalResultsButton = "//*[text()='Partijų mandatų rezultatai']";
	private String publicPageMembersListButton = "//*[text()='Laimėtojų sąrašas']";
	private String publicResultsTitle = "//h2";

	// ELEMETS

	@FindBy(linkText = "Pradinis")
	private WebElement link_HomePage;

	@FindBy(id = "candidates")
	private WebElement link_PublicCandidates;

	@FindBy(id = "finresults")
	private WebElement link_PublicFinalResults;

	@FindBy(id = "members")
	private WebElement link_PublicMembers;

	@FindBy(id = "results")
	private WebElement link_PublicResults;

	@FindBy(id = "singleresults")
	private WebElement link_PublicSingleResults;

	@FindBy(id = "multiresults")
	private WebElement link_PublicMultiResults;

	@FindBy(id = "login")
	private WebElement link_PublicLogin;

	// CONSTRUCTOR
	public PublicNavigation(WebDriver webDriver) {
		super(webDriver);
		this.PAGE_TITLE = "RinkSis";
		this.PAGE_URL = "http://localhost:8080/";
	}

	// ASSERTS
	public void assertInHomePage() {
		loadPage();
		clickElement(link_HomePage);
		checkIsLoaded();
		assertEquals(getPublicPageWelcomeMessage(), "Naujausia Lietuvos Respublikos Seimo rinkimų sistema",
				"You are not in Public Home Page");
	}

	public void assertInPublicCandidates() {
		loadPage();
		waitForElementToBeInDOM(link_PublicCandidates);
		clickElement(link_PublicCandidates);
		assertTrue(webDriver.findElement(By.id("search")).isDisplayed());

	}

	public void assertInPublicFinalResults() {
		loadPage();
		waitForElementToBeInDOM(link_PublicFinalResults);
		clickElement(link_PublicFinalResults);
		waitUntilElementToBeClickable(By.xpath(publicPageFinalResultsButton));
		assertEquals(getPublicPageFinalResultsTitle(), "Partijų mandatų rezultatai",
				"You are not in Public Final Results Page");
	}

	public void assertInPublicMembersList() {
		loadPage();
		waitForElementToBeInDOM(link_PublicMembers);
		clickElement(link_PublicMembers);
		waitUntilElementToBeClickable(By.xpath(publicPageMembersListButton));
		assertEquals(getPublicPageMembersListButton(), "Laimėtojų sąrašas", "You are not in Public Members List Page");

	}

	public void assertInResultsSingle() {
		loadPage();
		waitForElementToBeInDOM(link_PublicResults);
		clickElement(link_PublicResults);
		clickElement(link_PublicSingleResults);
		assertEquals(getPublicResultsTitle(), "Balsavimo rezultatai vienmandatėse apygardose",
				"You are not in Public Results Single Page");

	}

	public void assertInResultsMulti() {
		loadPage();
		waitForElementToBeInDOM(link_PublicResults);
		clickElement(link_PublicResults);
		clickElement(link_PublicMultiResults);
		assertEquals(getPublicResultsTitle(), "Balsavimo rezultatai daugiamandatėse apygardose",
				"You are not in Public Results Multi Page");
	}

	public void assertPublicLoginButtonWorks() {
		loadPage();
		waitForElementToBeInDOM(link_PublicLogin);
		clickElement(link_PublicLogin);
		waitForElement(By.id("username"));
		assertTrue(webDriver.findElement(By.id("username")).isDisplayed(), "Login button doesn`t work");

	}

	// GETTERS

	public String getPublicPageWelcomeMessage() {
		return webDriver.findElement(By.xpath(publicPageWelcomeMessage)).getText();
	}

	public String getPublicPageFinalResultsTitle() {
		return webDriver.findElement(By.xpath(publicPageFinalResultsButton)).getText();
	}

	public String getPublicPageMembersListButton() {
		return webDriver.findElement(By.xpath(publicPageMembersListButton)).getText();
	}

	public String getPublicResultsTitle() {
		return webDriver.findElement(By.xpath(publicResultsTitle)).getText();
	}

}
