package vs.public_.download.resultsTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import vs.public_.download.results.DownloadResultsService;


public class DownloadResultsServiceTest {

	DownloadResultsService downloadResultsService = new DownloadResultsService();
	List<String[]> testList = new ArrayList<String[]>();
	
	@Test
	public void stringToCSVSting() throws IOException {
		
		String[] entries = "first#second#third".split("#");
		String[] entries2 = "fourth#fifth#6".split("#");
		String[] entries3 = "first#se\"c\"ond#tex, xx".split("#");
		
		testList.add(entries);
		testList.add(entries2);		
		testList.add(entries3);
		
		System.out.println(downloadResultsService.listArrayToCSVSting(testList));
			
	}
	
	
	
}
