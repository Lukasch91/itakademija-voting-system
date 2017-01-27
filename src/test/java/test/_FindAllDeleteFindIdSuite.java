package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	{Ba_ConstituencyIT.class, 
	Bb_DistrictIT.class, 
	Bc_RepresentativeIT.class, 
	Bd_PartyIT.class, 
	Be_CandidateIT.class,
	Bf_MultiElectionIT.class,
	Bg_SingleElectionIT.class})

public class _FindAllDeleteFindIdSuite {

}
