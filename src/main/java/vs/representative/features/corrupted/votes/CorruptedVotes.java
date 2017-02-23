package vs.representative.features.corrupted.votes;

import java.math.BigDecimal;
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

import vs.admin.features.admin.district.District;

@Entity
@Table
public class CorruptedVotes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;

	@ManyToOne
	@JoinColumn
	private District district = new District();

	@Column
	@NotNull(message = "Būtina įvesti balsų skaičių")
	@Min(value = 0, message = "Minimalus balsų skaičius {value}")
	@Max(value = 500000, message = "Maksimalus balsų skaičius {value}")
	private BigDecimal votes;

	@Column
	private Boolean typeMulti;

	@Column
	private Date entered_date;

	@Column
	private Date published_date;

	@Column
	private Date deleted_date;

	public CorruptedVotes() {

	}

	public CorruptedVotes(Integer id, District district, BigDecimal votes, Boolean typeMulti, Date entered_date,
			Date published_date, Date deleted_date) {
		super();
		this.id = id;
		this.district = district;
		this.votes = votes;
		this.typeMulti = typeMulti;
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

	public Boolean getTypeMulti() {
		return typeMulti;
	}

	public void setTypeMulti(Boolean typeMulti) {
		this.typeMulti = typeMulti;
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
