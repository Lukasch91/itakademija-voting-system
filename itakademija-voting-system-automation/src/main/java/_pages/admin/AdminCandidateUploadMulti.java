package _pages.admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import _base.BasePage;
import _utils.CsvFilesList;

public class AdminCandidateUploadMulti extends BasePage{

	
	private String candidatePreviewRows = "//div[3]/..//tr";
	private String addCandidatesButton = "//td/div/button[text()='Pridėti kandidatus']";
	private String previewCandidatesButton = "//td/div/button[text()='Peržiurėti kandidatus']";
	
	//--ELEMENTS--

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
	
	//--CONSTRUCTOR--
	public AdminCandidateUploadMulti(WebDriver webDriver) {
		super(webDriver);
		this.PAGE_TITLE = "Rinkimai";
		this.PAGE_URL = "http://localhost:8080/admin#/upload-multi-cadidates";
	}


	
	// ----------METHODS---------

	// ---------ASSERTS----------
	public void assertCorrectPage() {
		assertTrue(button_PreviewCandidates.isDisplayed(), "You are not in Candidates Upload Multi page");
		
	}

	// PREVIEW
	public void assertPreviewCsvButtonWorks() {
		clickElement(button_PreviewCandidates);
		waitUntilElementToBeClickable(By.xpath("//*[text()='Atšaukti']"));
		assertEquals(button_DeleteCandidates.getText(), "Ištrinti kandidatus", "Not in candates preview modal window");
	}

	public void assertDeleteCandidatesWorks() {
		clickElement(button_DeleteCandidates);
		waitUntilElementToBeClickable(By.xpath("//*[text()='Pridėti kandidatus']"));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		clickElement(button_AddCandidates);
		waitUntilElementToBeClickable(By.xpath("//*[@type='file']"));
		assertTrue(button_ChooseFile.isEnabled(), "Not in candates upload modal window");
	}

	public void assertCsvUploadCancelButtonWorks() {
		clickElement(button_CancelCandidatesUpload);
		waitForJavascript();
		waitUntilElementToBeClickable(By.xpath("//*[text()='Pridėti kandidatus']"));
		assertTrue(button_AddCandidates.isEnabled(), "Cancel button in Candidates Single Preview Modal don`t work");
		waitUntilElementToBeClickable(By.xpath("//*[text()='Pridėti kandidatus']"));
	}

	public void assertCsvUploadWithCommasWorks() {
		setElementTextNoAssert(button_ChooseFile, CsvFilesList.partyCanComma);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clickElement(button_UploadCandidates);
		waitForJavascript();
		waitUntilElementToBeClickable(By.xpath("//*[text()='Peržiurėti kandidatus']"));
		waitForJavascript();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(getPreviewCandidatesButtonCount(), 2, "Candidates havn`t been added");

	}
	
	public void assertCsvUploadWithSemicolonWorks() {
		clickElement(radioButton_DataWithSemicolon);
		setElementTextNoAssert(button_ChooseFile, CsvFilesList.partyCanSemicolon);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clickElement(button_UploadCandidates);
		waitForJavascript();
		waitUntilElementToBeClickable(By.xpath("//*[text()='Peržiurėti kandidatus']"));
		waitForJavascript();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
