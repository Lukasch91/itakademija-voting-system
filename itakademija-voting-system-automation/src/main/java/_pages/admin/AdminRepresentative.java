package _pages.admin;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import _base.BasePage;

public class AdminRepresentative extends BasePage{
	

	
	//--ELEMENTS--
	
	private String textConstituencyName = "//tr[1]/td[1]";
	private String textDistrictName = "//tr[2]/td[1]";
	private String textFirstName = "//tr[3]/td[1]";
	private String textLastName = "//tr[4]/td[1]";
	private String textLoginName = "//tr[5]/td[1]";
	private String textEmail = "//tr[6]/td[1]";
	private By reviewRep = By.xpath("//*[text()='Peržiūrėti atstovą']");
	private By addRep = By.xpath("//*[text()='Pridėti atstovą']");


	@FindBy(id = "deleteRepresentative")
	private WebElement button_DeleteRepresentative;
	
	@FindBy(id = "bakcToDistrict")
	private WebElement button_BackToDistrict;
	
	//REGISTER
	@FindBy(id = "representativeName")
	private WebElement field_RepresentativeName;
	
	@FindBy(id = "representativeSurname")
	private WebElement field_RepresentativeSurname;
	
	@FindBy(id = "representativeUsername")
	private WebElement field_RepresentativeUsername;
	
	@FindBy(id = "email")
	private WebElement field_RepresentativeEmail;
	
	@FindBy(id = "addRepresentative")
	private WebElement button_AddRepresentative;
	
	@FindBy(id = "cancelRepresentative")
	private WebElement button_CancelAddingRepresentative;
	
	//MODAL
	@FindBy(xpath = "//*[@class='btn btn-xs btn-danger']")
	private WebElement button_ModalConfirmAddingRepresentative;
	
	@FindBy(xpath = "//*[@class='btn btn-xs btn-default']")
	private WebElement button_ModalCancelAddingRepresentative;
	
	@FindBy(xpath = "//*[@class='//*[@class='close']")
	private WebElement button_ModalClose;
	
	//-----------CONSTRUCTOR--------------
	public AdminRepresentative(WebDriver webDriver) {
		super(webDriver);
	}
	

	// ---------METHODS--------
	

	
	//--------------ASSERTS-------------

	public void assertCorrectRepresentativeRegistrationPage() {
		waitForElementToBeInDOM(webDriver.findElement(reviewRep));
		assertTrue(webDriver.findElement(addRep).isDisplayed(), "You are not in Reprentative register page");
	}
	
	public void assertRepresentativeRegistrationWorks(String name, String surname, String username, String email) {
		waitForElementToBeInDOM(field_RepresentativeName);
		setElementText(field_RepresentativeName, name);
		setElementText(field_RepresentativeSurname, surname);
		setElementText(field_RepresentativeUsername, username);
		setElementText(field_RepresentativeEmail, email);
		clickElement(button_AddRepresentative);
		waitForJavascript();
		waitForElementToBeInDOM(button_ModalConfirmAddingRepresentative);
		clickElement(button_ModalConfirmAddingRepresentative);
		waitForJavascript();
		waitUntilElementToBeClickable(reviewRep);
		assertTrue(webDriver.findElement(reviewRep).isDisplayed());
		
	}
	
	public void assertCorrectPreviewPage() {
		clickElement(webDriver.findElement(reviewRep));
		waitForElementToBeInDOM(button_DeleteRepresentative);
		assertTrue(button_DeleteRepresentative.isDisplayed(), "You are not in Reprentative preview page");
		
	}
	
	public void assertRepresentativeDeletionWorks() {
		clickElement(webDriver.findElement(reviewRep));
		waitForElementToBeInDOM(button_DeleteRepresentative);
		clickElement(button_DeleteRepresentative);
		waitForJavascript();
		confirmPopUp();
		waitForJavascript();
		waitUntilElementToBeClickable(addRep);
		assertTrue(webDriver.findElement(addRep).isDisplayed());
		
	}
	
	
	//-------------GETTERS---------------
	
	public String getTextConstituencyName() {
		return webDriver.findElement(By.xpath(textConstituencyName)).getText();
	}

	public String getTextDistrictName() {
		return webDriver.findElement(By.xpath(textDistrictName)).getText();
	}

	public String getTextFirstName() {
		return webDriver.findElement(By.xpath(textFirstName)).getText();
	}

	public String getTextLastName() {
		return webDriver.findElement(By.xpath(textLastName)).getText();
	}

	public String getTextLoginName() {
		return webDriver.findElement(By.xpath(textLoginName)).getText();
	}

	public String getTextEmail() {
		return webDriver.findElement(By.xpath(textEmail)).getText();
	}




}
