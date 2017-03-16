package testclasses._public;

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
import _pages._public.PublicCandidatesSearch;
import _utils.DataBaseCommands;
import _utils.DataBaseInsert;

public class CandidateSearchTest extends BaseTest {

	@Test(groups = { "smoke" })
	public void candidateSearchLoadPageTest() {
		try {
			DataBaseInsert.insertDataToDataBaseFromFile(DataBaseCommands.allData2Parties4Candidates);
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
		publicCandidatesSearch.loadPage();
		publicCandidatesSearch.assertCorrectPage();
	}

	@Test(groups = { "reg" }, dependsOnMethods = "candidateSearchLoadPageTest")
	@Parameters("name")
	public void candidateSearchByNameTest(String name) {
		publicCandidatesSearch.assertCandidateSearchByNameWorks(name);
	}

	@Parameters("surname")
	@Test(groups = { "reg" }, dependsOnMethods = "candidateSearchLoadPageTest")
	public void candidateSearchBySurnameTest(String surname) {
		publicCandidatesSearch.assertCandidateSearchBySurnameWorks(surname);
	}

	@Parameters("party")
	@Test(groups = { "reg" }, dependsOnMethods = "candidateSearchLoadPageTest")
	public void candidateSearchByPartyTest(String party) {
		publicCandidatesSearch.assertCandidateSearchByPartyWorks(party);
	}

	@Parameters("abbreviation")
	@Test(groups = { "reg" }, dependsOnMethods = "candidateSearchLoadPageTest")
	public void candidateSearchByAbbreviationTest(String abbreviation) {
		publicCandidatesSearch.assertCandidateSearchByAbbreviationWorks(abbreviation);
	}

	@Parameters("city")
	@Test(groups = { "reg" }, dependsOnMethods = "candidateSearchLoadPageTest")
	public void candidateSearchByConstituencyTest(String city) {
		publicCandidatesSearch.assertCandidateSearchByConstituencyWorks(city);
	}

}
