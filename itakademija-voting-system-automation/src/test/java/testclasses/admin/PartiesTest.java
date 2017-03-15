package testclasses.admin;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import _base.BaseTest;
import _pages.LoginPage;
import _pages.admin.AdminParties;
import _utils.DataBaseCommands;
import _utils.DataBaseInsert;
import data.LoginData;

public class PartiesTest extends BaseTest {

	@Test(groups = { "p1" }, dataProvider = "loginAsAdmin", dataProviderClass = LoginData.class, priority = 1)
	public void partyLoadPageTest(String username, String password)
			throws ClassNotFoundException, SQLException, InterruptedException {
		DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.AllData2Parties4CandidatesFORCSVSINGLE);
		loginPage.login(username, password);
		adminParties.loadPage();
		adminParties.assertCorrectPage();
	}

	@Test(groups = { "p2" }, dependsOnMethods = "partyLoadPageTest", priority = 2)
	public void addPartyButtonTest() {
		adminParties.assertAddNewPartyButtonWorks();
	}

	@Parameters({ "partyName", "partyAbbreviation" })
	@Test(groups = { "p2" }, dependsOnMethods = "addPartyButtonTest", priority = 3)
	public void addNewPartyTest(String partyName, String partyAbbreviation) throws InterruptedException {
		adminParties.assertAddNewPartyWorks(partyName, partyAbbreviation);
	}

	@Test(groups = { "p2" }, dependsOnMethods = "partyLoadPageTest", priority = 4)
	public void removePartyTest() throws InterruptedException {
		adminParties.assertPartyRemoveWorks();
	}
}
