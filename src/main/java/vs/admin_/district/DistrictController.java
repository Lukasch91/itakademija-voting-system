package vs.admin_.district;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import vs.admin_.constituency.ConstituencyController;

@CrossOrigin
@RestController
public class DistrictController {

	private static final Logger log = Logger.getLogger(DistrictController.class.getName());
	
	@Autowired
	private DistrictRepository districtRepository;

	@RequestMapping(value = "/api/ADMIN/district", method = RequestMethod.GET)
	@ApiOperation(value = "[ADMIN] - ")
	public List<District> findAllDistricts() {
		log.debug("was used");
		return districtRepository.findAllDistricts();
	}

	@RequestMapping(value = "/api/ADMIN/district", method = RequestMethod.POST)
	@ApiOperation(value = "[ADMIN] - ")
	public District createOrUpdateDistrict(@Valid @RequestBody District district) {
		log.debug("was used. district: " + district.getTitle());
		return districtRepository.saveOrUpdate(district);
	}
	
	@RequestMapping(value = "/api/ADMIN/district/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "[ADMIN] - ")
	public void deteleConstituencyById(@PathVariable("id") Integer id) {
		log.debug("was used. id: " + id);
		districtRepository.deleteDistrict(id);
	}	
	
	@RequestMapping(value = "/api/REPRES/district/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[REPRES] - ")
	public District findAllDistricts(@PathVariable("id") Integer id) {
		log.debug("was used " + id);
		return districtRepository.findDistrictById(id);
	}
	
	@RequestMapping(value = "/api/ADMIN/districtbyid/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[UNUSED - ADMIN] - ")
	public List<District> findDistrictsByConstituencyId(@PathVariable("id") Integer id) {
		log.debug(" was used. id: " + id);
		return districtRepository.findAllDistrictsByConstituencyId(id);
	}
}
