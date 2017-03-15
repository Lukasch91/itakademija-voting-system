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
import _pages.admin.AdminConstituency;
import _utils.DataBaseCommands;
import _utils.DataBaseInsert;
import data.LoginData;

public class ConstituencyTest extends BaseTest {

	@Test(groups = { "p1" }, dataProvider = "loginAsAdmin", dataProviderClass = LoginData.class)
	public void constituencyLoadPageTest(String username, String password)
			throws ClassNotFoundException, SQLException, InterruptedException {
		DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.oneConstituency);
		loginPage.login(username, password);
		adminConstituency.loadPage();
		adminConstituency.assertCorrectPage();
	}

	@Test(groups = { "p2" }, dependsOnMethods = "constituencyLoadPageTest")
	public void addNewConstituencyButtonTest() {
		adminConstituency.assertAddNewConstituencyButtonWorks();
	}

	@Parameters({ "constituencyName" })
	@Test(groups = { "p2" }, dependsOnMethods = "addNewConstituencyButtonTest")
	public void addNewConstituencyTest(String constituencyName) {
		adminConstituency.assertAddNewConstituencyWorks(constituencyName);
	}

	@Test(groups = { "p2" }, dependsOnMethods = "constituencyLoadPageTest")
	public void removeConstituencyTest() {
		adminConstituency.assertConstituencyRemoveWorks();
	}

}
