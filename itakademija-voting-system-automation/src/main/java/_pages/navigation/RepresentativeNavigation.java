package _pages.navigation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import _base.BasePage;

public class RepresentativeNavigation extends BasePage {

	private String publicRepWelcomeText = "//h5";
	private String publicRepVoteRegistrationTitle = "//h3";
	private String changePasswordLabel = "//label[1]";

	// ELEMENTS
	@FindBy(linkText = "Pradinis")
	@CacheLookup
	private WebElement link_HomePage;

	@FindBy(id = "singleVoteRegistration")
	@CacheLookup
	private WebElement link_SingleVoteRegistration;

	@FindBy(id = "multiVoteRegistration")
	@CacheLookup
	private WebElement link_MultiVoteRegistration;

	@FindBy(id = "changeAdminPassword")
	@CacheLookup
	private WebElement link_ChangeAdminPassword;

	@FindBy(id = "logout")
	@CacheLookup
	private WebElement link_logout;

	// CONSTRUCTOR
	public RepresentativeNavigation(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}

	// ASSERTS

	public void assertInHomePage() {
		clickElement(link_HomePage);
		checkIsLoaded();
		assertEquals(getPublicRepWelcomeText(),
				"Jūs esate prisijungęs prie RinkSis sistemos apylinkės atstovo aplinkos.",
				"You are not in Reprentative Home Page");

	}

	public void assertInRepresentativeVoteRegistrationSingle() {
		waitForElementToBeInDOM(link_SingleVoteRegistration);
		clickElement(link_SingleVoteRegistration);
		assertEquals(getPublicRepVoteRegistrationTitle(), "Balsavimo rezultatai vienmandatėse apygardose",
				"You are not in Reprentative Vote Registration Single Page");

	}

	public void assertInRepresentativeVoteRegistrationMulti() {
		waitForElementToBeInDOM(link_MultiVoteRegistration);
		clickElement(link_MultiVoteRegistration);
		assertEquals(getPublicRepVoteRegistrationTitle(), "Balsavimo rezultatai daugiamandatėse apygardose",
				"You are not in Reprentative Vote Registration Multi Page");

	}

	public void assertRepresentativeChangePassword() {
		waitForElementToBeInDOM(link_ChangeAdminPassword);
		clickElement(link_ChangeAdminPassword);
		assertEquals(getadminChangePasswordLabel(), "Įveskite naują slaptažodį",
				"You are not in Admin Password Change Page");
	}

	public void assertRepresentativeLogout() {
		waitForElementToBeInDOM(link_logout);
		clickElement(link_logout);
		waitForElementToBeInDOM(webDriver.findElement(By.id("login")));
		waitForElement(By.id("login"));
		assertTrue(webDriver.findElement(By.id("login")).isDisplayed(), "You didn`t log out");
	}

	// GETTERS

	public String getPublicRepWelcomeText() {
		return webDriver.findElement(By.xpath(publicRepWelcomeText)).getText();
	}

	public String getPublicRepVoteRegistrationTitle() {
		return webDriver.findElement(By.xpath(publicRepVoteRegistrationTitle)).getText();
	}

	public String getadminChangePasswordLabel() {
		return webDriver.findElement(By.xpath(changePasswordLabel)).getText();
	}
}
