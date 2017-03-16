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
import _pages._public.PublicResultsSingle;
import _utils.DataBaseCommands;
import _utils.DataBaseInsert;

public class PublicResultsSingleTest extends BaseTest{

	@Test(groups = { "smoke" })
	public void resultPreviewSingleTest() {
		try {
			DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.allData2Parties4Candidates);
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
		publicResultsSingle.loadPage();
		publicResultsSingle.assertCorrectPage();
	}
	
	@Test(groups = { "reg" }, dependsOnMethods = "resultPreviewSingleTest")
	public void resultPreviewForSingleConsituencyTest() {
		publicResultsSingle.assertConstituencyPreviewPage();
	}
	
	@Test(groups = { "reg" }, dependsOnMethods = "resultPreviewForSingleConsituencyTest")
	public void resultPreviewForSingleConsituencyDistrictTest() {
		publicResultsSingle.assertDistrictPreviewPage();
	}
}
