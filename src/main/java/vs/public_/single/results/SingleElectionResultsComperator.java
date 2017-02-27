package vs.public_.single.results;

import java.util.Comparator;

public class SingleElectionResultsComperator implements Comparator<SingleElectionResult> {

	@Override
	public int compare(SingleElectionResult r1, SingleElectionResult r2) {
		return r1.getVoted().compareTo(r2.getVoted());
	}
}
