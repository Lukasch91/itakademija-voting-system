package testclasses.rep;

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
import _pages.representative.VoteRegistrationSingle;
import _utils.DataBaseCommands;
import _utils.DataBaseInsert;
import data.LoginData;

public class VoteRegistrationSingleTest extends BaseTest {

	@Test(groups = { "p1" }, dataProvider = "loginAsRepresentative", dataProviderClass = LoginData.class)
	public void voteRegistrationLoadPageTest(String username, String password)
			throws ClassNotFoundException, SQLException, InterruptedException {
		DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.allDataOnePartyOneRepresentative);
		loginPage.login(username, password);
		voteRegistrationSingle.loadPage();
		voteRegistrationSingle.assertCorrectSingleVoteRegistrationPage();
	}

	@Parameters({ "firstField", "secondField" })
	@Test(groups = { "p2" }, dependsOnMethods = "voteRegistrationLoadPageTest")
	public void voteRegistrationForSingleTest(String firstField, String secondField) {
		try {
			DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.allDataOnePartyOneRepresentative);
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		voteRegistrationSingle.assertVoteRegistrationForSingleWorks(firstField, secondField);
	}
}
