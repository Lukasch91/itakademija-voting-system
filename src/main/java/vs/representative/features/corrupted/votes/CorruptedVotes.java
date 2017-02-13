package vs.representative.features.corrupted.votes;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import vs.admin.features.admin.district.District;

@Entity
@Table
public class CorruptedVotes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;
	
	@OneToOne
	private  District district;
	
	@Column
	private BigDecimal votes;
	
	@Column
	private String singleOrMulti;
	
	@Column
	private Date entered_date;

	@Column
	private Date published_date;

	@Column
	private Date deleted_date;
	
	public CorruptedVotes() {

	}

	public CorruptedVotes(Integer id, District district, BigDecimal votes, String singleOrMulti, Date entered_date,
			Date published_date, Date deleted_date) {
		super();
		this.id = id;
		this.district = district;
		this.votes = votes;
		this.singleOrMulti = singleOrMulti;
		this.entered_date = entered_date;
		this.published_date = published_date;
		this.deleted_date = deleted_date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public BigDecimal getVotes() {
		return votes;
	}

	public void setVotes(BigDecimal votes) {
		this.votes = votes;
	}

	public String getSingleOrMulti() {
		return singleOrMulti;
	}

	public void setSingleOrMulti(String singleOrMulti) {
		this.singleOrMulti = singleOrMulti;
	}

	public Date getEntered_date() {
		return entered_date;
	}

	public void setEntered_date(Date entered_date) {
		this.entered_date = entered_date;
	}

	public Date getPublished_date() {
		return published_date;
	}

	public void setPublished_date(Date published_date) {
		this.published_date = published_date;
	}

	public Date getDeleted_date() {
		return deleted_date;
	}

	public void setDeleted_date(Date deleted_date) {
		this.deleted_date = deleted_date;
	}


	
	
	
	
}
