package _base;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import _pages.LoginPage;
import _pages._public.PublicCandidatesSearch;
import _pages._public.PublicResultsMulti;
import _pages._public.PublicResultsSingle;
import _pages.admin.AdminCandidateUploadMulti;
import _pages.admin.AdminCandidateUploadSingle;
import _pages.admin.AdminConstituency;
import _pages.admin.AdminDistrict;
import _pages.admin.AdminHomePage;
import _pages.admin.AdminParties;
import _pages.admin.AdminRepresentative;
import _pages.admin.AdminResults;
import _pages.navigation.AdminNavigation;
import _pages.navigation.PublicNavigation;
import _pages.navigation.RepresentativeNavigation;
import _pages.representative.RepresentativeHomePage;
import _pages.representative.VoteRegistrationMulti;
import _pages.representative.VoteRegistrationSingle;
import _utils.DataBaseCommands;
import _utils.DataBaseInsert;

public class BaseTest {

	public static DataBaseInsert insert;
	public static DataBaseCommands command;

	public WebDriver webDriver;

	@BeforeTest
	public void open() {
		try {
			DataBaseInsert.openDataBase();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@AfterTest
	public void close() {
		try {
			DataBaseInsert.openDataBase();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		this.webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		webDriver.manage().window().maximize();
		pageFactory();

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		webDriver.close();
		webDriver.quit();
	}

	// nepaskaiciau iki galo info apie page factory del to taip gavosi :(

	public LoginPage loginPage;
	public AdminConstituency adminConstituency;
	public AdminDistrict adminDistrict;
	public AdminRepresentative adminRepresentative;
	public AdminParties adminParties;
	public PublicCandidatesSearch publicCandidatesSearch;
	public PublicResultsMulti publicResultsMulti;
	public PublicResultsSingle publicResultsSingle;
	public AdminHomePage adminHomePage;
	public AdminResults adminResults;
	public AdminCandidateUploadMulti adminCandidateUploadMulti;
	public AdminCandidateUploadSingle adminCandidateUploadSingle;
	public RepresentativeHomePage representativeHomePage;
	public VoteRegistrationMulti voteRegistrationMulti;
	public VoteRegistrationSingle voteRegistrationSingle;
	public AdminNavigation adminNavigation;
	public PublicNavigation publicNavigation;
	public RepresentativeNavigation representativeNavigation;

	public void pageFactory() {
		loginPage = PageFactory.initElements(webDriver, LoginPage.class);
		adminConstituency = PageFactory.initElements(webDriver, AdminConstituency.class);
		adminDistrict = PageFactory.initElements(webDriver, AdminDistrict.class);
		adminRepresentative = PageFactory.initElements(webDriver, AdminRepresentative.class);
		adminParties = PageFactory.initElements(webDriver, AdminParties.class);
		publicCandidatesSearch = PageFactory.initElements(webDriver, PublicCandidatesSearch.class);
		publicResultsMulti = PageFactory.initElements(webDriver, PublicResultsMulti.class);
		publicResultsSingle = PageFactory.initElements(webDriver, PublicResultsSingle.class);
		adminHomePage = PageFactory.initElements(webDriver, AdminHomePage.class);
		adminResults = PageFactory.initElements(webDriver, AdminResults.class);
		adminCandidateUploadMulti = PageFactory.initElements(webDriver, AdminCandidateUploadMulti.class);
		adminCandidateUploadSingle = PageFactory.initElements(webDriver, AdminCandidateUploadSingle.class);
		representativeHomePage = PageFactory.initElements(webDriver, RepresentativeHomePage.class);
		voteRegistrationMulti = PageFactory.initElements(webDriver, VoteRegistrationMulti.class);
		voteRegistrationSingle = PageFactory.initElements(webDriver, VoteRegistrationSingle.class);
		adminNavigation = PageFactory.initElements(webDriver, AdminNavigation.class);
		publicNavigation = PageFactory.initElements(webDriver, PublicNavigation.class);
		representativeNavigation = PageFactory.initElements(webDriver, RepresentativeNavigation.class);
	}
}