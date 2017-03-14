package vs.public_.download.results;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vs.public_.multi.results.MultiElectionConstituencyList;
import vs.public_.multi.results.MultiElectionDistrictList;
import vs.public_.multi.results.MultiElectionResults;
import vs.public_.multi.results.MultiElectionResultsService;
import vs.public_.single.results.SingleElectionConstituency;
import vs.public_.single.results.SingleElectionDistrict;
import vs.public_.single.results.SingleElectionResult;
import vs.public_.single.results.SingleElectionResultsService;

@Service
public class ResultsCollectorService {

	private static final Logger log = Logger.getLogger(ResultsCollectorService.class.getName());
	
	public ResultsCollectorService() {
	}

	@Autowired
	private SingleElectionResultsService singleElectionConstitencyService;
	@Autowired
	private MultiElectionResultsService multiElectionResultsService;
	
	
	/**
	 * Method prepares all the data of multi elections as an MultiElectionResultsAll object List. For
	 * that it assembles MultiElectionConstituencyList, MultiElectionDistrictList and
	 * MultiElectionResults objects into one object.
	 * 
	 * @return
	 */
	public List<MultiElectionResultsAll> multiElectionResultsAll() {
		log.info("||--> Started..." );
		List<MultiElectionResultsAll> all = new ArrayList<MultiElectionResultsAll>();

		List<MultiElectionConstituencyList> multiElectionConstituencyList = multiElectionResultsService
				.getMultiElectionConstituencyList();

		for (MultiElectionConstituencyList mECL : multiElectionConstituencyList) {

			List<MultiElectionDistrictList> multiElectionDistrictList =  multiElectionResultsService
					.getResultsOfDistricts(mECL.getId());

			for (MultiElectionDistrictList mEDL : multiElectionDistrictList) {

				List<MultiElectionResults> multiElectionResults = multiElectionResultsService
						.getDistrictPartiesResults(mEDL.getId());

				for (MultiElectionResults mER : multiElectionResults) {

					MultiElectionResultsAll r = new MultiElectionResultsAll();
					/* constituency */
					r.setConId(mECL.getId());
					r.setConTitle(mECL.getTitle());
					r.setConNumberOfDistricts(mECL.getNumberOfDistricts());
					r.setConDistrictsPublishedResults(mECL.getDistrictsPublishedResults());
					r.setConNumberOfVoters(mECL.getNumberOfVoters());
					r.setConNumberOfVotersWhoVoted(mECL.getNumberOfVotersWhoVoted());
					r.setConPercentegeOfVoted(mECL.getPercentegeOfVoted());
					r.setConInvalidVotes(mECL.getInvalidVotes());
					r.setConPercentageOfInvalidVotes(mECL.getPercentageOfInvalidVotes());
					r.setConValidVotes(mECL.getValidVotes());
					r.setConPercentageOfValidVotes(mECL.getPercentageOfValidVotes());
					/* constituency */
					/* district */				
					r.setDistId(mEDL.getId());
					r.setDistTitle(mEDL.getTitle());
					r.setDistNumberOfVoters(mEDL.getNumberOfVoters());
					r.setDistNumberOfVotersWhoVoted(mEDL.getNumberOfVotersWhoVoted());
					r.setDistPercentageOfVoted(mEDL.getPercentageOfVoted());
					r.setDistInvalidVotes(mEDL.getInvalidVotes());
					r.setDistPercentageOfInvalidVotes(mEDL.getPercentageOfInvalidVotes());
					r.setDistValidVotes(mEDL.getValidVotes());
					r.setDistPercentageOfValidVotes(mEDL.getPercentageOfValidVotes());
					/* district */
					/* result */
					r.setResId(mER.getId());
					r.setResPartyTitle(mER.getPartyTitle());
					r.setResVotes(mER.getVotes());
					r.setResPercentageOfAllVotes(mER.getPercentageOfAllVotes());
					r.setResNumberOfMandates(mER.getNumberOfMandates());
					/* result */
					all.add(r);
				}
			}
		}
		log.info("||--> Finished!" );
		return all;
	}
	
