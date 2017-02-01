package vs.admin.features.admin.representative;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api // swagger
@CrossOrigin
public class RepresentativeController {

	@Autowired
	private RepresentativeRepository representativeRepository;

	@RequestMapping(value = "/api/representative", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get all representatives") // swagger
	public List<Representative> findAllRepresentatives() {
		return representativeRepository.findAllRepresentatives();
	}
/*============================================================*/
	@RequestMapping(value = "/api/representative", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "Create or update representative", notes = "Data: {\"districtId\": 13, "
			+ "\"email\": \"Sablonskis@gmail.com\", \"id\": null, \"loginName\": \"Sab\", "
			+ "\"name\": \"Sablonius\", \"password\": \"xxx\", \"surname\": \"SABLONSKIS\"}") //swaggerino
	
	public Representative createOrUpdateRepresentative(@Valid @RequestBody Representative representative) {
		return representativeRepository.saveOrUpdateRepresentative(representative);		
	}
	/*============================================================*/
	@RequestMapping(value = "/api/representative/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get representative by id") // swagger
	public Representative getRepresentativeById(@PathVariable("id") Integer id) {
		return representativeRepository.findRepresentativeById(id);
	}

	@RequestMapping(value = "/api/representative/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete representative by id(realDelete)") // swagger
	public void deleteRepresentativeById(@PathVariable("id") Integer id) {
		representativeRepository.deleteRepresentative(id);
	}
}
