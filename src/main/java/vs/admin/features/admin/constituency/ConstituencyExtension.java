package vs.admin.features.admin.constituency;

import java.util.Date;
import java.util.List;
import vs.admin.features.admin.district.District;

public class ConstituencyExtension extends Constituency {

	private Integer numberOfCandidatesInConstituency;

	public ConstituencyExtension() {
	}

	public Integer getNumberOfCandidatesInConstituency() {
		return numberOfCandidatesInConstituency;
	}

	public void setNumberOfCandidatesInConstituency(Integer numberOfCandidatesInConstituency) {
		this.numberOfCandidatesInConstituency = numberOfCandidatesInConstituency;
	}

	@Override
	public Date getDeletedTime() {
		// TODO Auto-generated method stub
		return super.getDeletedTime();
	}

	@Override
	public void setDeletedTime(Date deletedTime) {
		// TODO Auto-generated method stub
		super.setDeletedTime(deletedTime);
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
	public String getTitle() {
		// TODO Auto-generated method stub
		return super.getTitle();
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		super.setTitle(title);
	}

	@Override
	public List<District> getDistricts() {
		// TODO Auto-generated method stub
		return super.getDistricts();
	}

	@Override
	public void setDistricts(List<District> districts) {
		// TODO Auto-generated method stub
		super.setDistricts(districts);
	}

}