	/**
	 * Method prepares all the data of single elections as an SingleElectionResultsAll object List. For
	 * that it assembles SingleElectionConstituency, SingleElectionDistrict and
	 * SingleElectionResult objects into one object.
	 * 
	 * @return
	 */
	public List<SingleElectionResultsAll> singleElectionResultsAll() {
		log.info("||--> Started..." );
		List<SingleElectionResultsAll> all = new ArrayList<SingleElectionResultsAll>();

		List<SingleElectionConstituency> singleConstituencyResultsList = singleElectionConstitencyService
				.singleElectionConstituencyResults();

		for (SingleElectionConstituency sEC : singleConstituencyResultsList) {

			List<SingleElectionDistrict> singleDistrictRelsultsByConsId = singleElectionConstitencyService
					.singleElectionDistrictResults(sEC.getConstituencyId());

			for (SingleElectionDistrict sED : singleDistrictRelsultsByConsId) {

				List<SingleElectionResult> singleRelsultsByDistId = singleElectionConstitencyService
						.getSingleElectionResultInDistrict(sED.getDistrictId());

				for (SingleElectionResult sER : singleRelsultsByDistId) {

					SingleElectionResultsAll r = new SingleElectionResultsAll();
					/* constituency */
					r.setConId(sEC.getConstituencyId());
					r.setConTitle(sEC.getTitle());
					r.setConNumberOfDistricts(sEC.getNumberOfDistricts());
					r.setConNumberOfDistrictsPublishedResults(sEC.getNumberOfDistrictsPublishedResults());
					r.setConNumberOfVoters(sEC.getNumberOfVoters());
					r.setConNumberOfVotersWhoVote(sEC.getNumberOfVotersWhoVote());
					r.setConPercentageOfVotersWhoVote(sEC.getPercentageOfVotersWhoVote());
					r.setConNumberOfInvalidVotes(sEC.getNumberOfInvalidVotes());
					r.setConPercentageOfInvalidVotes(sEC.getPercentageOfInvalidVotes());
					r.setConNumberOfValidVotes(sEC.getNumberOfValidVotes());
					r.setConPercentageOfValidVotes(sEC.getPercentageOfValidVotes());					
					/* constituency */
					/* district */
					r.setDistId(sED.getDistrictId());
					r.setDistTitle(sED.getTitle());
					r.setDistNumberOfVoters(sED.getNumberOfVoters());
					r.setDistVoted(sED.getVoted());
					r.setDistPercentageOfVoted(sED.getPercentageOfVoted());
					r.setDistInvalidVotes(sED.getInvalidVotes());
					r.setDistPercentageOfInvalidVotes(sED.getPercentageOfInvalidVotes());
					r.setDistValidVotes(sED.getValidVotes());
					r.setDistPercentageOfValidVotes(sED.getPercentageOfValidVotes());
					/* district */
					/* result */
					r.setResCandidateId(sER.getCandidateId());
					r.setResCandidateFirstname(sER.getCandidateFirstname());
					r.setResCandidateSurname(sER.getCandidateSurname());
					r.setResParty(sER.getParty());
					r.setResVoted(sER.getVoted());
					r.setResPercentageOfValidVotes(sER.getPercentageOfValidVotes());
					r.setResPercentageOfAllVotes(sER.getPercentageOfAllVotes());
					r.setResConsId(sER.getConsId());
					/* result */
					all.add(r);
				}
			}
		}
		log.info("||--> Finished!" );
		return all;
	}

	public String[] multiElectionResultsAllHeader() {
		log.info("||--> was used." );
		return new String[] {
				/* constituency */			
				"Apygardos Id",
				"Apygardos pavadinimas",
				"Apygardos apylinkiu skaicius",
				"Apygardos apylinkes paskelbusios rezultatus",
				"Apygardos balsuotoju skaicius",
				"Apygardos balsavusiuju skaicius",
				"Apygardos balsavusiuju % nuo apygardos balsuotoju",
				"Apygardos sugadinti balsai",
				"Apygardos sugadintu balsu % nuo apygarodos balsuotoju",
				"Apygardos geru balsu skaicius",
				"Apygardos geru balsu % nuo apygardos balsuotoju",
				/* constituency */
				/* district */
				"Apylinkes Id",
				"Apylinkes pavadinimas",
				"Apylinkes balsuotoju skaicius",
				"Apylinkes balsavusiuju skaicius",
				"Apylinkes balsavusiuju % nuo apylinkes balsuotoju",
				"Apylinkes sugadinti balsai",
				"Apylinkes sugadintu balsu % nuo apylinkes balsuotoju",
				"Apylinkes geru balsu skaicius",
				"Apylinkes geru balsu % nuo apylinkes balsuotoju",
				/* district */
				/* result */
				"Partijos Id",
				"Partijos pavadinimas",
				"Partijos balsu skaicius",
				"Partijos geru balsu % nuo apylinkes balsuotoju???"
//				"Partijos mandatai"
				/* result */	
				};	
	}
	
	public String[] singleElectionResultsAllHeader() {
		log.info("||--> was used." );
		return new String[] {
				/* constituency */				
				"Apygardos Id",
				"Apygardos pavadinimas",
				"Apygardos apylinkiu skaicius",
				"Apygardos apylinkes paskelbusios rezultatus",
				"Apygardos balsuotoju skaicius",
				"Apygardos balsavusiuju skaicius",
				"Apygardos balsavusiuju % nuo apygardos balsuotoju",
				"Apygardos sugadinti balsai",
				"Apygardos sugadintu balsu % nuo apygarodos balsuotoju",
				"Apygardos geru balsu skaicius",
				"Apygardos geru balsu % nuo apygardos balsuotoju",
				/* constituency */
				/* district */
				"Apylinkes Id",
				"Apylinkes pavadinimas",
				"Apylinkes balsuotoju skaicius",
				"Apylinkes balsavusiuju skaicius",
				"Apylinkes balsavusiuju % nuo apylinkes balsuotoju",
				"Apylinkes sugadinti balsai",
				"Apylinkes sugadintu balsu % nuo apylinkes balsuotoju",
				"Apylinkes geru balsu skaicius",
				"Apylinkes geru balsu % nuo apylinkes balsuotoju",
				/* district */
				/* result */
				"Kandidato Id",
				"Kandidato vardas",
				"Kandidato pavarde",
				"Kandidato partija",
				"Kandidato balsu skaicius",
				"Kandidato geru balsu % nuo apylinkes balsuotoju???",
				"Kandidato geru balsu % nuo visu balsuotoju???",
				"Kandidato Apygardos Id",
				/* result */	
				};	
	}
	
