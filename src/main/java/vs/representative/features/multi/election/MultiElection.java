package vs.representative.features.multi.election;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vs.admin.features.admin.district.District;
import vs.admin.features.party.model.Party;

@Entity
@Table(name="multi_member_votes")
public class MultiElection {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	
	@Column
	private Integer votes;
	
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
	
	public MultiElection () {
		
	}

	public MultiElection(Integer id, Integer votes, Date entered_date, Date published_date, Date deleted_date,
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

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
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
