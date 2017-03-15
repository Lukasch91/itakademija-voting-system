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
import _pages.admin.AdminCandidateUploadSingle;
import _utils.DataBaseCommands;
import _utils.DataBaseInsert;
import data.LoginData;

public class CsvUploadSingleTest extends BaseTest {

	@Test(groups = { "csv1" }, dataProvider = "loginAsAdmin", dataProviderClass = LoginData.class)
	public void csvUploadLoadPageTest(String username, String password)
			throws ClassNotFoundException, SQLException, InterruptedException {
		webDriver.manage().deleteAllCookies();
		DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.AllData2Parties4CandidatesFORCSVSINGLE);
		loginPage.login(username, password);
		adminCandidateUploadSingle.loadPage();
		adminCandidateUploadSingle.assertCorrectPage();
	}

	// preview
	@Test(groups = { "csv2" }, dependsOnMethods = "csvUploadLoadPageTest")
	public void csvPreviewButtonTest() {
		adminCandidateUploadSingle.assertPreviewCsvButtonWorks();
	}

	@Test(groups = { "csv3" }, dependsOnMethods = "csvPreviewButtonTest")
	public void csvDeleteCandidatesTest() {
		adminCandidateUploadSingle.assertDeleteCandidatesWorks();
	}

	@Test(groups = { "csv3" }, dependsOnMethods = "csvPreviewButtonTest")
	public void csvPreviewListWithCandidatesDisplayedTest() {
		adminCandidateUploadSingle.assertPreviewListWithCandidatesDisplayed();
	}

	@Test(groups = { "csv3" }, dependsOnMethods = "csvPreviewButtonTest")
	public void csvPreviewCancelButtonTest() {
		adminCandidateUploadSingle.assertCsvPreviewCancelButtonWorks();
	}

	// upload
	@Test(groups = { "p2" }, dependsOnMethods = "csvUploadLoadPageTest")
	public void csvUploadButtonTest() {
		adminCandidateUploadSingle.assertUploadCsvButtonWorks();
	}

	@Test(groups = { "csv3" }, dependsOnMethods = "csvUploadButtonTest")
	public void csvUploadCancelButtonTest() {
		adminCandidateUploadSingle.assertCsvUploadCancelButtonWorks();
	}

	@Test(groups = { "csv3" }, dependsOnMethods = "csvUploadButtonTest")
	public void csvUploadCommasTest() {
		adminCandidateUploadSingle.assertCsvUploadWithCommasWorks();
	}

	@Test(groups = { "csv3" }, dependsOnMethods = "csvUploadButtonTest")
	public void csvUploadSimcolonTest() {
		adminCandidateUploadSingle.assertCsvUploadWithSemicolonWorks();
	}

}
