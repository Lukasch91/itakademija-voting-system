package vs.public_.download.results;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.opencsv.CSVWriter;

import vs.public_.consolidated.results.ConsolidatedResults;
import vs.public_.consolidated.results.ConsolidatedResultsService;
import vs.public_.consolidated.results.ConsolidatetPartyListService;
import vs.public_.consolidated.results.MemberOfParliament;

@Service
public class DownloadResultsService {

	private static final Logger log = Logger.getLogger(DownloadResultsService.class.getName());

	public DownloadResultsService() {
	}

	@Autowired
	private ResultsCollectorService rCS;
	@Autowired
	private ConsolidatetPartyListService cPS;
	@Autowired
	private ConsolidatedResultsService cRS;

	public String returnSelector(Integer request) {

		log.info("||--> was used.");

		switch (request) {
		case 1:
			return request1();
		case 2:
			return request2();
		case 3:
			return request3();
		case 4:
			return request4();
		case 5:
			return request5();
		case 6:
			return request6();
		default:
			return "Klaida";
		}
	}

	private String request1() {
		log.info("||--> Started...");

		List<String[]> tableData = new ArrayList<String[]>();
		List<SingleElectionResultsAll> singleElectionResultsAll = rCS.singleElectionResultsAll();

		tableData.add(rCS.singleElectionResultsAllHeader());
		for (SingleElectionResultsAll sERA : singleElectionResultsAll) {
			tableData.add(rCS.singleElectionResultsAllToStringArray(sERA));
		}

		int[] have = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
				27 };

		String result = listArrayToCSVSting(dataLimiter(tableData, have));
		log.info("||--> Finished!");
		return result;
	}

	private String request2() {
		log.info("||--> Started...");

		List<String[]> tableData = new ArrayList<String[]>();
		List<MultiElectionResultsAll> multiElectionResultsAll = rCS.multiElectionResultsAll();

		tableData.add(rCS.multiElectionResultsAllHeader());
		for (MultiElectionResultsAll mERA : multiElectionResultsAll) {
			tableData.add(rCS.multiElectionResultsAllToStringArray(mERA));
		}

		int[] have = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 };

		String result = listArrayToCSVSting(dataLimiter(tableData, have));
		log.info("||--> Finished!");
		return result;
	}

	private String request3() {
		log.info("||--> Started...");

		List<String[]> tableData = new ArrayList<String[]>();
		List<SingleElectionResultsAll> singleElectionResultsAll = rCS.singleElectionResultsAll();

		tableData.add(rCS.singleElectionResultsAllHeader());
		for (SingleElectionResultsAll sERA : singleElectionResultsAll) {
			tableData.add(rCS.singleElectionResultsAllToStringArray(sERA));
		}

		int[] have = { 1, 12, 21, 22, 23, 24, 25, 26, 27 };

		String result = listArrayToCSVSting(dataLimiter(tableData, have));
		log.info("||--> Finished!");
		return result;
	}

	private String request4() {
		log.info("||--> Started...");

		List<String[]> tableData = new ArrayList<String[]>();
		List<MultiElectionResultsAll> multiElectionResultsAll = rCS.multiElectionResultsAll();

		tableData.add(rCS.multiElectionResultsAllHeader());
		for (MultiElectionResultsAll mERA : multiElectionResultsAll) {
			tableData.add(rCS.multiElectionResultsAllToStringArray(mERA));
		}

		int[] have = { 1, 12, 21, 22, 23 };

		String result = listArrayToCSVSting(dataLimiter(tableData, have));
		log.info("||--> Finished!");
		return result;
	}

	private String request5() {
		log.info("||--> Started...");
		List<String[]> tableData = new ArrayList<String[]>();
		List<MemberOfParliament> memberOfParliament = cRS.getSortedMemberOfParlList();

		tableData.add(rCS.memberOfParliamentHeader());
		for (MemberOfParliament mOP : memberOfParliament) {
			tableData.add(rCS.memberOfParliamentToStringArray(mOP));
		}
		String result = listArrayToCSVSting(tableData);
		log.info("||--> Finished!");
		return result;
	}

	private String request6() {
		log.info("||--> Started...");
		List<String[]> tableData = new ArrayList<String[]>();
		List<ConsolidatedResults> consolidatedResults = cPS.getSortedPartyList();

		tableData.add(rCS.consolidatedResultsHeader());
		for (ConsolidatedResults cR : consolidatedResults) {
			tableData.add(rCS.consolidatedResultsToStringArray(cR));
		}
		String result = listArrayToCSVSting(tableData);
		log.info("||--> Finished!");
		return result;
	}

	private List<String[]> dataLimiter(List<String[]> rowListToLimit, int[] columnsToHave) {
		log.info("||--> Started...");
		List<String[]> newList = new ArrayList<String[]>();

		for (String[] row : rowListToLimit) {
			String[] newRow = new String[columnsToHave.length];
			for (int a = 0; a < columnsToHave.length; a++) {
				newRow[a] = row[columnsToHave[a]];
			}
			newList.add(newRow);
		}
		log.info("||--> Finished!");
		return newList;
	}

	public String listArrayToCSVSting(List<String[]> listRows) {
		log.info("||--> Started...");
		Writer stringWriter = new StringWriter();
		CSVWriter writer = new CSVWriter(stringWriter, ',');
		writer.writeAll(listRows);
		try {
			writer.close();
		} catch (IOException e) {
			log.error("||--> IO exception trying to close. " + e);
			e.printStackTrace();
		}
		log.info("||--> Finished!");
		return stringWriter.toString();
	}

}
