package vs.public_.download.results;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api
public class DownloadResultsController {

//	@Autowired
//	private CSV SERVICE CSV SERVICE;

	@RequestMapping(value = "/api/PUBLIC/downloadCSV/{request}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[PUBLIC] - Get table data as CSV string")
	public String downloadcsv2(@PathVariable("request") Integer request) {		
		return ("from controller: " +request);
	}

}
