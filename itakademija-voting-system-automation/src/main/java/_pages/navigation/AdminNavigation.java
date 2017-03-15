package _pages.navigation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class AdminNavigation {
	
	@FindBy(linkText = "Pradinis")
	@CacheLookup
	private WebElement link_HomePage;
	
	@FindBy(id = "constituency")
	@CacheLookup
	private WebElement link_ConstituencyDistrict;
	
	@FindBy(id = "party")
	@CacheLookup
	private WebElement link_Parties;
	
	@FindBy(id = "addCandidates")
	@CacheLookup
	private WebElement link_AddCandidates;
	
	@FindBy(id = "singleMember")
	@CacheLookup
	private WebElement link_AddCandidatesSingle;
	
	@FindBy(id = "multiMember")
	@CacheLookup
	private WebElement link_AddCandidatesMulti;
	
	@FindBy(id = "resultDeletePage")
	@CacheLookup
	private WebElement link_ResultsConfirmDelete;
	
	@FindBy(id = "changeAdminPassword")
	@CacheLookup
	private WebElement link_ChangeAdminPassword;
	
	@FindBy(id = "changeAdminPassword")
	@CacheLookup
	private WebElement link_logout;

}
