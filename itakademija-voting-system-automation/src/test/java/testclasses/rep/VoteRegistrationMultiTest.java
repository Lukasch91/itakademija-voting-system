package testclasses.rep;

import java.sql.SQLException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import _base.BaseTest;
import _utils.DataBaseCommands;
import _utils.DataBaseInsert;
import data.LoginData;

public class VoteRegistrationMultiTest extends BaseTest {

	@Test(groups = { "p1" }, dataProvider = "loginAsRepresentative", dataProviderClass = LoginData.class)
	public void voteRegistrationLoadPageTest(String username, String password)
			throws ClassNotFoundException, SQLException, InterruptedException {
		DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.allDataOnePartyOneRepresentative);
		loginPage.login(username, password);
		voteRegistrationMulti.loadPage();
		voteRegistrationMulti.assertCorrectMultiVoteRegistrationPage();
	}

	@Parameters({ "firstField", "secondField" })
	@Test(groups = { "p2" }, dependsOnMethods = "voteRegistrationLoadPageTest")
	public void voteRegistrationForMultiTest(String firstField, String secondField) {
		voteRegistrationMulti.assertVoteRegistrationForMultiWorks(firstField, secondField);
	}
}
