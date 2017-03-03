package vs.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String loginName;
	
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
