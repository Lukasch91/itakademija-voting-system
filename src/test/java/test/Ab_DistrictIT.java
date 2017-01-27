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
import vs.admin.features.admin.district.DistrictRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Ab_DistrictIT.Config.class,
		Application.class })
public class Ab_DistrictIT {

	private static final String URI = "/api/district"; // keisti++
	JSONParser parser = new JSONParser(0);

	@Autowired
	private TestRestTemplate restTemplate;

	private void createOrUpdateDistrictTest(final JSONObject createDistrict) { // keisti++
		// Exercise
		ResponseEntity<Void> response = restTemplate.postForEntity(URI, createDistrict, Void.class); // keisti++
		// Verify
		Assert.assertThat(response.getStatusCode(), CoreMatchers.is(HttpStatus.OK)); // keisti++
	}

	@Test
	public void createConstituencies() {

		final String district_01 = "{\"address\": \"J. Lelevelio g. 6, Vilnius\", " + "\"constituencyId\": 1, "
				+ "\"deletedTime\": null, " + "\"id\": null, " + "\"numberOfVoters\": 81, "
				+ "\"title\": \"Gedimino\"}";
		final String district_02 = "{\"address\": \"Studentų g. 39, Vilnius\", " + "\"constituencyId\": 1, "
				+ "\"deletedTime\": null, " + "\"id\": null, " + "\"numberOfVoters\": 288, "
				+ "\"title\": \"Saltoniškių\"}";
		final String district_03 = "{\"address\": \"Studentų g. 39, Vilnius\", " + "\"constituencyId\": 1, "
				+ "\"deletedTime\": null, " + "\"id\": null, " + "\"numberOfVoters\": 11, " + "\"title\": \"Sėlių\"}";
		final String district_04 = "{\"address\": \"Žalioji g. 4, Vilnius\", " + "\"constituencyId\": 2, "
				+ "\"deletedTime\": null, " + "\"id\": null, " + "\"numberOfVoters\": 506, "
				+ "\"title\": \"Vytauto\"}";
		final String district_05 = "{\"address\": \"Blindžių g. 3, Vilnius\", " + "\"constituencyId\": 2, "
				+ "\"deletedTime\": null, " + "\"id\": null, " + "\"numberOfVoters\": 260, "
				+ "\"title\": \"Birutės\"}";
		final String district_06 = "{\"address\": \"J. Jasinskio g. 11, Vilnius\", " + "\"constituencyId\": 3, "
				+ "\"deletedTime\": null, " + "\"id\": null, " + "\"numberOfVoters\": 65, " + "\"title\": \"Vytauto\"}";
		final String district_07 = "{\"address\": \"K. Kalinausko g. 21, Vilnius\", " + "\"constituencyId\": 3, "
				+ "\"deletedTime\": null, " + "\"id\": null, " + "\"numberOfVoters\": 54, "
				+ "\"title\": \"Taurakalnio\"}";
		final String district_08 = "{\"address\": \"S. Konarskio g. 27, Vilnius\", " + "\"constituencyId\": 4, "
				+ "\"deletedTime\": null, " + "\"id\": null, " + "\"numberOfVoters\": 115, "
				+ "\"title\": \"S. Konarskio\"}";
		final String district_09 = "{\"address\": \"Mindaugo g. 9, Vilnius\", " + "\"constituencyId\": 5, "
				+ "\"deletedTime\": null, " + "\"id\": null, " + "\"numberOfVoters\": 362, "
				+ "\"title\": \"Mindaugo\"}";
		final String district_10 = "{\"address\": \"T. Ševčenkos g. 31, Vilnius\", " + "\"constituencyId\": 6, "
				+ "\"deletedTime\": null, " + "\"id\": null, " + "\"numberOfVoters\": 309, "
				+ "\"title\": \"Vytenio\"}";
		final String district_11 = "{\"address\": \"Statybininkų g. 5, Vilnius\", " + "\"constituencyId\": 7, "
				+ "\"deletedTime\": null, " + "\"id\": null, " + "\"numberOfVoters\": 1189, "
				+ "\"title\": \"Žemaitės\"}";
		final String district_12 = "{\"address\": \"Kauno g. 3, Vilnius\", " + "\"constituencyId\": 8, "
				+ "\"deletedTime\": null, " + "\"id\": null, " + "\"numberOfVoters\": 89, " + "\"title\": \"Algirdo\"}";

		createOrUpdateDistrictTest(stringToJson(district_01));
		createOrUpdateDistrictTest(stringToJson(district_02));
		createOrUpdateDistrictTest(stringToJson(district_03));
		createOrUpdateDistrictTest(stringToJson(district_04));
		createOrUpdateDistrictTest(stringToJson(district_05));
		createOrUpdateDistrictTest(stringToJson(district_06));
		createOrUpdateDistrictTest(stringToJson(district_07));
		createOrUpdateDistrictTest(stringToJson(district_08));
		createOrUpdateDistrictTest(stringToJson(district_09));
		createOrUpdateDistrictTest(stringToJson(district_10));
		createOrUpdateDistrictTest(stringToJson(district_11));
		createOrUpdateDistrictTest(stringToJson(district_12));

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
		public DistrictRepository constRepo() {
			return new DistrictRepository();
		}
	}
}
