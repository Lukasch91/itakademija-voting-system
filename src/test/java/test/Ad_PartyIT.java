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

import net.minidev.json.JSONObject; //string to JSON
import net.minidev.json.parser.JSONParser; //string to JSON
import net.minidev.json.parser.ParseException; //string to JSON
import vs.Application;
import vs.admin_.party.PartyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Ad_PartyIT.Config.class,
		Application.class })
public class Ad_PartyIT {

	private static final String URI = "/api/ADMIN/party";
	JSONParser parser = new JSONParser(0);

	@Autowired
	private TestRestTemplate restTemplate;

	private void createOrUpdatePartyTest(final JSONObject createParty) {
		// Exercise
		ResponseEntity<Void> response = restTemplate.postForEntity(URI, createParty, Void.class);
		// Verify
		Assert.assertThat(response.getStatusCode(), CoreMatchers.is(HttpStatus.OK));
	}
	@Ignore
	@Test
	public void createParties() {
				
		final String party_01 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"LSDP\", "
				+ "\"title\": \"Lietuvos socialdemokratų partija\"}";
		final String party_02 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"TS-LKD\", "
				+ "\"title\": \"Tėvynės sąjunga - Lietuvos krikščionys demokratai\"}";
		final String party_03 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"LLS\", "
				+ "\"title\": \"Lietuvos laisvės sąjunga (liberalai)\"}";
		final String party_04 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"TT\", "
				+ "\"title\": \"Partija Tvarka ir teisingumas\"}";
		final String party_05 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"LVŽS\", "
				+ "\"title\": \"Lietuvos valstiečių ir žaliųjų sąjunga\"}";
		final String party_06 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"LRLS\", "
				+ "\"title\": \"Lietuvos Respublikos liberalų sąjūdis\"}";
		final String party_07 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"DP\", "
				+ "\"title\": \"Darbo partija\"}";
		final String party_08 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"DK\", "
				+ "\"title\": \"„Drąsos kelias“ politinė partija\"}";
		final String party_09 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"LLSUTL\", "
				+ "\"title\": \"Lietuvos liaudies partija\"}";
		final String party_10 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"LLRA\", "
				+ "\"title\": \"Lietuvos lenkų rinkimų akcija-Krikščioniškų šeimų sąjunga\"}";
		final String party_11 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"LŽP\", "
				+ "\"title\": \"Lietuvos žaliųjų partija\"}";
		final String party_12 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"LS\", "
				+ "\"title\": \"Politinė partija „Lietuvos sąrašas“\"}";
		final String party_13 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"LCS\", "
				+ "\"title\": \"Lietuvos centro partija\"}";
		final String party_14 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"TS\", "
				+ "\"title\": \"Tautininkų sąjunga\"}";
		final String party_15 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"JL\", "
				+ "\"title\": \"Partija „Jaunoji Lietuva“\"}";
		final String party_16 = "{\"deletedTime\": null, " + "\"id\": null, " + "\"party_abbreviation\": \"LPP\", "
				+ "\"title\": \"Lietuvos pensininkų partija\"}";
		
		createOrUpdatePartyTest(stringToJson(party_01));
		createOrUpdatePartyTest(stringToJson(party_02));
		createOrUpdatePartyTest(stringToJson(party_03));
		createOrUpdatePartyTest(stringToJson(party_04));
		createOrUpdatePartyTest(stringToJson(party_05));
		createOrUpdatePartyTest(stringToJson(party_06));
		createOrUpdatePartyTest(stringToJson(party_07));
		createOrUpdatePartyTest(stringToJson(party_08));
		createOrUpdatePartyTest(stringToJson(party_09));
		createOrUpdatePartyTest(stringToJson(party_10));
		createOrUpdatePartyTest(stringToJson(party_11));
		createOrUpdatePartyTest(stringToJson(party_12));
		createOrUpdatePartyTest(stringToJson(party_13));
		createOrUpdatePartyTest(stringToJson(party_14));
		createOrUpdatePartyTest(stringToJson(party_15));
		createOrUpdatePartyTest(stringToJson(party_16));
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
		public PartyRepository constRepo() {
			return new PartyRepository();
		}
	}
}
