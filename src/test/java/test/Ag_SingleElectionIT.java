package test;



import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import net.minidev.json.JSONArray; //string to JSON
import net.minidev.json.JSONObject; //string to JSON
import net.minidev.json.parser.JSONParser; //string to JSON
import net.minidev.json.parser.ParseException; //string to JSON
import vs.Application;
import vs.representative_.singleelection.SingleElectionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
		Ag_SingleElectionIT.Config.class, Application.class })
public class Ag_SingleElectionIT {

	private static final String URI = "/api/REPRES/singleelection";
	JSONParser parser = new JSONParser(0);

	@Autowired
	private TestRestTemplate restTemplate;

	private void createSingleElectionResultsTest(final JSONArray createSingleElectionResult) {
		ResponseEntity<Void> response = restTemplate.postForEntity(URI, createSingleElectionResult, Void.class);
		Assert.assertThat(response.getStatusCode(), CoreMatchers.is(HttpStatus.CREATED));
	}
	@Ignore
	@Test
	public void createSingleElectionResults() {

		final String singleElectionResult_01 = "{\"singleId\": null, " + "\"singleCandidate\": {\"candidateID\": 1}, \"singleDistrict\": { \"id\": 1}, " + "\"singleVotes\": 1000}";
		final String singleElectionResult_02 = "{\"singleId\": null, " + "\"singleCandidate\": {\"candidateID\": 1}, \"singleDistrict\": { \"id\": 2}, " + "\"singleVotes\": 100}";	
		final String singleElectionResult_03 = "{\"singleId\": null, " + "\"singleCandidate\": {\"candidateID\": 2}, \"singleDistrict\": { \"id\": 2}, " + "\"singleVotes\": 100}";
		final String singleElectionResult_04 = "{\"singleId\": null, " + "\"singleCandidate\": {\"candidateID\": 6}, \"singleDistrict\": { \"id\": 7}, " + "\"singleVotes\": 600}";
		final String singleElectionResult_05 = "{\"singleId\": null, " + "\"singleCandidate\": {\"candidateID\": 7}, \"singleDistrict\": { \"id\": 7}, " + "\"singleVotes\": 700}";
		final String singleElectionResult_06 = "{\"singleId\": null, " + "\"singleCandidate\": {\"candidateID\": 8}, \"singleDistrict\": { \"id\": 7}, " + "\"singleVotes\": 800}";
		
	
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(stringToJson(singleElectionResult_01));
		jsonArray.add(stringToJson(singleElectionResult_02));
		jsonArray.add(stringToJson(singleElectionResult_03));
		jsonArray.add(stringToJson(singleElectionResult_04));
		jsonArray.add(stringToJson(singleElectionResult_05));
		jsonArray.add(stringToJson(singleElectionResult_06));
		
		
		createSingleElectionResultsTest(jsonArray);		
	}
	
	
	private JSONObject stringToJson(final String jstring) {
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(jstring);
		} catch (ParseException e) {
			System.out.println("---------------------------------------------");
			e.printStackTrace();
		}
		return json;
	}

	@TestConfiguration
	static class Config {
		@Bean
		@Primary
		public SingleElectionRepository constRepo() {
			return new SingleElectionRepository();
		}
	}
}
