package vs.admin_.representative;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import vs.BasicUser;
import vs.utils_.hibernate.validators.representative.UniqueRepresentativeEmail;
import vs.utils_.hibernate.validators.representative.UniqueRepresentativeUsername;

@Entity
@Table(name = "representatives")
public class Representative implements BasicUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@ApiModelProperty(value = "No validation")
	private Integer id;

	@Column
	@NotEmpty(message = "Vardas negali būti tuščias")
	@Size(max = 30, message = "Vardo ilgis negali būti ilgesnis negu {max} simboliu")
	@ApiModelProperty(value = "@NotEmpty, @Size(max=30)")
	private String name;

	@Column
	@NotEmpty(message = "Pavardė negali būti tuščia")
	@Size(max = 20, message = "Pavardės ilgis negali būti ilgesnis negu {max} simbolių")
	@ApiModelProperty(value = "@NotEmpty, @Size(max=20)")
	private String surname;

	@Column
	@NotEmpty(message = "Slapyvardis negali būti tuščias")
	@UniqueRepresentativeUsername // message in the validator class
	@ApiModelProperty(value = "@NotEmpty, @Unique")
	private String loginName;

	@Column
	@NotEmpty(message = "Slaptažodis negali būti tuščias")
	//@Max(value = 60, message = "Slaptažodzio ilgis negali būti ilgesnis negu {value} simbolių")
	//@Min(value = 12, message = "Slaptažodzio ilgis negali būti trumpesnis negu {value} simbolių")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[`~!@#$%^&*=-?.])(?=\\S+$).{12,60}$", message="Slaptažodį turi sudaryti 12-60 simbolių, jame turi būti bent viena mažoji raidė, didžioji raidė, skaičius ir bent vienas nurodytas simbolis `~!@#$%^&*=-?. ")
	@ApiModelProperty(value = "@NotEmpty, @Max(value = 60), @Min(value = 12)")
	private String password;

	@Column
	@NotEmpty(message = "E-paštas negali būti tuščias")
	@Email(message = "Neteisingas E-pašto formatas")
	@Size(max = 50, message = "E-pašto ilgis negali būti ilgesnis negu {max} simbolių")
	@UniqueRepresentativeEmail // message in the validator class
	@ApiModelProperty(value = "@NotEmpty, @Size(max=50), @Email, @Unique")
	private String email;

	@Column(name = "district_id")
	// Uppon attempted validation<javax.validation.UnexpectedTypeException:
	// HV000030: No validator could be found for constraint>
	@ApiModelProperty(value = "No validation")
	private Integer districtId;

	public Representative() {
	}

	public Representative(Integer id, String name, String surname, String loginName, String password, String email,
			Integer districtId) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.loginName = loginName;
		this.password = password;
		this.email = email;
		this.districtId = districtId;
	}
	
	public Representative(Representative representative) {
		this.id = representative.id;
		this.name = representative.name;
		this.surname = representative.surname;
		this.loginName = representative.loginName;
		this.password = representative.password;
		this.email = representative.email;
		this.districtId = representative.districtId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
}
