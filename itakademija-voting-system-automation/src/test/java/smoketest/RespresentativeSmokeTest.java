package smoketest;

import org.testng.annotations.Test;

import _base.BaseTest;
import data.LoginData;

public class RespresentativeSmokeTest extends BaseTest {

	@Test(groups = { "p1" }, dataProvider = "loginAsRepresentative", dataProviderClass = LoginData.class, priority = 1)
	public void testLoginPage(String username, String password) throws InterruptedException {
		webDriver.manage().deleteAllCookies();
		loginPage.loadPage();
		loginPage.login(username, password);
	}

	@Test(priority = 2)
	public void testRepresentativeHomePage() {
		representativeNavigation.assertInHomePage();
	}

	@Test(priority = 3)
	public void testRepresentativeVoteRegistrationSingle() {
		representativeNavigation.assertInRepresentativeVoteRegistrationSingle();
	}

	@Test(priority = 4)
	public void testRepresentativeVoteRegistrationMulti() {
		representativeNavigation.assertInRepresentativeVoteRegistrationMulti();
	}

	@Test(priority = 5)
	public void testRepresentativeChangePasswordPage() {
		representativeNavigation.assertRepresentativeChangePassword();
	}

	@Test(priority = 6)
	public void testRepresentativeLogOut() {
		representativeNavigation.assertRepresentativeLogout();
	}

}
