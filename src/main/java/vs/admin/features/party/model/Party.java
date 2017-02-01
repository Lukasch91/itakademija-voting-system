package vs.admin.features.party.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "parties")
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;

	@Column
	//@Valid
	@NotBlank(message = "Title cant be with only spaces or empty")					//check for spaces, null not valid
	//@Size(min=1, max=50, message = "Title length must be between 1 and 50")		//title letter length
	@Pattern(regexp = ".*([a-zA-Z0-9ąčęėįšųūžĄČĘĖĮŠŲŪŽ]{2}$)", message = "Title must be letters or numbers")
	private String title;

	@Column
	@NotBlank
	@Pattern(regexp = ".*([a-zA-Z0-9ąčęėįšųūžĄČĘĖĮŠŲŪŽ]{2}$)", message = "Party abbreviation must be letters or numbers")
	//@Length(min = 2, message = "The field must be at least 2 characters")
	private String party_abbreviation;
	

	@Column
	private Date deletedTime;

	public Party() {

	}

	public Party(Integer id, String title, Date deletedTime) {
		this.id = id;
		this.title = title;
		this.deletedTime = deletedTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDeletedTime(Date deletedTime) {
		this.deletedTime = deletedTime;
	}

	public Date getDeletedTime() {
		return deletedTime;
	}

	public String getParty_abbreviation() {
		return party_abbreviation;
	}

	public void setParty_abbreviation(String party_abbreviation) {
		this.party_abbreviation = party_abbreviation;
	}

}
