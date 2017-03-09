package vs.public_.consolidated.results;

public class ConsolidatedResults {

	private String partyTitle;

	private Long mandates;

	public ConsolidatedResults(String partyTitle, Long mandates) {
		super();

		this.partyTitle = partyTitle;
		this.mandates = mandates;

	}

	public String getPartyTitle() {
		return partyTitle;
	}

	public void setPartyTitle(String partyTitle) {
		this.partyTitle = partyTitle;
	}

	public Long getMandates() {
		return mandates;
	}

	public void setMandates(Long mandates) {
		this.mandates = mandates;
	}

}