package vs.public_.multi.results;

import java.math.BigDecimal;

public class ElectionDistrictDetails {

	private Integer districtId;

	private String districtTitle;

	private String constituencyTitle;

	private Integer constituencyId;

	private Long voters;

	private Long validVotes;

	private Long invalidVotes;

	private Long allVotes;

	private BigDecimal percentageOfVoted;

	private BigDecimal percentageOfInvalidVotes;

	private BigDecimal percentageOfValidVotes;

	private String updateDate;

	public ElectionDistrictDetails(Integer districtId, String districtTitle, String constituencyTitle,
			Integer constituencyId, Long voters, Long validVotes, Long invalidVotes, Long allVotes,
			BigDecimal percentageOfVoted, BigDecimal percentageOfInvalidVotes, BigDecimal percentageOfValidVotes,
			String updateDate) {
		super();
		this.districtId = districtId;
		this.districtTitle = districtTitle;
		this.constituencyTitle = constituencyTitle;
		this.constituencyId = constituencyId;
		this.voters = voters;
		this.validVotes = validVotes;
		this.invalidVotes = invalidVotes;
		this.allVotes = allVotes;
		this.percentageOfVoted = percentageOfVoted;
		this.percentageOfInvalidVotes = percentageOfInvalidVotes;
		this.percentageOfValidVotes = percentageOfValidVotes;
		this.updateDate = updateDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getDistrictTitle() {
		return districtTitle;
	}

	public void setDistrictTitle(String districtTitle) {
		this.districtTitle = districtTitle;
	}

	public String getConstituencyTitle() {
		return constituencyTitle;
	}

	public void setConstituencyTitle(String constituencyTitle) {
		this.constituencyTitle = constituencyTitle;
	}

	public Integer getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Integer constituencyId) {
		this.constituencyId = constituencyId;
	}

	public Long getVoters() {
		return voters;
	}

	public void setVoters(Long voters) {
		this.voters = voters;
	}

	public Long getValidVotes() {
		return validVotes;
	}

	public void setValidVotes(Long validVotes) {
		this.validVotes = validVotes;
	}

	public Long getInvalidVotes() {
		return invalidVotes;
	}

	public void setInvalidVotes(Long invalidVotes) {
		this.invalidVotes = invalidVotes;
	}

	public Long getAllVotes() {
		return allVotes;
	}

	public void setAllVotes(Long allVotes) {
		this.allVotes = allVotes;
	}

	public BigDecimal getPercentageOfVoted() {
		return percentageOfVoted;
	}

	public void setPercentageOfVoted(BigDecimal percentageOfVoted) {
		this.percentageOfVoted = percentageOfVoted;
	}

	public BigDecimal getPercentageOfInvalidVotes() {
		return percentageOfInvalidVotes;
	}

	public void setPercentageOfInvalidVotes(BigDecimal percentageOfInvalidVotes) {
		this.percentageOfInvalidVotes = percentageOfInvalidVotes;
	}

	public BigDecimal getPercentageOfValidVotes() {
		return percentageOfValidVotes;
	}

	public void setPercentageOfValidVotes(BigDecimal percentageOfValidVotes) {
		this.percentageOfValidVotes = percentageOfValidVotes;
	}

}
