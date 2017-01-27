package vs.admin.features.admin.representative;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api; //swagger
import io.swagger.annotations.ApiOperation; //swagger

@RestController
@Api // swagger
@CrossOrigin //what is this???
public class RepresentativeController {

	@Autowired
	private RepresentativeRepository representativeRepository;

	@RequestMapping(value = "/api/representative", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get all representatives")
	public List<Representative> findAllRepresentatives() {
		return representativeRepository.findAllRepresentatives();
	}

	@RequestMapping(value = "/api/representative", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "Create or update representative")
	public Representative createOrUpdateRepresentative(@RequestBody Representative representative) {
		return representativeRepository.saveOrUpdateRepresentative(representative);
	}

	@RequestMapping(value = "/api/representative/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get representative by id")
	public Representative getRepresentativeById(@PathVariable("id") Integer id) {
		return representativeRepository.findRepresentativeById(id);
	}

	@RequestMapping(value = "/api/representative/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete representative by id(realDelete)")
	public void deleteRepresentativeById(@PathVariable("id") Integer id) {
		representativeRepository.deleteRepresentative(id);
	}
}
