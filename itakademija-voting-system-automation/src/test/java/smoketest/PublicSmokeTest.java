package smoketest;

import org.testng.annotations.Test;

import _base.BaseTest;

public class PublicSmokeTest extends BaseTest {

	@Test(priority = 1)
	public void testPublicHomePage() {
		publicNavigation.assertInHomePage();
	}

	@Test(priority = 2)
	public void testPublicCandidatesPage() {
		publicNavigation.assertInPublicCandidates();
	}

	@Test(priority = 3)
	public void testPublicFinalResultsPage() {
		publicNavigation.assertInPublicFinalResults();
	}

	@Test(priority = 4)
	public void testPublicMembersList() {
		publicNavigation.assertInPublicMembersList();
	}

	@Test(priority = 5)
	public void testPublicResultsSingle() {
		publicNavigation.assertInResultsSingle();
	}

	@Test(priority = 6)
	public void testPublicResultsMulti() {
		publicNavigation.assertInResultsMulti();
	}

	@Test(priority = 6)
	public void testPublicLogin() {
		publicNavigation.assertPublicLoginButtonWorks();
	}
}
