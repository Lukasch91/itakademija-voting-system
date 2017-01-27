package vs.representative.features.single.election;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vs.admin.features.admin.district.District;
import vs.admin.features.candidate.model.Candidate;

@Entity
@Table(name = "single_member_votes")
public class SingleElection {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer singleId;

	@Column
	private Integer singleVotes;

	@Column
	private Date singleEnteredDate;

	@Column
	private Date singlePublishedDate;

	@Column
	private Date singleDeletedDate;

	/* ====================================== */
	@ManyToOne
	@JoinColumn(name = "singleCandidate") // referencedColumnName = "candidateID"
	private Candidate singleCandidate = new Candidate();

	@ManyToOne
	@JoinColumn(name = "singleDistrict") // referencedColumnName = "id"
	private District singleDistrict = new District();
	/* ====================================== */

	public SingleElection() {
	}

	public SingleElection(Integer singleId, Integer singleVotes, Date singleEnteredDate, Date singlePublishedDate,
			Date singleDeletedDate, Candidate singleCandidate, District singleDistrict) {
		super();
		this.singleId = singleId;
		this.singleVotes = singleVotes;
		this.singleEnteredDate = singleEnteredDate;
		this.singlePublishedDate = singlePublishedDate;
		this.singleDeletedDate = singleDeletedDate;
		this.singleCandidate = singleCandidate;
		this.singleDistrict = singleDistrict;
	}

	public Integer getSingleId() {
		return singleId;
	}

	public void setSingleId(Integer singleId) {
		this.singleId = singleId;
	}

	public Integer getSingleVotes() {
		return singleVotes;
	}

	public void setSingleVotes(Integer singleVotes) {
		this.singleVotes = singleVotes;
	}

	public Date getSingleEnteredDate() {
		return singleEnteredDate;
	}

	public void setSingleEnteredDate(Date singleEnteredDate) {
		this.singleEnteredDate = singleEnteredDate;
	}

	public Date getSinglePublishedDate() {
		return singlePublishedDate;
	}

	public void setSinglePublishedDate(Date singlePublishedDate) {
		this.singlePublishedDate = singlePublishedDate;
	}

	public Date getSingleDeletedDate() {
		return singleDeletedDate;
	}

	public void setSingleDeletedDate(Date singleDeletedDate) {
		this.singleDeletedDate = singleDeletedDate;
	}

	public Candidate getSingleCandidate() {
		return singleCandidate;
	}

	public void setSingleCandidate(Candidate singleCandidate) {
		this.singleCandidate = singleCandidate;
	}

	public District getSingleDistrict() {
		return singleDistrict;
	}

	public void setSingleDistrict(District singleDistrict) {
		this.singleDistrict = singleDistrict;
	}

}
