package _pages._public;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import _base.BasePage;

public class PublicResultsMulti extends BasePage {

	private String titleForFirstResultsPage = "//h2";
	private String titleForSecondResultsPage = "//h3";
	private String titleForThirdResultsPage = "//h4";
	private By backButton = By.xpath("//*[@class='btn btn-xs btn-warning']");

	// --ELEMENTS--
	@FindBy(xpath = "//*[@class='btn btn-xs btn-info']")
	private WebElement button_PreviewConstituencyResults;

	@FindBy(xpath = "//*[@class='btn btn-xs btn-warning']")
	private WebElement button_Back;

	// --CONSTRUCTOR--
	public PublicResultsMulti(WebDriver webDriver) {
		super(webDriver);
		this.PAGE_TITLE = "Rinkimai";
		this.PAGE_URL = "http://localhost:8080/#/multiresults";
	}

	// ----------METHODS---------

	// ---------ASSERTS----------

	public void assertCorrectPage() {
		waitForElementToBeInDOM(button_PreviewConstituencyResults);
		assertEquals(getTitleForFirstResultsPage(), "Balsavimo rezultatai daugiamandatėse apygardose",
				"You are not in Public Single Result preview page");
	}

	public void assertConstituencyPreviewPage() {
		clickElement(button_PreviewConstituencyResults);
		waitForElementToBeInDOM(button_Back);
		waitForJavascript();
		waitOrNotToWait(1000);
		waitUntilElementToBeClickable(backButton);
		assertEquals(getTitleForSecondResultsPage(), "Balsavimo rezultatai Kaunas apygardoje",
				"You are not in Public Single Constituency Result preview page");
	}

	public void assertDistrictPreviewPage() {
		clickElement(button_PreviewConstituencyResults);
		waitForElementToBeInDOM(button_Back);
		waitForJavascript();
		waitOrNotToWait(1000);
		waitUntilElementToBeClickable(backButton);
		assertEquals(getTitleForThirdResultsPage(), "Vyto rinkimų apylinkė",
				"You are not in Public Single Constituency District Result preview page");

	}

	// ---------GETTERS------

	public String getTitleForFirstResultsPage() {
		return webDriver.findElement(By.xpath(titleForFirstResultsPage)).getText();
	}

	public String getTitleForSecondResultsPage() {
		return webDriver.findElement(By.xpath(titleForSecondResultsPage)).getText();
	}

	public String getTitleForThirdResultsPage() {
		return webDriver.findElement(By.xpath(titleForThirdResultsPage)).getText();
	}

}
