package vs.admin_.candidate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import vs.admin_.constituency.Constituency;
import vs.admin_.party.Party;

@Entity
@Table(name = "candidates")
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer candidateID;

	@Column
	@NotBlank(message="Vardas negali būti tuščias")
	private String candidateName;

	@Column
	@NotBlank(message="Pavardė negali būti tuščia")
	private String candidateSurname;

	@Column
	@NotBlank(message="Kandidato gimimo data negali būti tuščia")
	private String candidateDateOfBirth; // Date or String???

	@Column
	@NotBlank(message="Asmens kodas negali būti tuščias")
	private String candidatePersonalID;

	@Lob
	@Column(columnDefinition = "TEXT", length = 65535)
	private String candidateDescription;

	/*-----------------------------------------------------------------*/
	@ManyToOne
	@JoinColumn(name = "candidateParty") // referencedColumnName = "id"
	private Party candidateParty = new Party();
	/*-----------------------------------------------------------------*/

	@Column
	//validate as not null only if candidateParty is not null
	private Integer candidateNumberInParty;

	/*-----------------------------------------------------------------*/
	@ManyToOne
	@JoinColumn(name = "candidateConstituency")
	private Constituency candidateConstituency = new Constituency();
	/*-----------------------------------------------------------------*/

	@Column
	private Date candidateDeletedDate;

	public Candidate() {
	}

	public Candidate(Integer candidateID, String candidateName, String candidateSurname, String candidateDateOfBirth,
			String candidatePersonalID, String candidateDescription, Party candidateParty,
			Integer candidateNumberInParty, Constituency candidateConstituency, Date candidateDeletedDate) {
		super();
		this.candidateID = candidateID;
		this.candidateName = candidateName;
		this.candidateSurname = candidateSurname;
		this.candidateDateOfBirth = candidateDateOfBirth;
		this.candidatePersonalID = candidatePersonalID;
		this.candidateDescription = candidateDescription;
		this.candidateParty = candidateParty;
		this.candidateNumberInParty = candidateNumberInParty;
		this.candidateConstituency = candidateConstituency;
		this.candidateDeletedDate = candidateDeletedDate;
	}

	public Integer getCandidateID() {
		return candidateID;
	}

	public void setCandidateID(Integer candidateID) {
		this.candidateID = candidateID;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidateSurname() {
		return candidateSurname;
	}

	public void setCandidateSurname(String candidateSurname) {
		this.candidateSurname = candidateSurname;
	}

	public String getCandidateDateOfBirth() {
		return candidateDateOfBirth;
	}

	public void setCandidateDateOfBirth(String candidateDateOfBirth) {
		this.candidateDateOfBirth = candidateDateOfBirth;
	}

	public String getCandidatePersonalID() {
		return candidatePersonalID;
	}

	public void setCandidatePersonalID(String candidatePersonalID) {
		this.candidatePersonalID = candidatePersonalID;
	}

	public String getCandidateDescription() {
		return candidateDescription;
	}

	public void setCandidateDescription(String candidateDescription) {
		this.candidateDescription = candidateDescription;
	}

	public Party getCandidateParty() {
		return candidateParty;
	}

	public void setCandidateParty(Party candidateParty) {
		this.candidateParty = candidateParty;
	}

	public Integer getCandidateNumberInParty() {
		return candidateNumberInParty;
	}

	public void setCandidateNumberInParty(Integer candidateNumberInParty) {
		this.candidateNumberInParty = candidateNumberInParty;
	}

	public Constituency getCandidateConstituency() {
		return candidateConstituency;
	}

	public void setCandidateConstituency(Constituency candidateConstituency) {
		this.candidateConstituency = candidateConstituency;
	}

	public Date getCandidateDeletedDate() {
		return candidateDeletedDate;
	}

	public void setCandidateDeletedDate(Date candidateDeletedDate) {
		this.candidateDeletedDate = candidateDeletedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidatePersonalID == null) ? 0 : candidatePersonalID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidate other = (Candidate) obj;
		if (candidatePersonalID == null) {
			if (other.candidatePersonalID != null)
				return false;
		} else if (!candidatePersonalID.equals(other.candidatePersonalID))
			return false;
		return true;
	}
}
