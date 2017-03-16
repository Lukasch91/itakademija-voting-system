package testclasses.admin;

import java.sql.SQLException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import _base.BaseTest;
import _utils.DataBaseCommands;
import _utils.DataBaseInsert;
import data.LoginData;

public class DistrictTest extends BaseTest {

	@Test(groups = { "p1" }, dataProvider = "loginAsAdmin", dataProviderClass = LoginData.class)
	public void districtLoadPageTest(String username, String password)
			throws ClassNotFoundException, SQLException, InterruptedException {
		DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.oneConstituencyOneDistrict);
		loginPage.login(username, password);
		adminConstituency.loadPage();
		adminConstituency.clickAdministrateDistrict();
		adminDistrict.assertCorrectPage();
	}

	@Test(groups = { "p2" }, dependsOnMethods = "districtLoadPageTest")
	public void addNewDistrictButtonTest() {
		adminDistrict.assertAddNewConstituencyButtonWorks();
	}

	@Parameters({ "districtName", "districtAdress", "votersCount" })
	@Test(groups = { "p2" }, dependsOnMethods = "addNewDistrictButtonTest")
	public void addNewConstituencyTest(String districtName, String districtAdress, String votersCount) {
		adminDistrict.assertAddNewConstituencyWorks(districtName, districtAdress, votersCount);
	}

	@Test(groups = { "p2" }, dependsOnMethods = "districtLoadPageTest")
	public void removeDistrictTest() {
		adminDistrict.assertConstituencyRemoveWorks();
	}

}
