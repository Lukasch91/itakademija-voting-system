package smoketest;

import java.sql.SQLException;

import org.testng.annotations.Test;

import _base.BaseTest;
import _utils.DataBaseCommands;
import _utils.DataBaseInsert;
import data.LoginData;

public class AdminSmokeTest extends BaseTest {

	@Test(groups = { "p1" }, dataProvider = "loginAsAdmin", dataProviderClass = LoginData.class, priority = 1)
	public void testLoginPage(String username, String password)
			throws InterruptedException, ClassNotFoundException, SQLException {
		DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.allData2Parties4Candidates);
		loginPage.loadPage();
		loginPage.login(username, password);
	}

	@Test(priority = 2)
	public void testHomePage() {
		adminNavigation.assertInHomePage();
	}

	@Test(priority = 3)
	public void testConstituencyDistrict() {
		adminNavigation.assertConstituencyDistrictPage();
	}

	@Test(priority = 4)
	public void testPartyPage() {
		adminNavigation.assertPartyPage();
	}

	@Test(priority = 5)
	public void testCandidateUploadSinglePage() {
		adminNavigation.assertCandidateUploadSinglePage();
	}

	@Test(priority = 6)
	public void testCandidateUploadMultiPage() {
		adminNavigation.assertCandidateUploadMultiPage();
	}

	@Test(priority = 7)
	public void testAdminResultsPage() {
		adminNavigation.assertAdminResultsPage();
	}

	@Test(priority = 8)
	public void testAdminChangePasswordPage() {
		adminNavigation.assertAdminChangePassword();
	}

	@Test(priority = 9)
	public void testAdminLogOut() {
		adminNavigation.assertAdminLogout();
	}

}
