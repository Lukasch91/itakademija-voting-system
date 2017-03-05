package vs.public_.multi.results;

import java.util.Comparator;

public class MultiElectionResultsComperator implements Comparator<MultiElectionResults> {

	@Override
	public int compare(MultiElectionResults r1, MultiElectionResults r2) {
		if (r1.getVotes() == null) {
			return (r2.getVotes() == null) ? 0 : -1;
		}
		if (r2.getVotes() == null) {
			return 1;
		}
		return r1.getVotes().compareTo(r2.getVotes());
	}

}
