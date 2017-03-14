package vs.public_.consolidated.results;

public class MemberOfParliament {

	private String firstname;

	private String surname;

	private String party;

	public MemberOfParliament(String firstname, String surname, String party) {
		super();
		this.firstname = firstname;
		this.surname = surname;
		this.party = party;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

}
