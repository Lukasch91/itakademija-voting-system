package _pages.navigation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import _base.BasePage;
import _base.BaseTest;

public class AdminNavigation extends BasePage {

	private String adminWelcomeMessage = "//h5";
	private String adminConstituencyButton = "//*[text()='Administruoti apylinkes']";
	private String adminPartyTableTitle = "//th[1]";
	private String adminCandidateUploadTitle = "//h4";
	private String adminResultsButton = "//*[text()='Apylinkių sąrašas']";
	private String adminChangePasswordLabel = "//label[1]";

	// ELEMETS
	@FindBy(linkText = "Pradinis")
	@CacheLookup
	private WebElement link_HomePage;

	@FindBy(id = "constituency")
	@CacheLookup
	private WebElement link_ConstituencyDistrict;

	@FindBy(id = "party")
	@CacheLookup
	private WebElement link_Parties;

	@FindBy(id = "addCandidates")
	@CacheLookup
	private WebElement link_AddCandidates;

	@FindBy(id = "singleMember")
	@CacheLookup
	private WebElement link_AddCandidatesSingle;

	@FindBy(id = "multiMember")
	@CacheLookup
	private WebElement link_AddCandidatesMulti;

	@FindBy(id = "resultDeletePage")
	@CacheLookup
	private WebElement link_ResultsConfirmDelete;

	@FindBy(id = "changeAdminPassword")
	@CacheLookup
	private WebElement link_ChangeAdminPassword;

	@FindBy(id = "logout")
	@CacheLookup
	private WebElement link_logout;

	// CONSTRUCTOR
	public AdminNavigation(WebDriver webDriver) {
		super(webDriver);
	}

	// ASSERTS
	public void assertInHomePage() {
		waitForElementToBeInDOM(link_HomePage);
		clickElement(link_HomePage);
		assertEquals(getAdminWelcomeMessage(),
				"Jūs esate prisijungęs prie RinkSis sistemos administratoriaus aplinkos.",
				"You are not in Admin Home Page");
	}

	public void assertConstituencyDistrictPage() {
		waitForElementToBeInDOM(link_ConstituencyDistrict);
		clickElement(link_ConstituencyDistrict);
		assertEquals(getAdminConstituencyButton(), "Administruoti apylinkes", "You are not in Constituency Page");

	}

	public void assertPartyPage() {
		waitForElementToBeInDOM(link_Parties);
		clickElement(link_Parties);

	}

	public void assertCandidateUploadSinglePage() {
		waitForElementToBeInDOM(link_AddCandidates);
		clickElement(link_AddCandidates);
		waitForElementToBeInDOM(link_AddCandidatesSingle);
		clickElement(link_AddCandidatesSingle);
		assertTrue(getAdminCandidateUploadTitle("Vienmandatės"), "You are not in Candidate Upload Single Page");

	}

	public void assertCandidateUploadMultiPage() {
		waitForElementToBeInDOM(link_AddCandidates);
		clickElement(link_AddCandidates);
		waitForElementToBeInDOM(link_AddCandidatesMulti);
		clickElement(link_AddCandidatesMulti);
		assertTrue(getAdminCandidateUploadTitle("Daugiamandatės"), "You are not in Candidate Upload Multi Page");

	}

	public void assertAdminResultsPage() {
		waitForElementToBeInDOM(link_ResultsConfirmDelete);
		clickElement(link_ResultsConfirmDelete);
		assertEquals(getResultsButton(), "Apylinkių sąrašas", "You are not in Results Page");
	}

	public void assertAdminChangePassword() {
		waitForElementToBeInDOM(link_ChangeAdminPassword);
		clickElement(link_ChangeAdminPassword);
		assertEquals(getadminChangePasswordLabel(), "Įveskite naują slaptažodį",
				"You are not in Admin Password Change Page");
	}

	public void assertAdminLogout() {
		waitForElementToBeInDOM(link_logout);
		clickElement(link_logout);
		waitForElementToBeInDOM(webDriver.findElement(By.id("login")));
		assertTrue(webDriver.findElement(By.id("login")).isDisplayed(), "You didn`t log out");
	}

	// ------GETTERS------

	public String getAdminWelcomeMessage() {
		return webDriver.findElement(By.xpath(adminWelcomeMessage)).getText();
	}

	public String getAdminConstituencyButton() {
		return webDriver.findElement(By.xpath(adminConstituencyButton)).getText();
	}

	public String getAdminPartyTableTitle() {
		return webDriver.findElement(By.xpath(adminPartyTableTitle)).getText();
	}

	public boolean getAdminCandidateUploadTitle(String title) {
		return webDriver.findElement(By.xpath(adminCandidateUploadTitle)).getText().contains(title);
	}

	public String getResultsButton() {
		return webDriver.findElement(By.xpath(adminResultsButton)).getText();
	}

	public String getadminChangePasswordLabel() {
		return webDriver.findElement(By.xpath(adminChangePasswordLabel)).getText();
	}

}
