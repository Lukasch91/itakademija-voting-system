package vs.admin_.party;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import io.swagger.annotations.ApiModelProperty;
import vs.utils_.hibernate.validators.party.UniqueParty;

@Entity
@Table(name = "parties")
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;

	@Column
	//@Valid
	@UniqueParty
	@NotBlank(message = "Pavadinimas negali būti tuščias")					//check for spaces, null not valid
	@Size(min=5, max=100, message = "Partijos pavadinimas negali būti trumpesnis nei {min} ar ilgesnis nei {max}")		//title letter length
	@Pattern(regexp = ".*([a-zA-Z0-9ąčęėįšųūžĄČĘĖĮŠŲŪŽ„“][^\\_]+$)", message = "Pavadinime naudojami netinkami simboliai")
	@ApiModelProperty(value = "@NotBlank, @Size(min=5, max=70), @UniqueParty, @Patern")
	private String title;

	@Column
	@NotBlank(message = "Trumpinys negali būti tuščias")
	@Pattern(regexp = ".*([a-zA-Z0-9ąčęėįšųūžĄČĘĖĮŠŲŪŽ„“]{2}$)", message = "Partijos trumpinyje naudojami netinkami simboliai")
	@Size(min=1, max=10, message = "Partijos trumpinio ilgis turi būti mažiausiai : {min} ir daugiausiai: {max} simbolių")
	private String party_abbreviation;
	

	@Column
	private Date deletedTime;

	public Party() {

	}

	public Party(Integer id, String title, Date deletedTime, String party_abbreviation) {
		this.id = id;
		this.title = title;
		this.deletedTime = deletedTime;
		this.party_abbreviation = party_abbreviation;
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
