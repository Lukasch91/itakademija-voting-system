package vs.representative.features.election.single.results;

import java.math.BigDecimal;

public class SingleElectionConstituency {

	private Integer constituencyId;

	private String title;

	private Long numberOfDistricts;

	private Long numberOfDistrictsPublishedResults;

	private Long numberOfVoters;

	private Long numberOfVotersWhoVote;

	private BigDecimal percentageOfVotersWhoVote;

	private Long numberOfInvalidVotes;

	private BigDecimal percentageOfInvalidVotes;

	private Long numberOfValidVotes;

	private BigDecimal percentageOfValidVotes;

	public SingleElectionConstituency(Integer constituencyId, String title, Long numberOfDistricts,
			Long numberOfDistrictsPublishedResults, Long numberOfVoters, Long numberOfVotersWhoVote,
			BigDecimal percentageOfVotersWhoVote, Long numberOfInvalidVotes, BigDecimal percentageOfInvalidVotes,
			Long numberOfValidVotes, BigDecimal percentageOfValidVotes) {
		super();
		this.constituencyId = constituencyId;
		this.title = title;
		this.numberOfDistricts = numberOfDistricts;
		this.numberOfDistrictsPublishedResults = numberOfDistrictsPublishedResults;
		this.numberOfVoters = numberOfVoters;
		this.numberOfVotersWhoVote = numberOfVotersWhoVote;
		this.percentageOfVotersWhoVote = percentageOfVotersWhoVote;
		this.numberOfInvalidVotes = numberOfInvalidVotes;
		this.percentageOfInvalidVotes = percentageOfInvalidVotes;
		this.numberOfValidVotes = numberOfValidVotes;
		this.percentageOfValidVotes = percentageOfValidVotes;
	}

	public Integer getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Integer constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getNumberOfDistricts() {
		return numberOfDistricts;
	}

	public void setNumberOfDistricts(Long numberOfDistricts) {
		this.numberOfDistricts = numberOfDistricts;
	}

	public Long getNumberOfDistrictsPublishedResults() {
		return numberOfDistrictsPublishedResults;
	}

	public void setNumberOfDistrictsPublishedResults(Long numberOfDistrictsPublishedResults) {
		this.numberOfDistrictsPublishedResults = numberOfDistrictsPublishedResults;
	}

	public Long getNumberOfVoters() {
		return numberOfVoters;
	}

	public void setNumberOfVoters(Long numberOfVoters) {
		this.numberOfVoters = numberOfVoters;
	}

	public Long getNumberOfVotersWhoVote() {
		return numberOfVotersWhoVote;
	}

	public void setNumberOfVotersWhoVote(Long numberOfVotersWhoVote) {
		this.numberOfVotersWhoVote = numberOfVotersWhoVote;
	}

	public BigDecimal getPercentageOfVotersWhoVote() {
		return percentageOfVotersWhoVote;
	}

	public void setPercentageOfVotersWhoVote(BigDecimal percentageOfVotersWhoVote) {
		this.percentageOfVotersWhoVote = percentageOfVotersWhoVote;
	}

	public Long getNumberOfInvalidVotes() {
		return numberOfInvalidVotes;
	}

	public void setNumberOfInvalidVotes(Long numberOfInvalidVotes) {
		this.numberOfInvalidVotes = numberOfInvalidVotes;
	}

	public BigDecimal getPercentageOfInvalidVotes() {
		return percentageOfInvalidVotes;
	}

	public void setPercentageOfInvalidVotes(BigDecimal percentageOfInvalidVotes) {
		this.percentageOfInvalidVotes = percentageOfInvalidVotes;
	}

	public Long getNumberOfValidVotes() {
		return numberOfValidVotes;
	}

	public void setNumberOfValidVotes(Long numberOfValidVotes) {
		this.numberOfValidVotes = numberOfValidVotes;
	}

	public BigDecimal getPercentageOfValidVotes() {
		return percentageOfValidVotes;
	}

	public void setPercentageOfValidVotes(BigDecimal percentageOfValidVotes) {
		this.percentageOfValidVotes = percentageOfValidVotes;
	}

}