package vs.admin.features.admin.district;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import vs.admin.features.admin.representative.Representative;

@Entity
@Table(name = "districts")
public class District {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;

	@Column
	@NotBlank(message = "Pavadinimas negali būti tuščias")
	@Size(min = 2, max = 70, message = "Pavadinimo negali sudaryti mažiau nei {min} ar daugiau nei {max} simbolių")
	@Pattern(regexp = ".*([a-zA-Z0-9ąčęėįšųūžĄČĘĖĮŠŲŪŽ„“]$)", message = "Pavadinime naudojami netinkami simboliai")
	private String title;

	@Column
	@Min(value = 0, message = "Per maža reikšmė")
	@Max(value = 500000, message = "Reikšmė ribota iki {max}")
	private Long numberOfVoters;

	@Column
	@NotBlank(message = "Adresas negali būti tuščias")
	@Size(min = 2, max = 70, message = "Adreso negali sudaryti mažiau nei {min} ar daugiau nei {max} simbolių")
	private String address;

	@Column
	private Date deletedTime;

	@Column(name = "constituency_id")
	private Integer constituencyId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "district_Id", referencedColumnName = "id")
	private List<Representative> representatives = new ArrayList<>();

	public District() {

	}

	public Long getNumberOfVoters() {
		return numberOfVoters;
	}

	public void setNumberOfVoters(Long numberOfVoters) {
		this.numberOfVoters = numberOfVoters;
	}

	public Integer getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Integer constituencyId) {
		this.constituencyId = constituencyId;
	}

	public List<Representative> getRepresentatives() {
		return representatives;
	}

	public void setRepresentatives(List<Representative> representatives) {
		this.representatives = representatives;
	}

	public Date getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Date deletedTime) {
		this.deletedTime = deletedTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

}
