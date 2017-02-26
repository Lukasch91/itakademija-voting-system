package vs.representative.features.multi.election;

import java.util.Date;


import vs.admin.features.admin.district.District;
import vs.admin.features.party.model.Party;


public class MultiVotesPackage extends MultiElection {

	private Boolean typeMulti;
	
	
	public MultiVotesPackage() {
		
	}
	
	public Boolean getTypeMulti() {
		return typeMulti;
	}

	public void setTypeMulti(Boolean typeMulti) {
		this.typeMulti = typeMulti;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	@Override
	public Long getVotes() {
		// TODO Auto-generated method stub
		return super.getVotes();
	}

	@Override
	public void setVotes(Long votes) {
		// TODO Auto-generated method stub
		super.setVotes(votes);
	}

	@Override
	public Date getEntered_date() {
		// TODO Auto-generated method stub
		return super.getEntered_date();
	}

	@Override
	public void setEntered_date(Date entered_date) {
		// TODO Auto-generated method stub
		super.setEntered_date(entered_date);
	}

	@Override
	public Date getPublished_date() {
		// TODO Auto-generated method stub
		return super.getPublished_date();
	}

	@Override
	public void setPublished_date(Date published_date) {
		// TODO Auto-generated method stub
		super.setPublished_date(published_date);
	}

	@Override
	public Date getDeleted_date() {
		// TODO Auto-generated method stub
		return super.getDeleted_date();
	}

	@Override
	public void setDeleted_date(Date deleted_date) {
		// TODO Auto-generated method stub
		super.setDeleted_date(deleted_date);
	}

	@Override
	public Party getParty() {
		// TODO Auto-generated method stub
		return super.getParty();
	}

	@Override
	public void setParty(Party party) {
		// TODO Auto-generated method stub
		super.setParty(party);
	}

	@Override
	public District getDistrict() {
		// TODO Auto-generated method stub
		return super.getDistrict();
	}

	@Override
	public void setDistrict(District district) {
		// TODO Auto-generated method stub
		super.setDistrict(district);
	}		
}
