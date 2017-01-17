package vs.admin.features.admin.constituency;

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

import vs.admin.features.admin.district.District;

@Entity
@Table(name = "constituency")
public class Constituency {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "constituency_id")
	private Integer id;

	@Column(name = "constituency_title")
	private String title;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "district_id")
	private List<District> districts;

	public Constituency(Integer id, String title) {
		this.id = id;
		this.title = title;

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
