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
import _pages.admin.AdminDistrict;
import _pages.admin.AdminRepresentative;
import _utils.DataBaseCommands;
import _utils.DataBaseInsert;
import data.LoginData;

public class ReprentativeTest extends BaseTest {

	@Test(groups = { "p1" }, dataProvider = "loginAsAdmin", dataProviderClass = LoginData.class)
	public void reprentativeLoadPageTest(String username, String password)
			throws ClassNotFoundException, SQLException, InterruptedException {
		DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.oneConstituencyOneDistrict);
		loginPage.login(username, password);
		adminConstituency.loadPage();
		adminConstituency.clickAdministrateDistrict();
		adminRepresentative.assertCorrectRepresentativeRegistrationPage();
	}

	@Parameters({ "name", "surname", "username", "email" })
	@Test(groups = { "p2" }, dependsOnMethods = "reprentativeLoadPageTest")
	public void addNewReprentativeTest(String name, String surname, String username, String email) {
		adminDistrict.clickAddReprentative();
		adminRepresentative.assertRepresentativeRegistrationWorks(name, surname, username, email);
	}

	@Test(groups = { "p2" }, dependsOnMethods = "reprentativeLoadPageTest")
	public void deleteRepresntativeTest() {
		adminRepresentative.assertRepresentativeDeletionWorks();
	}

	@Test(groups = { "p2" }, dependsOnMethods = "reprentativeLoadPageTest")
	public void correctPreviewTest() {
		try {
			DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.oneConstituencyOneDistrictOneRepresentative);
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		adminRepresentative.assertCorrectPreviewPage();
	}
}
