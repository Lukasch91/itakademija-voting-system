package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	_DeleteDatabaseFiles.class, 
	Aa_ConstituencyIT.class, 
	Ab_DistrictIT.class, 
	Ac_RepresentativeIT.class, 
	Ad_PartyIT.class, 
	Ae_CandidateIT.class,
	Ah_AdminIT.class/*, 
	Af_MultiElectionIT.class, 
	Ag_SingleElectionIT.class*/})

public class _CreateUpdateSuite {

}
