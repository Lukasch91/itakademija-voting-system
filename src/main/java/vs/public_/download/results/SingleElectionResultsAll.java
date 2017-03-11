package vs.public_.download.results;

import java.math.BigDecimal;

public class SingleElectionResultsAll {

	/* constituency */

	private Integer conId;

	private String conTitle;

	private Long conNumberOfDistricts;

	private Long conNumberOfDistrictsPublishedResults;

	private Long conNumberOfVoters;

	private Long conNumberOfVotersWhoVote;

	private BigDecimal conPercentageOfVotersWhoVote;

	private Long conNumberOfInvalidVotes;

	private BigDecimal conPercentageOfInvalidVotes;

	private Long conNumberOfValidVotes;

	private BigDecimal conPercentageOfValidVotes;

	/* constituency */

	/* district */

	private Integer distId;

	private String distTitle;

	private Long distNumberOfVoters;

	private Long distVoted;

	private BigDecimal distPercentageOfVoted;

	private Long distInvalidVotes;

	private BigDecimal distPercentageOfInvalidVotes;

	private Long distValidVotes;

	private BigDecimal distPercentageOfValidVotes;

	/* district */

	/* result */

	private Integer resCandidateId;

	private String resCandidateFirstname;

	private String resCandidateSurname;

	private String resParty;

	private Long resVoted;

	private BigDecimal resPercentageOfValidVotes;

	private BigDecimal resPercentageOfAllVotes;

	private Integer resConsId;

	/* result */

	public SingleElectionResultsAll() {
	}

	public Integer getConId() {
		return conId;
	}

	public void setConId(Integer conId) {
		this.conId = conId;
	}

	public String getConTitle() {
		return conTitle;
	}

	public void setConTitle(String conTitle) {
		this.conTitle = conTitle;
	}

	public Long getConNumberOfDistricts() {
		return conNumberOfDistricts;
	}

	public void setConNumberOfDistricts(Long conNumberOfDistricts) {
		this.conNumberOfDistricts = conNumberOfDistricts;
	}

	public Long getConNumberOfDistrictsPublishedResults() {
		return conNumberOfDistrictsPublishedResults;
	}

	public void setConNumberOfDistrictsPublishedResults(Long conNumberOfDistrictsPublishedResults) {
		this.conNumberOfDistrictsPublishedResults = conNumberOfDistrictsPublishedResults;
	}

	public Long getConNumberOfVoters() {
		return conNumberOfVoters;
	}

	public void setConNumberOfVoters(Long conNumberOfVoters) {
		this.conNumberOfVoters = conNumberOfVoters;
	}

	public Long getConNumberOfVotersWhoVote() {
		return conNumberOfVotersWhoVote;
	}

	public void setConNumberOfVotersWhoVote(Long conNumberOfVotersWhoVote) {
		this.conNumberOfVotersWhoVote = conNumberOfVotersWhoVote;
	}

	public BigDecimal getConPercentageOfVotersWhoVote() {
		return conPercentageOfVotersWhoVote;
	}

	public void setConPercentageOfVotersWhoVote(BigDecimal conPercentageOfVotersWhoVote) {
		this.conPercentageOfVotersWhoVote = conPercentageOfVotersWhoVote;
	}

	public Long getConNumberOfInvalidVotes() {
		return conNumberOfInvalidVotes;
	}

	public void setConNumberOfInvalidVotes(Long conNumberOfInvalidVotes) {
		this.conNumberOfInvalidVotes = conNumberOfInvalidVotes;
	}

	public BigDecimal getConPercentageOfInvalidVotes() {
		return conPercentageOfInvalidVotes;
	}

	public void setConPercentageOfInvalidVotes(BigDecimal conPercentageOfInvalidVotes) {
		this.conPercentageOfInvalidVotes = conPercentageOfInvalidVotes;
	}

	public Long getConNumberOfValidVotes() {
		return conNumberOfValidVotes;
	}

