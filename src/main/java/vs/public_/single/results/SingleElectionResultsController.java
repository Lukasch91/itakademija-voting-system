package vs.public_.single.results;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import vs.admin_.district.DistrictRepository;
import vs.public_.multi.results.ElectionDistrictDetails;
import vs.public_.multi.results.MultiElectionResultsComperator;

@RestController
@CrossOrigin
public class SingleElectionResultsController {

	private static final Logger log = Logger.getLogger(SingleElectionResultsController.class.getName());
	
	@Autowired
	SingleElectionResultsService singleElectionConstitencyService;

	@Autowired
	DistrictRepository districtRepository;

	@RequestMapping(value = "/api/PUBLIC/constresults", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<SingleElectionConstituency> getConstituencyResults() {
		log.info("||--> was used." );
		return singleElectionConstitencyService.singleElectionConstituencyResults();
	}

	@RequestMapping(value = "/api/PUBLIC/districtresults/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<SingleElectionDistrict> getDistrictResults(@PathVariable Integer id) {
		log.info("||--> was used." );
		return singleElectionConstitencyService.singleElectionDistrictResults(id);
	}

	@RequestMapping(value = "/api/PUBLIC/candidatesresults/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<SingleElectionResult> getSingleCandidatesResults(@PathVariable Integer id) {
		log.info("||--> was used." );
		return singleElectionConstitencyService.getSingleElectionResults(id);
	}

	@RequestMapping(value = "/api/PUBLIC/constresults/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public SingleElectionConstituency getConstiuencyResultsDetails(@PathVariable Integer id) {
		log.info("||--> was used." );
		return singleElectionConstitencyService.getConstiteuncyResult(id);
	}

	@RequestMapping(value = "/api/PUBLIC/constresultsdis/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public SingleElectionConstituency getConstiuencyResultsDetailsByDistrictId(@PathVariable Integer id) {
		log.info("||--> was used." );
		return singleElectionConstitencyService
				.getConstiteuncyResult(districtRepository.getConstituencyIdByDistrictId(id));
	}

	@RequestMapping(value = "/api/PUBLIC/districtdetails/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<SingleElectionResult> getDistrictResultsDetails(@PathVariable Integer id) {
		log.info("||--> was used." );
		return singleElectionConstitencyService.getSingleElectionResultInDistrict(id);
	}

	@RequestMapping(value = "/api/PUBLIC/singledetails/", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public ElectionDetails getSinglElectionDetails() {
		log.info("||--> was used." );
		return singleElectionConstitencyService.getSingleElectionDetails();
	}

	@RequestMapping(value = "/api/PUBLIC/singleDistrictDetails/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public ElectionDistrictDetails getSinglElectionDistrictDetails(@PathVariable Integer id) {
		log.info("||--> was used." );
		return singleElectionConstitencyService.getDistrictElectionDetails(id);
	}

}
