package vs.public_.download.results;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vs.public_.single.results.SingleElectionConstituency;
import vs.public_.single.results.SingleElectionResultsService;
import com.opencsv.CSVWriter;

@Service
public class DownloadResultsService {

	public DownloadResultsService() {
	}

	@Autowired
	SingleElectionResultsService singleElectionConstitencyService;

	public String returnSelector(Integer request) {

		List<SingleElectionConstituency> singleConstituencyResultsList = singleElectionConstitencyService
				.singleElectionConstituencyResults();

		List<String[]> tableData = new ArrayList<String[]>();

		for (SingleElectionConstituency sEC : singleConstituencyResultsList) {
			tableData.add(singleElectionConstituencyToStringArray(sEC));
		}

		String result = listArrayToCSVSting(tableData);

		return (result + "\n\nXXX" + request);
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
