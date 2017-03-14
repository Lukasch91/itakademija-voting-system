package vs.public_.multi.results;

import java.math.BigDecimal;

public class MultiElectionResults {

	private Integer id;

	private String partyTitle;

	private Long votes;

	private BigDecimal percentageOfAllVotes;

	private Long numberOfMandates;

	public MultiElectionResults(Integer id, String partyTitle, Long votes, BigDecimal percentageOfAllVotes,
			Long numberOfMandates) {
		super();
		this.id = id;
		this.partyTitle = partyTitle;
		this.votes = votes;
		this.percentageOfAllVotes = percentageOfAllVotes;
		this.numberOfMandates = numberOfMandates;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPartyTitle() {
		return partyTitle;
	}

	public void setPartyTitle(String partyTitle) {
		this.partyTitle = partyTitle;
	}

	public Long getVotes() {
		return votes;
	}

	public void setVotes(Long votes) {
		this.votes = votes;
	}

	public BigDecimal getPercentageOfAllVotes() {
		return percentageOfAllVotes;
	}

	public void setPercentageOfAllVotes(BigDecimal percentageOfAllVotes) {
		this.percentageOfAllVotes = percentageOfAllVotes;
	}

	public Long getNumberOfMandates() {
		return numberOfMandates;
	}

	public void setNumberOfMandates(Long numberOfMandates) {
		this.numberOfMandates = numberOfMandates;
	}

}
