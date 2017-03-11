package vs.public_.download.results;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.public_.single.results.SingleElectionConstituency;
import vs.public_.single.results.SingleElectionDistrict;
import vs.public_.single.results.SingleElectionResult;
import vs.public_.single.results.SingleElectionResultsService;
import com.opencsv.CSVWriter;

@Service
public class DownloadResultsService {

	public DownloadResultsService() {
	}

	@Autowired
	SingleElectionResultsService singleElectionConstitencyService;

	public String returnSelector(Integer request) {

		switch (request) {
		case 1:
			return request1();
		case 2:
			return request2();
		default:
			return null;
		}

	}

	public String request2() {

		List<SingleElectionResultsAll> all = new ArrayList<SingleElectionResultsAll>();

		List<SingleElectionConstituency> singleConstituencyResultsList = singleElectionConstitencyService
				.singleElectionConstituencyResults();

		// =================================================

		for (SingleElectionConstituency sEC : singleConstituencyResultsList) {

			List<SingleElectionDistrict> singleDistrictRelsultsByConsId = singleElectionConstitencyService
					.singleElectionDistrictResults(sEC.getConstituencyId());

			for (SingleElectionDistrict sED : singleDistrictRelsultsByConsId) {

				List<SingleElectionResult> singleRelsultsByDistId = singleElectionConstitencyService
						.getSingleElectionResultInDistrict(sED.getDistrictId());

				for (SingleElectionResult sER : singleRelsultsByDistId) {

					SingleElectionResultsAll r = new SingleElectionResultsAll();
					r.setConTitle(sEC.getTitle());
					r.setDistTitle(sED.getTitle());
					r.setResCandidateFirstname(sER.getCandidateFirstname());
					r.setResCandidateSurname(sER.getCandidateSurname());
					r.setResParty(sER.getParty());
					r.setResVoted(sER.getVoted());
					r.setResPercentageOfValidVotes(sER.getPercentageOfValidVotes());
					r.setResPercentageOfAllVotes(sER.getPercentageOfAllVotes());

					all.add(r);
				}
			}
		}

		// =================================================

		List<String[]> tableData = new ArrayList<String[]>();
		tableData.add(singleElectionResultsAllHeader());
		for (SingleElectionResultsAll sERA : all) {
			tableData.add(singleElectionResultsAllToStringArray(sERA));
		}
		String result = listArrayToCSVSting(tableData);
		return result;
	}

	private String request1() {
		List<SingleElectionConstituency> singleConstituencyResultsList = singleElectionConstitencyService
				.singleElectionConstituencyResults();

		List<String[]> tableData = new ArrayList<String[]>();

		for (SingleElectionConstituency sEC : singleConstituencyResultsList) {
			tableData.add(singleElectionConstituencyToStringArray(sEC));
		}

		String result = listArrayToCSVSting(tableData);

		return result;
	}

	public String[] singleElectionResultsAllHeader() {
		return new String[] { "Apygarda", "Apylinkė", "Kandidato vardas", "Kandidato pavardė", "Kandidato partija",
				"Balsavo rinkėjų", "Gerų balsų %", "Visų balsų % ??? :D" };
	}

	public String[] singleElectionResultsAllToStringArray(SingleElectionResultsAll singleElectionResultsAll) {
		return new String[] { singleElectionResultsAll.getConTitle(), singleElectionResultsAll.getDistTitle(),
				singleElectionResultsAll.getResCandidateFirstname(), singleElectionResultsAll.getResCandidateSurname(),
				singleElectionResultsAll.getResParty(), singleElectionResultsAll.getResVoted().toString(),
				singleElectionResultsAll.getResPercentageOfValidVotes().toString(),
				singleElectionResultsAll.getResPercentageOfAllVotes().toString() };
	}

	public String[] singleElectionConstituencyToStringArray(SingleElectionConstituency singleConstituencyResult) {
		return new String[] { singleConstituencyResult.getTitle(),
				singleConstituencyResult.getNumberOfDistricts().toString(),
				singleConstituencyResult.getNumberOfDistrictsPublishedResults().toString(),
				singleConstituencyResult.getNumberOfVoters().toString(),
				singleConstituencyResult.getNumberOfVotersWhoVote().toString(),
				singleConstituencyResult.getPercentageOfVotersWhoVote().toString(),
				singleConstituencyResult.getNumberOfValidVotes().toString(),
				singleConstituencyResult.getPercentageOfInvalidVotes().toString(),
				singleConstituencyResult.getNumberOfValidVotes().toString(),
				singleConstituencyResult.getPercentageOfValidVotes().toString() };
	}

	public String listArrayToCSVSting(List<String[]> listRows) {
		Writer stringWriter = new StringWriter();
		CSVWriter writer = new CSVWriter(stringWriter, ',');
		writer.writeAll(listRows);
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringWriter.toString();
	}

}
