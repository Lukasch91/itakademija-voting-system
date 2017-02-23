package vs.representative.features.election.single.results;

import java.math.BigDecimal;

public class SingleElectionResult {

	private Integer candidateId;

	private String candidateFirstname;

	private String candidateSurname;

	private String party;

	private Long voted;

	private BigDecimal percentageOfValidVotes;

	private BigDecimal percentageOfAllVotes;

	public SingleElectionResult(Integer candidateId, String candidateFirstname, String candidateSurname,
			String party, Long voted, BigDecimal percentageOfValidVotes, BigDecimal percentageOfAllVotes) {
		super();
		this.candidateId = candidateId;
		this.candidateFirstname = candidateFirstname;
		this.candidateSurname = candidateSurname;
		this.party = party;
		this.voted = voted;
		this.percentageOfValidVotes = percentageOfValidVotes;
		this.percentageOfAllVotes = percentageOfAllVotes;
	}

	public Integer getConstituencyId() {
		return candidateId;
	}

	public void setConstituencyId(Integer candidateId) {
		this.candidateId = candidateId;
	}

	public String getCandidateFirstname() {
		return candidateFirstname;
	}

	public void setCandidateFirstname(String candidateFirstname) {
		this.candidateFirstname = candidateFirstname;
	}

	public String getCandidateSurname() {
		return candidateSurname;
	}

	public void setCandidateSurname(String candidateSurname) {
		this.candidateSurname = candidateSurname;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public Long getVoted() {
		return voted;
	}

	public void setVoted(Long voted) {
		this.voted = voted;
	}

	public BigDecimal getPercentageOfValidVotes() {
		return percentageOfValidVotes;
	}

	public void setPercentageOfValidVotes(BigDecimal percentageOfValidVotes) {
		this.percentageOfValidVotes = percentageOfValidVotes;
	}

	public BigDecimal getPercentageOfAllVotes() {
		return percentageOfAllVotes;
	}

	public void setPercentageOfAllVotes(BigDecimal percentageOfAllVotes) {
		this.percentageOfAllVotes = percentageOfAllVotes;
	}

}
