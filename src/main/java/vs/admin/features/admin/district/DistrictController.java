package vs.admin.features.admin.district;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class DistrictController {

	@Autowired
	private DistrictRepository districtRepository;

	@RequestMapping(value = "/api/district", method = RequestMethod.GET)
	public List<District> findAllDistricts() {
		return districtRepository.findAllDistricts();
	}

	@RequestMapping(value = "/api/district", method = RequestMethod.POST)
	public District createOrUpdateDistrict(@RequestBody District district) {
		return districtRepository.saveOrUpdate(district);
	}

	@RequestMapping(value = "/api/district/{id}", method = RequestMethod.PUT)
	public void deteleConstituencyById(@PathVariable("id") Integer id) {
		districtRepository.deleteDistrict(id);
	}

	@RequestMapping(value = "/api/district/{id}", method = RequestMethod.GET)
	public District findAllDistricts(@PathVariable("id") Integer id) {
		return districtRepository.findDistrictById(id);
	}
}
