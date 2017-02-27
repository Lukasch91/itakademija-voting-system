package vs.public_.multi.results;

import java.math.BigDecimal;

public class MultiElectionDistrictList {

	private Integer id;

	private String title;

	private Long numberOfVoters;

	private Long numberOfVotersWhoVoted;

	private BigDecimal percentageOfVoted;

	private Long invalidVotes;

	private BigDecimal percentageOfInvalidVotes;

	private Long validVotes;

	private BigDecimal percentageOfValidVotes;

	public MultiElectionDistrictList(Integer id, String title, Long numberOfVoters, Long numberOfVotersWhoVoted,
			BigDecimal percentageOfVoted, Long invalidVotes, BigDecimal percentageOfInvalidVotes, Long validVotes,
			BigDecimal percentageOfValidVotes) {
		super();
		this.id = id;
		this.title = title;
		this.numberOfVoters = numberOfVoters;
		this.numberOfVotersWhoVoted = numberOfVotersWhoVoted;
		this.percentageOfVoted = percentageOfVoted;
		this.invalidVotes = invalidVotes;
		this.percentageOfInvalidVotes = percentageOfInvalidVotes;
		this.validVotes = validVotes;
		this.percentageOfValidVotes = percentageOfValidVotes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Long getNumberOfVotersWhoVoted() {
		return numberOfVotersWhoVoted;
	}

	public void setNumberOfVotersWhoVoted(Long numberOfVotersWhoVoted) {
		this.numberOfVotersWhoVoted = numberOfVotersWhoVoted;
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

}
