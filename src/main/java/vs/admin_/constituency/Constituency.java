package vs.admin_.constituency;

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
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import vs.admin_.district.District;
import vs.utils_.hibernate.validators.constituency.UniqueConstituency;

@Entity
@Table(name = "constituency")
public class Constituency {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;

	@Column
	@UniqueConstituency
	//@NotBlank(message="Pavadinimas negali būti tuščias")
	@Length(min=2, max=70, message="Pavadinimą turi būti sudaryti {min} - {max} simbolių")
	@Pattern(regexp = ".*(^$|[a-zA-Z0-9ąčęėįšųūžĄČĘĖĮŠŲŪŽ„“]$)", message = "Pavadinime naudojami netinkami simboliai")
	@ApiModelProperty(value = "@Length(min=2, max=70), @UniqueConstituency, @Pattern")
	private String title;

	@Column
	private Date deletedTime;

	@OneToMany(cascade = CascadeType.ALL)
	@Where(clause = "deleted_time is null")
	@JoinColumn(name = "constituency_id", referencedColumnName = "id")
	private List<District> districts = new ArrayList<>();

	public Date getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Date deletedTime) {
		this.deletedTime = deletedTime;
	}

	public Constituency() {
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

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

}
