package testclasses.admin;


import org.testng.annotations.Test;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import _base.BaseTest;
import _pages.LoginPage;
import _pages.admin.AdminHomePage;
import data.LoginData;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

public class AdminLoginTest extends BaseTest{
	@Test(groups={"p1"}, dataProvider="AdminLoginWithValidation", dataProviderClass = LoginData.class)
	public void testLoginPage(String username, String password, String errorType) throws InterruptedException {
		webDriver.manage().deleteAllCookies();
		loginPage.loadPage();
		loginPage.login(username, password);
		//Verify what to test based on data passed in
		if (!StringUtils.isBlank(errorType)) {
			boolean result = loginPage.checkErrorMessage(errorType);
			assertTrue(result, "Expected Error: " + errorType);
		} else {
			assertTrue(!adminHomePage.getWelcomeText().isEmpty());
		}
	}

}

