package vs.public_.single.results;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import vs.admin_.district.DistrictRepository;

@RestController
@CrossOrigin
public class SingleElectionResultsController {

	@Autowired
	SingleElectionResultsService singleElectionConstitencyService;

	@Autowired
	DistrictRepository districtRepository;

	@RequestMapping(value = "/api/PUBLIC/constresults", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<SingleElectionConstituency> getConstituencyResults() {
		return singleElectionConstitencyService.singleElectionConstituencyResults();
	}

	@RequestMapping(value = "/api/PUBLIC/districtresults/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<SingleElectionDistrict> getDistrictResults(@PathVariable Integer id) {
		return singleElectionConstitencyService.singleElectionDistrictResults(id);
	}

	@RequestMapping(value = "/api/PUBLIC/candidatesresults/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<SingleElectionResult> getSingleCandidatesResults(@PathVariable Integer id) {
		return singleElectionConstitencyService.getSingleElectionResults(id);
	}

	@RequestMapping(value = "/api/PUBLIC/constresults/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public SingleElectionConstituency getConstiuencyResultsDetails(@PathVariable Integer id) {
		return singleElectionConstitencyService.getConstiteuncyResult(id);
	}

	@RequestMapping(value = "/api/PUBLIC/constresultsdis/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public SingleElectionConstituency getConstiuencyResultsDetailsByDistrictId(@PathVariable Integer id) {
		return singleElectionConstitencyService
				.getConstiteuncyResult(districtRepository.getConstituencyIdByDistrictId(id));
	}

	@RequestMapping(value = "/api/PUBLIC/districtdetails/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<SingleElectionResult> getDistrictResultsDetails(@PathVariable Integer id) {
		return singleElectionConstitencyService.getSingleElectionResultInDistrict(id);
	}

	@RequestMapping(value = "/api/PUBLIC/singledetails/", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public SingleElectionDetails getSinglElectionDetails() {
		return singleElectionConstitencyService.getSingleElectionDetails();
	}
	
}
