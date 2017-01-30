package vs.admin.features.admin.representative;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "representatives")
public class Representative {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;

	/*https://docs.jboss.org/hibernate/validator/4.2/api/org/hibernate/validator/constraints/package-summary.html  	*/
	
	@Column
	@NotBlank //Hibernate validation
	@Size(max=30) //Hibernate validation
	@ApiModelProperty(value = "@NotBlank; @Size(max=30)")
	private String name;

	@Column
	@ApiModelProperty(value = "-------------------------------")
	private String surname;

	// @UniqueConstraint: http://stackoverflow.com/questions/17092601/how-to-validate-unique-username-in-spring
	@Column
	@ApiModelProperty(value = "-------------------------------")
	private String loginName;

	@Column
	@ApiModelProperty(value = "-------------------------------")
	private String password;

	@Column
	@ApiModelProperty(value = "-------------------------------")
	private String email;

	@Column(name = "district_id")
	@ApiModelProperty(value = "-------------------------------")
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
