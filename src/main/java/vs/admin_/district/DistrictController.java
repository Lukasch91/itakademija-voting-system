package vs.admin_.district;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class DistrictController {

	@Autowired
	private DistrictRepository districtRepository;

	@RequestMapping(value = "/api/ADMIN/district", method = RequestMethod.GET)
	@ApiOperation(value = "[ADMIN] - ")
	public List<District> findAllDistricts() {
		return districtRepository.findAllDistricts();
	}

	@RequestMapping(value = "/api/ADMIN/district", method = RequestMethod.POST)
	@ApiOperation(value = "[ADMIN] - ")
	public District createOrUpdateDistrict(@Valid @RequestBody District district) {
		return districtRepository.saveOrUpdate(district);
	}
	
	@RequestMapping(value = "/api/ADMIN/district/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "[ADMIN] - ")
	public void deteleConstituencyById(@PathVariable("id") Integer id) {
		districtRepository.deleteDistrict(id);
	}	
	
	@RequestMapping(value = "/api/REPRES/district/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[REPRES] - ")
	public District findAllDistricts(@PathVariable("id") Integer id) {
		return districtRepository.findDistrictById(id);
	}
	
	@RequestMapping(value = "/api/ADMIN/districtbyid/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[UNUSED - ADMIN] - ")
	public List<District> findDistrictsByConstituencyId(@PathVariable("id") Integer id) {
		return districtRepository.findAllDistrictsByConstituencyId(id);
	}
}
