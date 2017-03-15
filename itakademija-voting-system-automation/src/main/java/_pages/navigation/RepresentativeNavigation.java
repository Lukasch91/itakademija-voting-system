package _pages.navigation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class RepresentativeNavigation {

	
	@FindBy(linkText = "Pradinis")
	@CacheLookup
	private WebElement link_HomePage;
	
	@FindBy(id = "singleVoteRegistration")
	@CacheLookup
	private WebElement link_SingleVoteRegistration;
	
	@FindBy(id = "multiVoteRegistration")
	@CacheLookup
	private WebElement link_MultiVoteRegistration;
}
