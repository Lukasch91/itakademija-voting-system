package vs.representative_.singleelection;

import java.util.Date;

import vs.admin_.candidate.Candidate;
import vs.admin_.district.District;

public class SingleVotesPackage extends SingleElection {

	private Boolean typeMulti;
	
	
	public SingleVotesPackage() {
	}
	
	public Boolean getTypeMulti() {
		return typeMulti;
	}

	public void setTypeMulti(Boolean typeMulti) {
		this.typeMulti = typeMulti;
	}

	@Override
	public Integer getSingleId() {
		// TODO Auto-generated method stub
		return super.getSingleId();
	}

	@Override
	public void setSingleId(Integer singleId) {
		// TODO Auto-generated method stub
		super.setSingleId(singleId);
	}

	@Override
	public String getSingleVotes() {
		// TODO Auto-generated method stub
		return super.getSingleVotes();
	}

	@Override
	public void setSingleVotes(String singleVotes) {
		// TODO Auto-generated method stub
		super.setSingleVotes(singleVotes);
	}

	@Override
	public Date getSingleEnteredDate() {
		// TODO Auto-generated method stub
		return super.getSingleEnteredDate();
	}

	@Override
	public void setSingleEnteredDate(Date singleEnteredDate) {
		// TODO Auto-generated method stub
		super.setSingleEnteredDate(singleEnteredDate);
	}

	@Override
	public Date getSinglePublishedDate() {
		// TODO Auto-generated method stub
		return super.getSinglePublishedDate();
	}

	@Override
	public void setSinglePublishedDate(Date singlePublishedDate) {
		// TODO Auto-generated method stub
		super.setSinglePublishedDate(singlePublishedDate);
	}

	@Override
	public Date getSingleDeletedDate() {
		// TODO Auto-generated method stub
		return super.getSingleDeletedDate();
	}

	@Override
	public void setSingleDeletedDate(Date singleDeletedDate) {
		// TODO Auto-generated method stub
		super.setSingleDeletedDate(singleDeletedDate);
	}

	@Override
	public Candidate getSingleCandidate() {
		// TODO Auto-generated method stub
		return super.getSingleCandidate();
	}

	@Override
	public void setSingleCandidate(Candidate singleCandidate) {
		// TODO Auto-generated method stub
		super.setSingleCandidate(singleCandidate);
	}

	@Override
	public District getSingleDistrict() {
		// TODO Auto-generated method stub
		return super.getSingleDistrict();
	}

	@Override
	public void setSingleDistrict(District singleDistrict) {
		// TODO Auto-generated method stub
		super.setSingleDistrict(singleDistrict);
	}


}
