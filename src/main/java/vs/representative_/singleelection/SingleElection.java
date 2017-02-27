package vs.representative_.singleelection;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import vs.admin_.candidate.Candidate;
import vs.admin_.district.District;

@Entity
@Table(name = "single_member_votes")
public class SingleElection {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer singleId;

	@Column
	@NotNull(message = "Būtina įvesti balsų skaičių")
	@Min(value = 0, message = "Minimalus balsų skaičius {value}")
	@Max(value = 500000, message = "Maksimalus balsų skaičius {value}")
	private Long singleVotes;

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

	public SingleElection(Integer singleId, Long singleVotes, Date singleEnteredDate, Date singlePublishedDate,
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

	public Long getSingleVotes() {
		return singleVotes;
	}

	public void setSingleVotes(Long singleVotes) {
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
