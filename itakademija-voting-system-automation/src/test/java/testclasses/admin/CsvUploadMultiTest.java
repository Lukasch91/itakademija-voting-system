package testclasses.admin;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import _base.BaseTest;
import _pages.LoginPage;
import _pages.admin.AdminCandidateUploadMulti;
import _pages.admin.AdminCandidateUploadSingle;
import _pages.admin.AdminConstituency;
import _utils.DataBaseCommands;
import _utils.DataBaseInsert;
import data.LoginData;

public class CsvUploadMultiTest extends BaseTest {
	
	@Test(groups = { "p1" }, dataProvider = "loginAsAdmin", dataProviderClass = LoginData.class)
	public void csvUploadLoadPageTest(String username, String password) throws ClassNotFoundException, SQLException, InterruptedException {
		DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.AllData2Parties4CandidatesFORCSVSINGLE);
		loginPage.login(username, password);
		adminCandidateUploadMulti.loadPage();
		adminCandidateUploadMulti.assertCorrectPage();
	}
	
	//preview
	@Test(groups = { "csv2" }, dependsOnMethods = "csvUploadLoadPageTest")
	public void csvPreviewButtonTest() {
		adminCandidateUploadMulti.assertPreviewCsvButtonWorks();
	}
	@Test(groups = { "csv3" }, dependsOnMethods = "csvPreviewButtonTest")
	public void csvDeleteCandidatesTest() {
		adminCandidateUploadMulti.assertDeleteCandidatesWorks();
	}
	
	@Test(groups = { "csv3" }, dependsOnMethods = "csvPreviewButtonTest")
	public void csvPreviewListWithCandidatesDisplayedTest() {
		adminCandidateUploadMulti.assertPreviewListWithCandidatesDisplayed();
	}
	
	@Test(groups = { "csv3" }, dependsOnMethods = "csvPreviewButtonTest")
	public void csvPreviewCancelButtonTest() {
		adminCandidateUploadMulti.assertCsvPreviewCancelButtonWorks();
	}
	

	//upload
	@Test(groups = { "p2" }, dependsOnMethods = "csvUploadLoadPageTest")
	public void csvUploadButtonTest() {
		adminCandidateUploadMulti.assertUploadCsvButtonWorks();
	}
	
	@Test(groups = { "csv3" }, dependsOnMethods = "csvUploadButtonTest")
	public void csvUploadCancelButtonTest() {
		adminCandidateUploadMulti.assertCsvUploadCancelButtonWorks();
	}
	
	@Test(groups = { "csv3" }, dependsOnMethods = "csvUploadButtonTest")
	public void csvUploadCommasTest() {
		adminCandidateUploadMulti.assertCsvUploadWithCommasWorks();
	}
	
	@Test(groups = { "csv3" }, dependsOnMethods = "csvUploadButtonTest")
	public void csvUploadSimcolonTest() {
		adminCandidateUploadMulti.assertCsvUploadWithSemicolonWorks();
	}
}
