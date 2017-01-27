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

import net.minidev.json.JSONObject; //string to JSON
import net.minidev.json.parser.JSONParser; //string to JSON
import net.minidev.json.parser.ParseException; //string to JSON
import vs.Application;
import vs.admin.features.admin.constituency.ConstituencyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Aa_ConstituencyIT.Config.class,
		Application.class })
public class Aa_ConstituencyIT {

	private static final String URI = "/api/constituency";
	JSONParser parser = new JSONParser(0);

	@Autowired
	private TestRestTemplate restTemplate;

	private void createOrUpdateConstituency(final JSONObject createConstituency) {
		// Exercise
		ResponseEntity<Void> response = restTemplate.postForEntity(URI, createConstituency, Void.class);
		// Verify
		Assert.assertThat(response.getStatusCode(), CoreMatchers.is(HttpStatus.OK));
	}

	@Test
	public void createConstituencies() {

		final String constituency_01 = "{\"deletedTime\": null," + " \"id\": null," + " \"title\": \"Naujamiesčio\"}";
		final String constituency_02 = "{\"deletedTime\": null," + " \"id\": null," + " \"title\": \"Senamiesčio\"}";
		final String constituency_03 = "{\"deletedTime\": null," + " \"id\": null," + " \"title\": \"Antakalnio\"}";
		final String constituency_04 = "{\"deletedTime\": null," + " \"id\": null," + " \"title\": \"Žirmūnų\"}";
		final String constituency_05 = "{\"deletedTime\": null," + " \"id\": null," + " \"title\": \"Fabijoniškių\"}";
		final String constituency_06 = "{\"deletedTime\": null," + " \"id\": null," + " \"title\": \"Šeškinės\"}";
		final String constituency_07 = "{\"deletedTime\": null," + " \"id\": null," + " \"title\": \"Justiniškių\"}";
		final String constituency_08 = "{\"deletedTime\": null," + " \"id\": null," + " \"title\": \"Karoliniškių\"}";

		createOrUpdateConstituency(stringToJson(constituency_01));
		createOrUpdateConstituency(stringToJson(constituency_02));
		createOrUpdateConstituency(stringToJson(constituency_03));
		createOrUpdateConstituency(stringToJson(constituency_04));
		createOrUpdateConstituency(stringToJson(constituency_05));
		createOrUpdateConstituency(stringToJson(constituency_06));
		createOrUpdateConstituency(stringToJson(constituency_07));
		createOrUpdateConstituency(stringToJson(constituency_08));
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
		public ConstituencyRepository constRepo() {
			return new ConstituencyRepository();
		}
	}
}
