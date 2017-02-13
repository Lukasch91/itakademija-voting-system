package vs.representative.features.multi.election;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import vs.admin.features.admin.district.District;
import vs.admin.features.party.model.Party;
import vs.utils.hibernate.validators.multiElection.VotesMultiElection;
import vs.utils.hibernate.validators.party.UniqueParty;

@Entity
@Table(name = "multi_member_votes")
public class MultiElection {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;

	@Column
	@VotesMultiElection
	@NotNull(message = "Būtina įvesti balsų skaičių")
	@Min(value = 0, message = "Minimalus balsų skaičius {value}")
	@Max(value = 500000, message = "Maksimalus balsų skaičius {value}")
	private BigDecimal  votes;

	@Column
	private Date entered_date;

	@Column
	private Date published_date;

	@Column
	private Date deleted_date;

	@ManyToOne
	private Party party;

	@ManyToOne
	private District district;

	public MultiElection() {

	}

	public MultiElection(Integer id, BigDecimal  votes, Date entered_date, Date published_date, Date deleted_date,
			Party party, District district) {
		super();
		this.id = id;
		this.votes = votes;
		this.entered_date = entered_date;
		this.published_date = published_date;
		this.deleted_date = deleted_date;
		this.party = party;
		this.district = district;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal  getVotes() {
		return votes;
	}

	public void setVotes(BigDecimal  votes) {
		this.votes = votes;
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

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

}
