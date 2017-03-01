package vs.public_.multi.results;

import java.util.Comparator;

public class MultiElectionResultsComperator implements Comparator<MultiElectionResults> {

	@Override
	public int compare(MultiElectionResults r1, MultiElectionResults r2) {
		if (r1.getNumberOfMandates() == null) {
			return (r2.getNumberOfMandates() == null) ? 0 : -1;
		}
		if (r2.getNumberOfMandates() == null) {
			return 1;
		}
		return r1.getNumberOfMandates().compareTo(r2.getNumberOfMandates());
	}

}
