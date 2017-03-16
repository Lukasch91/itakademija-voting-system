package _pages.admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import _base.BasePage;

public class AdminConstituency extends BasePage {

	private By rows = By.xpath("//tr");
	private By addNew = By.id("addNew");
	private By removeButton = By.xpath("//*[@class='glyphicon glyphicon-remove']");
	private By administrateButton = By.xpath("//*[@class='glyphicon glyphicon-remove']");

	// --ELEMENTS--

	@FindBy(id = "addNew")
	private WebElement button_AddNewConstituency;

	@FindBy(xpath = "//*[text()='Administruoti apylinkes']")
	private WebElement button_AdministrateDistrict;

	@FindBy(xpath = "//*[@class='glyphicon glyphicon-remove']")
	private WebElement button_RemoveConstituency;

	// --register--
	@FindBy(id = "constituencyName")
	private WebElement fields_ConstituencyName;

	@FindBy(id = "addConstituency")
	private WebElement button_AddConstituency;

	@FindBy(id = "cancelConstituency")
	private WebElement button_CancelAddingConstituency;

	// --CONSTRUCTOR--

	public AdminConstituency(WebDriver webDriver) {
		super(webDriver);
		this.PAGE_TITLE = "Rinkimai";
		this.PAGE_URL = "http://localhost:8080/admin#/con";
	}

	// --METHODS--
	public void clickAddNew() {
		clickElement(button_AddNewConstituency);
	}

	public void clickAdministrateDistrict() {
		waitUntilElementToBeClickable(administrateButton);
		clickElement(button_AdministrateDistrict);
	}

	public void clickRemove() {
		clickElement(button_RemoveConstituency);
	}

	public WebElement findConstituencyByName(String name) {
		return webDriver.findElement(By.xpath("//td[1][text()='" + name + "']"));
	}

	public int constituencyCount() {
		return webDriver.findElements(rows).size() - 1;
	}

	// ---------ASSERTS----------

	public void assertCorrectPage() {
		assertTrue(button_AdministrateDistrict.isDisplayed(), "You are in the wrong page");
	}

	public void assertAddNewConstituencyButtonWorks() {
		clickAddNew();
		assertTrue(fields_ConstituencyName.isDisplayed(), "You are in the wrong page");
	}

	public void assertAddNewConstituencyWorks(String constituencyName) {
		waitForElementToBeInDOM(fields_ConstituencyName);
		setElementText(fields_ConstituencyName, constituencyName);
		clickElement(button_AddConstituency);
		waitForJavascript();
		waitForElementToBeInDOM(button_AdministrateDistrict);
		assertEquals(constituencyCount(), 2);
	}

	public void assertConstituencyRemoveWorks() {
		waitForJavascript();
		checkIsLoaded();
		waitForElementToBeInDOM(button_RemoveConstituency);
		waitUntilElementToBeClickable(removeButton);
		clickRemove();
		confirmPopUp();
		waitUntilElementToBeClickable(addNew);
		waitOrNotToWait(500);
		assertEquals(constituencyCount(), 0);

	}

}
