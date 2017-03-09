package vs.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Table;

import vs.BasicUser;

@Entity
public class Admin implements BasicUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;
	@Column
	private String loginName;
	@Column
	private String password;
	
	public Admin() {
	}

	public Admin(Integer id, String loginName, String password) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.password = password;
	}
	
	public Admin(Admin admin) {
		this.id = admin.id;
		this.loginName = admin.loginName;
		this.password = admin.password;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
}
