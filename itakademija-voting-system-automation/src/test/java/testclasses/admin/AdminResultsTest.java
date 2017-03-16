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
import _pages.admin.AdminResults;
import _utils.DataBaseCommands;
import _utils.DataBaseInsert;
import data.LoginData;

public class AdminResultsTest extends BaseTest {

	@Test(groups = { "p1" }, dataProvider = "loginAsAdmin", dataProviderClass = LoginData.class)
	public void resultsLoadPageTest(String username, String password)
			throws ClassNotFoundException, SQLException, InterruptedException {
		DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.allDataOnePartyOneCandidateWithVotesNOTCONFIRMED);
		loginPage.login(username, password);
		adminResults.loadPage();
		adminResults.assertCorrectResultsPage();
	}

	@Test(groups = { "p1" }, dependsOnMethods = "resultsLoadPageTest")
	public void constituencyLoadPageForSelectedConstituencyTest() {
		adminResults.assertCorrectResultsPageInSelectedConstituency();
	}

	@Test(groups = { "p1" }, dependsOnMethods = "constituencyLoadPageForSelectedConstituencyTest", enabled = false)
	public void previewRegisterdVotesSingleTest() {
		adminResults.assertCanPreviewRegisterdVotesForSingle();
	}

	@Test(groups = { "p1" }, dependsOnMethods = "constituencyLoadPageForSelectedConstituencyTest", enabled = false)
	public void previewRegisterdVotesMultiTest() {
		adminResults.assertCanPreviewRegisterdVotesForMulti();
	}

	@Test(groups = { "p1" }, dependsOnMethods = "constituencyLoadPageForSelectedConstituencyTest")
	public void publicizeRegisterdVotesSingleTest() throws ClassNotFoundException, SQLException, InterruptedException {
		DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.allDataOnePartyOneCandidateWithVotesNOTCONFIRMED);
		adminResults.assertCanPublisizeRegisterdVotesForSingle();
	}

	@Test(groups = { "p1" }, dependsOnMethods = "constituencyLoadPageForSelectedConstituencyTest")
	public void publicizeRegisterdVotesMultiTest() throws ClassNotFoundException, SQLException, InterruptedException {
		DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.allDataOnePartyOneCandidateWithVotesNOTCONFIRMED);
		adminResults.assertCanPublisizeRegisterdVotesForMulti();
	}

	@Test(groups = { "p1" }, dependsOnMethods = "constituencyLoadPageForSelectedConstituencyTest")
	public void deleteRegisterdVotesSingleTest() throws ClassNotFoundException, SQLException, InterruptedException {
		DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.allDataOnePartyOneCandidateWithVotesNOTCONFIRMED);
		adminResults.assertCanDeleteRegisterdVotesForSingle();
	}

	@Test(groups = { "p1" }, dependsOnMethods = "constituencyLoadPageForSelectedConstituencyTest")
	public void deleteRegisterdVotesMultiTest() throws ClassNotFoundException, SQLException, InterruptedException {
		DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.allDataOnePartyOneCandidateWithVotesNOTCONFIRMED);
		adminResults.assertCanDeleteRegisterdVotesForMulti();
	}

}
