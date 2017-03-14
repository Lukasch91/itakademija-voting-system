
package vs.public_.download.results;

import java.math.BigDecimal;

public class MultiElectionResultsAll {

	/* constituency | class MultiElectionConstituencyList */
	private Integer conId;
	private String conTitle;
	private Long conNumberOfDistricts;
	private Long conDistrictsPublishedResults;
	private Long conNumberOfVoters;
	private Long conNumberOfVotersWhoVoted;
	private BigDecimal conPercentegeOfVoted;
	private Long conInvalidVotes;
	private BigDecimal conPercentageOfInvalidVotes;
	private Long conValidVotes;
	private BigDecimal conPercentageOfValidVotes;
	/* constituency */

	/* district | class MultiElectionDistrictList */
	private Integer distId;
	private String distTitle;
	private Long distNumberOfVoters;
	private Long distNumberOfVotersWhoVoted;
	private BigDecimal distPercentageOfVoted;
	private Long distInvalidVotes;
	private BigDecimal distPercentageOfInvalidVotes;
	private Long distValidVotes;
	private BigDecimal distPercentageOfValidVotes;
	/* district */

	/* result | class MultiElectionResults */
	private Integer resId;
	private String resPartyTitle;
	private Long resVotes;
	private BigDecimal resPercentageOfAllVotes;
	private Long resNumberOfMandates;
	/* result */

	public MultiElectionResultsAll() {
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

	public Long getConDistrictsPublishedResults() {
		return conDistrictsPublishedResults;
	}

	public void setConDistrictsPublishedResults(Long conDistrictsPublishedResults) {
		this.conDistrictsPublishedResults = conDistrictsPublishedResults;
	}

	public Long getConNumberOfVoters() {
		return conNumberOfVoters;
	}

	public void setConNumberOfVoters(Long conNumberOfVoters) {
		this.conNumberOfVoters = conNumberOfVoters;
	}

	public Long getConNumberOfVotersWhoVoted() {
		return conNumberOfVotersWhoVoted;
	}

	public void setConNumberOfVotersWhoVoted(Long conNumberOfVotersWhoVoted) {
		this.conNumberOfVotersWhoVoted = conNumberOfVotersWhoVoted;
	}

	public BigDecimal getConPercentegeOfVoted() {
		return conPercentegeOfVoted;
	}

	public void setConPercentegeOfVoted(BigDecimal conPercentegeOfVoted) {
		this.conPercentegeOfVoted = conPercentegeOfVoted;
	}

	public Long getConInvalidVotes() {
		return conInvalidVotes;
	}

	public void setConInvalidVotes(Long conInvalidVotes) {
		this.conInvalidVotes = conInvalidVotes;
	}

	public BigDecimal getConPercentageOfInvalidVotes() {
		return conPercentageOfInvalidVotes;
	}

	public void setConPercentageOfInvalidVotes(BigDecimal conPercentageOfInvalidVotes) {
		this.conPercentageOfInvalidVotes = conPercentageOfInvalidVotes;
	}

	public Long getConValidVotes() {
		return conValidVotes;
	}

	public void setConValidVotes(Long conValidVotes) {
		this.conValidVotes = conValidVotes;
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

	public Long getDistNumberOfVotersWhoVoted() {
		return distNumberOfVotersWhoVoted;
	}

	public void setDistNumberOfVotersWhoVoted(Long distNumberOfVotersWhoVoted) {
		this.distNumberOfVotersWhoVoted = distNumberOfVotersWhoVoted;
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

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public String getResPartyTitle() {
		return resPartyTitle;
	}

	public void setResPartyTitle(String resPartyTitle) {
		this.resPartyTitle = resPartyTitle;
	}

	public Long getResVotes() {
		return resVotes;
	}

	public void setResVotes(Long resVotes) {
		this.resVotes = resVotes;
	}

	public BigDecimal getResPercentageOfAllVotes() {
		return resPercentageOfAllVotes;
	}

	public void setResPercentageOfAllVotes(BigDecimal resPercentageOfAllVotes) {
		this.resPercentageOfAllVotes = resPercentageOfAllVotes;
	}

	public Long getResNumberOfMandates() {
		return resNumberOfMandates;
	}

	public void setResNumberOfMandates(Long resNumberOfMandates) {
		this.resNumberOfMandates = resNumberOfMandates;
	}

}
