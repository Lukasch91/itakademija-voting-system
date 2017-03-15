package testclasses._public;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import _base.BaseTest;
import _pages._public.PublicResultsMulti;
import _pages._public.PublicResultsSingle;
import _utils.DataBaseCommands;
import _utils.DataBaseInsert;

public class PublicResultsMultiTest extends BaseTest {

	@Test(groups = { "p1" })
	public void resultPreviewMultiTest() {
		try {
			DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.allData2Parties4Candidates);
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
		publicResultsMulti.loadPage();
		publicResultsMulti.assertCorrectPage();
	}

	@Test(groups = { "p1" }, dependsOnMethods = "resultPreviewMultiTest")
	public void resultPreviewForMultiConsituencyTest() {
		publicResultsMulti.assertConstituencyPreviewPage();
	}

	@Test(groups = { "p1" }, dependsOnMethods = "resultPreviewForMultiConsituencyTest")
	public void resultPreviewForMultiConsituencyDistrictTest() {
		publicResultsMulti.assertDistrictPreviewPage();
	}
}