	public String[] multiElectionResultsAllToStringArray(MultiElectionResultsAll multiElectionResultsAll) {
		log.info("||--> was used." );
		return new String[] { 
				/* constituency */
				multiElectionResultsAll.getConId().toString(),
				multiElectionResultsAll.getConTitle(),
				multiElectionResultsAll.getConNumberOfDistricts().toString(),
				multiElectionResultsAll.getConDistrictsPublishedResults().toString(),
				multiElectionResultsAll.getConNumberOfVoters().toString(),
				multiElectionResultsAll.getConNumberOfVotersWhoVoted().toString(),
				multiElectionResultsAll.getConPercentegeOfVoted().toString(),
				multiElectionResultsAll.getConInvalidVotes().toString(),
				multiElectionResultsAll.getConPercentageOfInvalidVotes().toString(),
				multiElectionResultsAll.getConValidVotes().toString(),
				multiElectionResultsAll.getConPercentageOfValidVotes().toString(),
				/* constituency */
				/* district */
				multiElectionResultsAll.getDistId().toString(),
				multiElectionResultsAll.getDistTitle(),
				multiElectionResultsAll.getDistNumberOfVoters().toString(),
				multiElectionResultsAll.getDistNumberOfVotersWhoVoted().toString(),
				multiElectionResultsAll.getDistPercentageOfVoted().toString(),
				multiElectionResultsAll.getDistInvalidVotes().toString(),
				multiElectionResultsAll.getDistPercentageOfInvalidVotes().toString(),
				multiElectionResultsAll.getDistValidVotes().toString(),
				multiElectionResultsAll.getDistPercentageOfValidVotes().toString(),
				/* district */
				/* result */
				multiElectionResultsAll.getResId().toString(),
				multiElectionResultsAll.getResPartyTitle(),
				multiElectionResultsAll.getResVotes().toString(),
				multiElectionResultsAll.getResPercentageOfAllVotes().toString()
//				multiElectionResultsAll.getResNumberOfMandates().toString()
				/* result */			
				};
	}
	
	public String[] singleElectionResultsAllToStringArray(SingleElectionResultsAll singleElectionResultsAll) {
		log.info("||--> was used." );
		return new String[] { 
				/* constituency */
				singleElectionResultsAll.getConId().toString(),
				singleElectionResultsAll.getConTitle(),
				singleElectionResultsAll.getConNumberOfDistricts().toString(),
				singleElectionResultsAll.getConNumberOfDistrictsPublishedResults().toString(),
				singleElectionResultsAll.getConNumberOfVoters().toString(),
				singleElectionResultsAll.getConNumberOfVotersWhoVote().toString(),
				singleElectionResultsAll.getConPercentageOfVotersWhoVote().toString(),
				singleElectionResultsAll.getConNumberOfInvalidVotes().toString(),
				singleElectionResultsAll.getConPercentageOfInvalidVotes().toString(),
				singleElectionResultsAll.getConNumberOfValidVotes().toString(),
				singleElectionResultsAll.getConPercentageOfValidVotes().toString(),
				/* constituency */
				/* district */
				singleElectionResultsAll.getDistId().toString(),
				singleElectionResultsAll.getDistTitle(),
				singleElectionResultsAll.getDistNumberOfVoters().toString(),
				singleElectionResultsAll.getDistVoted().toString(),
				singleElectionResultsAll.getDistPercentageOfVoted().toString(),
				singleElectionResultsAll.getDistInvalidVotes().toString(),
				singleElectionResultsAll.getDistPercentageOfInvalidVotes().toString(),
				singleElectionResultsAll.getDistValidVotes().toString(),
				singleElectionResultsAll.getDistPercentageOfValidVotes().toString(),
				/* district */
				/* result */
				singleElectionResultsAll.getResCandidateId().toString(),
				singleElectionResultsAll.getResCandidateFirstname(),
				singleElectionResultsAll.getResCandidateSurname(),
				singleElectionResultsAll.getResParty(),
				singleElectionResultsAll.getResVoted().toString(),
				singleElectionResultsAll.getResPercentageOfValidVotes().toString(),
				singleElectionResultsAll.getResPercentageOfAllVotes().toString(),
				singleElectionResultsAll.getResConsId().toString()
				/* result */			
				};
	}
}
