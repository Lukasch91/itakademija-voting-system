package test;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;

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
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import vs.Application;
import vs.representative.features.multi.election.MultiElectionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Af_MultiElectionIT.Config.class,
		Application.class })

public class Af_MultiElectionIT {

	private static final String URI = "/api/reg-votes-multi";
	JSONParser parser = new JSONParser(0);

	@Autowired
	private TestRestTemplate restTemplate;

	private void createOrUpdateMultiElectionTest(final JSONObject createMultiElection) {
		
		ResponseEntity<Void> response = restTemplate.postForEntity(URI, createMultiElection, Void.class); // Exercise
		Assert.assertThat(response.getStatusCode(), CoreMatchers.is(HttpStatus.OK)); // Verify
	}
	
	@Test
	public void createElection() {

		final String election_01 = 
				 "{"
							+ "\"id\": null, " 
							+ "\"district\": {\"id\": 1}, " 
							+ "\"party\": {\"id\": 1}, "
							+ "\"votes\": 100"
						+ "}";
		final String election_02 = 
				 "{"
							+ "\"id\": null, " 
							+ "\"district\": {\"id\": 2}, " 
							+ "\"party\": {\"id\": 2}, "
							+ "\"votes\": 200"
						+ "}";
		final String election_03 = 
				 "{"
							+ "\"id\": null, " 
							+ "\"district\": {\"id\": 3}, " 
							+ "\"party\": {\"id\": 3}, "
							+ "\"votes\": 300"
						+ "}";
		final String election_04 = 
				 "{"
							+ "\"id\": null, " 
							+ "\"district\": {\"id\": 4}, " 
							+ "\"party\": {\"id\": 4}, "
							+ "\"votes\": 400"
						+ "}";
		final String election_05 = 
				 "{"
							+ "\"id\": null, " 
							+ "\"district\": {\"id\": 5}, " 
							+ "\"party\": {\"id\": 1}, "
							+ "\"votes\": 500"
						+ "}";
		createOrUpdateMultiElectionTest(stringToJson(election_01));
		createOrUpdateMultiElectionTest(stringToJson(election_02));
		createOrUpdateMultiElectionTest(stringToJson(election_03));
		createOrUpdateMultiElectionTest(stringToJson(election_04));
		createOrUpdateMultiElectionTest(stringToJson(election_05));
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
		public MultiElectionRepository multiRepo() {
			return new MultiElectionRepository();
		}
	}
}