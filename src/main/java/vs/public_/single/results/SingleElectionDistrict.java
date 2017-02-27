package vs.public_.single.results;

import java.math.BigDecimal;

public class SingleElectionDistrict {

	private Integer districtId;

	private String title;

	private Long numberOfVoters;

	private Long voted;

	private BigDecimal percentageOfVoted;

	private Long invalidVotes;

	private BigDecimal percentageOfInvalidVotes;

	private Long validVotes;

	private BigDecimal percentageOfValidVotes;

	public SingleElectionDistrict(Integer districtId, String title, Long numberOfVoters, Long voted,
			BigDecimal percentageOfVoted, Long invalidVotes, BigDecimal percentageOfInvalidVotes, Long validVotes,
			BigDecimal percentageOfValidVotes) {
		super();
		this.districtId = districtId;
		this.title = title;
		this.numberOfVoters = numberOfVoters;
		this.voted = voted;
		this.percentageOfVoted = percentageOfVoted;
		this.invalidVotes = invalidVotes;
		this.percentageOfInvalidVotes = percentageOfInvalidVotes;
		this.validVotes = validVotes;
		this.percentageOfValidVotes = percentageOfValidVotes;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getNumberOfVoters() {
		return numberOfVoters;
	}

	public void setNumberOfVoters(Long numberOfVoters) {
		this.numberOfVoters = numberOfVoters;
	}

	public BigDecimal getPercentageOfVoted() {
		return percentageOfVoted;
	}

	public void setPercentageOfVoted(BigDecimal percentageOfVoted) {
		this.percentageOfVoted = percentageOfVoted;
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

	public Long getValidVotes() {
		return validVotes;
	}

	public void setValidVotes(Long validVotes) {
		this.validVotes = validVotes;
	}

	public BigDecimal getPercentageOfValidVotes() {
		return percentageOfValidVotes;
	}

	public void setPercentageOfValidVotes(BigDecimal percentageOfValidVotes) {
		this.percentageOfValidVotes = percentageOfValidVotes;
	}

	public Long getVoted() {
		return voted;
	}

	public void setVoted(Long voted) {
		this.voted = voted;
	}

}
