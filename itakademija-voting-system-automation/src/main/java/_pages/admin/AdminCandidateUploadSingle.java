package _pages.admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import _base.BasePage;
import _utils.CsvFilesList;

public class AdminCandidateUploadSingle extends BasePage {

	private String candidatePreviewRows = "//div[3]/..//tr";
	private String addCandidatesButton = "//td/div/button[text()='Pridėti kandidatus']";
	private String previewCandidatesButton = "//td/div/button[text()='Peržiurėti kandidatus']";
	private By button = By.xpath("//tr[1]//button[2]");
	private By addCandidates = By.xpath("//*[text()='Pridėti kandidatus']");

	// --ELEMENTS--

	@FindBy(xpath = "//*[text()='Peržiurėti kandidatus']")
	private WebElement button_PreviewCandidates;

	@FindBy(xpath = "//*[text()='Pridėti kandidatus']")
	private WebElement button_AddCandidates;

	// preview modal
	@FindBy(xpath = "//*[text()='Ištrinti kandidatus']")
	private WebElement button_DeleteCandidates;

	@FindBy(xpath = "//*[text()='Atšaukti']")
	private WebElement button_CancelCandidatesPreview;

	// upload modal

	@FindBy(xpath = "//form/div/input[1]")
	private WebElement button_ChooseFile;

	@FindBy(xpath = "//*[@id='submit']")
	private WebElement button_UploadCandidates;

	@FindBy(xpath = "//form/../../div//button[2]")
	private WebElement button_CancelCandidatesUpload;

	@FindBy(xpath = "//input[2]")
	private WebElement radioButton_DataWithSemicolon;

	@FindBy(xpath = "//input[2]")
	private WebElement checkBox_CsvWithTitles;

	// --CONSTRUCTOR--
	public AdminCandidateUploadSingle(WebDriver webDriver) {
		super(webDriver);
		this.PAGE_TITLE = "Rinkimai";
		this.PAGE_URL = "http://localhost:8080/admin#/upload-single-cadidates";
	}

	// ----------METHODS---------

	// ---------ASSERTS----------

	public void assertCorrectPage() {
		assertTrue(button_PreviewCandidates.isDisplayed(), "You are not in Candidates Upload Single page");

	}

	// PREVIEW
	public void assertPreviewCsvButtonWorks() {
		checkIsLoaded();
		waitForElementToBeInDOM(button_PreviewCandidates);
		clickElement(button_PreviewCandidates);
		waitUntilElementToBeClickable(button);
		assertEquals(button_DeleteCandidates.getText(), "Ištrinti kandidatus", "Not in candates preview modal window");
	}

	public void assertDeleteCandidatesWorks() {
		clickElement(button_DeleteCandidates);
		waitForJavascript();
		waitUntilElementToBeClickable(addCandidates);
		waitOrNotToWait(500);
		assertTrue(getAddCandidatesButtonCount() == 2, "Candidates havn`t been deleted");

	}

	public void assertPreviewListWithCandidatesDisplayed() {
		assertTrue(getCandidatePreviewRowsCount() > 1, "Table of candidates is not displayed");

	}

	public void assertCsvPreviewCancelButtonWorks() {
		clickElement(button_CancelCandidatesPreview);
		waitForJavascript();
		waitUntilElementToBeClickable(By.xpath("//*[text()='Pridėti kandidatus']"));
		assertTrue(button_AddCandidates.isEnabled(), "Cancel button in Candidates Single Preview Modal don`t work");
		waitUntilElementToBeClickable(By.xpath("//*[text()='Pridėti kandidatus']"));
	}

	// UPLOAD
	public void assertUploadCsvButtonWorks() {
		waitForElementToBeInDOM(button_AddCandidates);
		clickElement(button_AddCandidates);
		waitForJavascript();
		checkIsLoaded();
		waitUntilElementToBeClickable(By.xpath("//*[@type='file']"));
		assertTrue(button_ChooseFile.isEnabled(), "Not in candates upload modal window");
	}

	public void assertCsvUploadCancelButtonWorks() {
		clickElement(button_CancelCandidatesUpload);
		waitForJavascript();
		waitUntilElementToBeClickable(addCandidates);
		assertTrue(button_AddCandidates.isEnabled(), "Cancel button in Candidates Single Preview Modal don`t work");
		waitUntilElementToBeClickable(By.xpath("//*[text()='Pridėti kandidatus']"));
	}

	public void assertCsvUploadWithCommasWorks() {
		setElementTextNoAssert(button_ChooseFile, CsvFilesList.constituencyCanComma);
		waitOrNotToWait(3000);
		clickElement(button_UploadCandidates);
		waitForJavascript();
		waitUntilElementToBeClickable(By.xpath("//*[text()='Peržiurėti kandidatus']"));
		waitForJavascript();
		waitOrNotToWait(1000);
		assertEquals(getPreviewCandidatesButtonCount(), 2, "Candidates havn`t been added");

	}

	public void assertCsvUploadWithSemicolonWorks() {
		clickElement(radioButton_DataWithSemicolon);
		setElementTextNoAssert(button_ChooseFile, CsvFilesList.constituencyCanSemicolon);
		waitOrNotToWait(3000);
		clickElement(button_UploadCandidates);
		waitForJavascript();
		waitUntilElementToBeClickable(By.xpath("//*[text()='Peržiurėti kandidatus']"));
		waitForJavascript();
		waitOrNotToWait(1000);
		assertEquals(getPreviewCandidatesButtonCount(), 2, "Candidates havn`t been added");

	}

	// -----GETTERS------

	public int getCandidatePreviewRowsCount() {
		return webDriver.findElements(By.xpath(candidatePreviewRows)).size();
	}

	public int getAddCandidatesButtonCount() {
		return webDriver.findElements(By.xpath(addCandidatesButton)).size();
	}

	public int getPreviewCandidatesButtonCount() {
		return webDriver.findElements(By.xpath(previewCandidatesButton)).size();
	}

}
