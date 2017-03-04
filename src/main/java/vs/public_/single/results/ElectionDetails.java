package vs.public_.single.results;

import java.math.BigDecimal;

public class ElectionDetails {

	private Long numberOfDistricts;

	private Long numberOfConstituencies;

	private Long numberOfVoters;

	private Long numberOfVotersWhoVoted;

	private BigDecimal percentageOfVoters;

	private Long invalidVotes;

	private BigDecimal percentageOfInvalidVotes;

	private Long validvotes;

	private BigDecimal percentageOfValidVotes;

	public ElectionDetails(Long numberOfDistricts, Long numberOfConstituencies, Long numberOfVoters,
			Long numberOfVotersWhoVoted, BigDecimal percentageOfVoters, Long invalidVotes,
			BigDecimal percentageOfInvalidVotes, Long validvotes, BigDecimal percentageOfValidVotes) {
		super();
		this.numberOfDistricts = numberOfDistricts;
		this.numberOfConstituencies = numberOfConstituencies;
		this.numberOfVoters = numberOfVoters;
		this.numberOfVotersWhoVoted = numberOfVotersWhoVoted;
		this.percentageOfVoters = percentageOfVoters;
		this.invalidVotes = invalidVotes;
		this.percentageOfInvalidVotes = percentageOfInvalidVotes;
		this.validvotes = validvotes;
		this.percentageOfValidVotes = percentageOfValidVotes;
	}

	public Long getNumberOfDistricts() {
		return numberOfDistricts;
	}

	public void setNumberOfDistricts(Long numberOfDistricts) {
		this.numberOfDistricts = numberOfDistricts;
	}

	public Long getNumberOfConstituencies() {
		return numberOfConstituencies;
	}

	public void setNumberOfConstituencies(Long numberOfConstituencies) {
		this.numberOfConstituencies = numberOfConstituencies;
	}

	public Long getNumberOfVoters() {
		return numberOfVoters;
	}

	public void setNumberOfVoters(Long numberOfVoters) {
		this.numberOfVoters = numberOfVoters;
	}

	public Long getNumberOfVotersWhoVoted() {
		return numberOfVotersWhoVoted;
	}

	public void setNumberOfVotersWhoVoted(Long numberOfVotersWhoVoted) {
		this.numberOfVotersWhoVoted = numberOfVotersWhoVoted;
	}

	public BigDecimal getPercentageOfVoters() {
		return percentageOfVoters;
	}

	public void setPercentageOfVoters(BigDecimal percentageOfVoters) {
		this.percentageOfVoters = percentageOfVoters;
	}

	public Long getInvalidVotes() {
		return invalidVotes;
	}

	public void setInvalidVotes(Long invalidVotes) {
		this.invalidVotes = invalidVotes;
	}

	public BigDecimal getPercentageOfInvalidVotes() {
		return percentageOfInvalidVotes;
	}

	public void setPercentageOfInvalidVotes(BigDecimal percentageOfInvalidVotes) {
		this.percentageOfInvalidVotes = percentageOfInvalidVotes;
	}

	public Long getValidvotes() {
		return validvotes;
	}

	public void setValidvotes(Long validvotes) {
		this.validvotes = validvotes;
	}

	public BigDecimal getPercentageOfValidVotes() {
		return percentageOfValidVotes;
	}

	public void setPercentageOfValidVotes(BigDecimal percentageOfValidVotes) {
		this.percentageOfValidVotes = percentageOfValidVotes;
	}

}
