package vs.public_.download.results;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import vs.admin_.representative.RepresentativeRepository;

@RestController
@Api
public class DownloadResultsController {
	private static final Logger log = Logger.getLogger(DownloadResultsController.class.getName());

	@Autowired
	DownloadResultsService downloadResultsService;

	@RequestMapping(value = "/api/PUBLIC/downloadCSV/{request}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[PUBLIC] - Get table data as CSV string")
	public String downloadcsv(@PathVariable("request") Integer request) {
		log.debug("||--> was used. Request: " + request);
		return downloadResultsService.returnSelector(request);
	}
}
