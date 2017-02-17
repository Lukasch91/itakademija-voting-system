package vs.admin.features.party.model;

import java.util.Date;

public class PartyExtension extends Party {

	private Integer numberOfCandidatesInParty;

	public PartyExtension() {
	}

	public Integer getNumberOfCandidatesInParty() {
		return numberOfCandidatesInParty;
	}

	public void setNumberOfCandidatesInParty(Integer numberOfCandidatesInParty) {
		this.numberOfCandidatesInParty = numberOfCandidatesInParty;
	}

	@Override
	public Integer getId() {
		return super.getId();
	}

	@Override
	public void setId(Integer id) {
		super.setId(id);
	}

	@Override
	public String getTitle() {
		return super.getTitle();
	}

	@Override
	public void setTitle(String title) {
		super.setTitle(title);
	}

	@Override
	public void setDeletedTime(Date deletedTime) {
		super.setDeletedTime(deletedTime);
	}

	@Override
	public Date getDeletedTime() {
		return super.getDeletedTime();
	}

	@Override
	public String getParty_abbreviation() {
		return super.getParty_abbreviation();
	}

	@Override
	public void setParty_abbreviation(String party_abbreviation) {
		super.setParty_abbreviation(party_abbreviation);
	}
}