package testclasses.rep;

import static org.testng.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import _base.BaseTest;
import data.LoginData;

public class RepresentativeLoginTest extends BaseTest {

	@Test(groups = { "p1" }, dataProvider = "RepresentativeLoginWithValidation", dataProviderClass = LoginData.class)
	public void testLoginPage(String username, String password, String errorType) throws InterruptedException {
		webDriver.manage().deleteAllCookies();
		loginPage.loadPage();
		loginPage.login(username, password);
		// Verify what to test based on data passed in
		if (!StringUtils.isBlank(errorType)) {
			boolean result = loginPage.checkErrorMessage(errorType);
			assertTrue(result, "Expected Error: " + errorType);
		} else {
			assertTrue(!representativeHomePage.getWelcomeText().isEmpty());
		}
	}

}