	public void setConNumberOfValidVotes(Long conNumberOfValidVotes) {
		this.conNumberOfValidVotes = conNumberOfValidVotes;
	}

	public BigDecimal getConPercentageOfValidVotes() {
		return conPercentageOfValidVotes;
	}

	public void setConPercentageOfValidVotes(BigDecimal conPercentageOfValidVotes) {
		this.conPercentageOfValidVotes = conPercentageOfValidVotes;
	}

	public Integer getDistId() {
		return distId;
	}

	public void setDistId(Integer distId) {
		this.distId = distId;
	}

	public String getDistTitle() {
		return distTitle;
	}

	public void setDistTitle(String distTitle) {
		this.distTitle = distTitle;
	}

	public Long getDistNumberOfVoters() {
		return distNumberOfVoters;
	}

	public void setDistNumberOfVoters(Long distNumberOfVoters) {
		this.distNumberOfVoters = distNumberOfVoters;
	}

	public Long getDistVoted() {
		return distVoted;
	}

	public void setDistVoted(Long distVoted) {
		this.distVoted = distVoted;
	}

	public BigDecimal getDistPercentageOfVoted() {
		return distPercentageOfVoted;
	}

	public void setDistPercentageOfVoted(BigDecimal distPercentageOfVoted) {
		this.distPercentageOfVoted = distPercentageOfVoted;
	}

	public Long getDistInvalidVotes() {
		return distInvalidVotes;
	}

	public void setDistInvalidVotes(Long distInvalidVotes) {
		this.distInvalidVotes = distInvalidVotes;
	}

	public BigDecimal getDistPercentageOfInvalidVotes() {
		return distPercentageOfInvalidVotes;
	}

	public void setDistPercentageOfInvalidVotes(BigDecimal distPercentageOfInvalidVotes) {
		this.distPercentageOfInvalidVotes = distPercentageOfInvalidVotes;
	}

	public Long getDistValidVotes() {
		return distValidVotes;
	}

	public void setDistValidVotes(Long distValidVotes) {
		this.distValidVotes = distValidVotes;
	}

	public BigDecimal getDistPercentageOfValidVotes() {
		return distPercentageOfValidVotes;
	}

	public void setDistPercentageOfValidVotes(BigDecimal distPercentageOfValidVotes) {
		this.distPercentageOfValidVotes = distPercentageOfValidVotes;
	}

	public Integer getResCandidateId() {
		return resCandidateId;
	}

	public void setResCandidateId(Integer resCandidateId) {
		this.resCandidateId = resCandidateId;
	}

	public String getResCandidateFirstname() {
		return resCandidateFirstname;
	}

	public void setResCandidateFirstname(String resCandidateFirstname) {
		this.resCandidateFirstname = resCandidateFirstname;
	}

	public String getResCandidateSurname() {
		return resCandidateSurname;
	}

	public void setResCandidateSurname(String resCandidateSurname) {
		this.resCandidateSurname = resCandidateSurname;
	}

	public String getResParty() {
		return resParty;
	}

	public void setResParty(String resParty) {
		this.resParty = resParty;
	}

	public Long getResVoted() {
		return resVoted;
	}

	public void setResVoted(Long resVoted) {
		this.resVoted = resVoted;
	}

	public BigDecimal getResPercentageOfValidVotes() {
		return resPercentageOfValidVotes;
	}

	public void setResPercentageOfValidVotes(BigDecimal resPercentageOfValidVotes) {
		this.resPercentageOfValidVotes = resPercentageOfValidVotes;
	}

	public BigDecimal getResPercentageOfAllVotes() {
		return resPercentageOfAllVotes;
	}

	public void setResPercentageOfAllVotes(BigDecimal resPercentageOfAllVotes) {
		this.resPercentageOfAllVotes = resPercentageOfAllVotes;
	}

	public Integer getResConsId() {
		return resConsId;
	}

	public void setResConsId(Integer resConsId) {
		this.resConsId = resConsId;
	}
}
