package _pages.admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import _base.BasePage;

public class AdminDistrict extends BasePage {

	private String tableRows = "//tr";
	private By addNew = By.id("addNew");
	private By remove = By.xpath("//*[@class='glyphicon glyphicon-remove']");

	// --ELEMENTS--

	@FindBy(id = "addNew")
	private WebElement button_AddNewDistrict;

	@FindBy(id = "backToConstituency")
	private WebElement button_BackToConstituency;

	@FindBy(xpath = "//*[text()='Peržiūrėti atstovą']")
	private WebElement button_PreviewRepresentative;

	@FindBy(xpath = "//*[text()='Pridėti atstovą']")
	private WebElement button_AddRepresentative;

	@FindBy(xpath = "//*[@class='glyphicon glyphicon-remove']")
	private WebElement button_RemoveDistrict;

	// register

	@FindBy(id = "districtName")
	private WebElement field_DistrictName;

	@FindBy(id = "address")
	private WebElement field_DistrictAddress;

	@FindBy(id = "votersNumber")
	private WebElement field_VotersNumber;

	@FindBy(id = "addDistrict")
	private WebElement button_AddDistrict;

	@FindBy(id = "cancelDistrict")
	private WebElement button_CancelAddingDistrict;

	// --------CONSTRUCTOR-------
	public AdminDistrict(WebDriver webDriver) {
		super(webDriver);
	}

	// ---------METHODS--------

	public void clickAddNew() {
		clickElement(button_AddNewDistrict);
	}

	public void clickAddRepresentative() {
		clickElement(button_AddRepresentative);
	}

	public void clickPreviewRepresentative() {
		clickElement(button_PreviewRepresentative);
	}

	public void clickRemove() {
		clickElement(button_RemoveDistrict);
	}

	public WebElement findConstituencyByName(String name) {
		return webDriver.findElement(By.xpath("//td[1][text()='" + name + "']"));
	}

	public int constituencyCount() {
		return webDriver.findElements(By.xpath(tableRows)).size() - 1;
	}

	public void clickAddReprentative() {
		clickElement(button_AddRepresentative);
	}

	public void clickPreviewReprentative() {
		clickElement(button_PreviewRepresentative);
	}

	// ---------ASSERTS----------

	public void assertCorrectPage() {
		assertTrue(button_AddRepresentative.isDisplayed(), "You are in the wrong page");
	}

	public void assertAddNewConstituencyButtonWorks() {
		clickAddNew();
		assertTrue(field_DistrictName.isDisplayed(), "You are in the wrong page");
	}

	public void assertAddNewConstituencyWorks(String districtName, String districtAdress, String votersCount) {
		waitForElementToBeInDOM(field_DistrictName);
		setElementText(field_DistrictName, districtName);
		setElementText(field_DistrictAddress, districtAdress);
		setElementText(field_VotersNumber, votersCount);
		clickElement(button_AddDistrict);
		waitForJavascript();
		waitForElementToBeInDOM(button_AddRepresentative);
		assertEquals(constituencyCount(), 3);
	}

	public void assertConstituencyRemoveWorks() {
		waitUntilElementToBeClickable(remove);
		clickRemove();
		confirmPopUp();
		waitUntilElementToBeClickable(addNew);
		waitOrNotToWait(500);
		assertEquals(constituencyCount(), 1);

	}

}
