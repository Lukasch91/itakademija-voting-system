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
import vs.admin_.representative.RepresentativeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
		Ac_RepresentativeIT.Config.class, Application.class })
public class Ac_RepresentativeIT {

	private static final String URI = "/api/ADMIN/representative";
	JSONParser parser = new JSONParser(0);

	@Autowired
	private TestRestTemplate restTemplate;

	private void createOrUpdateRepresentativeTest(final JSONObject createRepresentative) {
		// Exercise
		ResponseEntity<Void> response = restTemplate.postForEntity(URI, createRepresentative, Void.class);
		// Verify
		Assert.assertThat(response.getStatusCode(), CoreMatchers.is(HttpStatus.CREATED));
	}
	@Ignore
	@Test
	public void createRepresentatives() {

		final String representative_01 = "{\"districtId\": 1, " + " \"email\": \"Zenonas@gmail.com\", "
				+ "\"id\": null, " + "\"loginName\": \"ZV\", " + "\"name\": \"Zenonas\", " + "\"password\": \""
						+ "$2a$10$..5tXNokn2XPFtHXuz49zuwEeYEiNvxE8aMyo092Ac3TQdVX4LhdO"
						+ "\", "
				+ "\"surname\": \"VAIGAUSKAS\"}";
		final String representative_02 = "{\"districtId\": 2, " + " \"email\": \"Antanas@gmail.com\", "
				+ "\"id\": null, " + "\"loginName\": \"AM\", " + "\"name\": \"Antanas\", " + "\"password\": \""
						+ "$2a$10$F1S8ih8UPPnGjScpd.TGIOMfWnM7vX/mSaO/mtTn41xa3oXs9EBBa"
						+ "\", "
				+ "\"surname\": \"MARCIJONAS\"}";
		final String representative_03 = "{\"districtId\": 3, " + " \"email\": \"Viktoras@gmail.com\", "
				+ "\"id\": null, " + "\"loginName\": \"VR\", " + "\"name\": \"Viktoras\", " + "\"password\": \""
						+ "$2a$10$Dn5sR0UMFspmA9RsHldeAO9vBn3gaowWi70adz.T/hsyBnTjXopI."
						+ "\", "
				+ "\"surname\": \"RINKEVIČIUS\"}";
		final String representative_04 = "{\"districtId\": 4, " + " \"email\": \"Vaidotas@gmail.com\", "
				+ "\"id\": null, " + "\"loginName\": \"VB\", " + "\"name\": \"Vaidotas\", " + "\"password\": \""
						+ "$2a$10$b6iNLyouR6FDC2CMVge1aOwsFVxNiACF7MeQV22xcEvDnwaA/Ljk."
						+ "\", "
				+ "\"surname\": \"BACEVIČIUS\"}";
		final String representative_05 = "{\"districtId\": 5, " + " \"email\": \"Ona@gmail.com\", " + "\"id\": null, "
				+ "\"loginName\": \"OB\", " + "\"name\": \"Ona\", " + "\"password\": \""
						+ "$2a$10$XZrDAZSecstHENx31cRqreEM9uEiFxdFYfw2.e4C.QDxCkpg1YcCm"
						+ "\", "
				+ "\"surname\": \"BUIŠIENĖ\"}";
		final String representative_06 = "{\"districtId\": 6, " + " \"email\": \"Antanas2@gmail.com\", "
				+ "\"id\": null, " + "\"loginName\": \"AB\", " + "\"name\": \"Antanas\", " + "\"password\": \""
						+ "$2a$10$gWyc4rtEhZrksoVuJu.nLuzPCzIHY.N8JB5ha/TzOB.bFpMWWR0MK"
						+ "\", "
				+ "\"surname\": \"BUKAUSKAS\"}";
		final String representative_07 = "{\"districtId\": 7, " + " \"email\": \"Reda@gmail.com\", " + "\"id\": null, "
				+ "\"loginName\": \"RD\", " + "\"name\": \"Reda\", " + "\"password\": \""
						+ "$2a$10$guKVMi45DRdpUWeK6r6ZsuLISy17cLeBqd7a03C0ui8q/EGkbMtBu"
						+ "\", "
				+ "\"surname\": \"DANIŠKEVIČIŪTĖ\"}";
		final String representative_08 = "{\"districtId\": 8, " + " \"email\": \"Jurate@gmail.com\", "
				+ "\"id\": null, " + "\"loginName\": \"JD\", " + "\"name\": \"Jūratė\", " + "\"password\": \""
						+ "$2a$10$fyt6QAs5TTsPvzP0XLEXNOzBM1IpyIdwcquzCLtQykgdc4WSa/lbK"
						+ "\", "
				+ "\"surname\": \"DRUNGILAITĖ\"}";
		final String representative_09 = "{\"districtId\": 9, " + " \"email\": \"Adolfas@gmail.com\", "
				+ "\"id\": null, " + "\"loginName\": \"AG\", " + "\"name\": \"Adolfas\", " + "\"password\": \""
						+ "$2a$10$ZjX./XHMdKq8tB7LGJclkOLUc5X9jwzExkIGyXBuLhzJXoE6myoja"
						+ "\", "
				+ "\"surname\": \"GYLYS\"}";
		final String representative_10 = "{\"districtId\": 10, " + " \"email\": \"Julius@gmail.com\", "
				+ "\"id\": null, " + "\"loginName\": \"JJ\", " + "\"name\": \"Julius\", " + "\"password\": \""
						+ "$2a$10$NFJMgIYhhS5VVQ.Mn8yM0upB8OzrVziioWTdEegJPV3o3jasEi1Um"
						+ "\", "
				+ "\"surname\": \"JASAITIS\"}";
		final String representative_11 = "{\"districtId\": 11, " + " \"email\": \"Saulius@gmail.com\", "
				+ "\"id\": null, " + "\"loginName\": \"SK\", " + "\"name\": \"Saulius\", " + "\"password\": \""
						+ "$2a$10$eT7JeISXxZYKmN5niJGn2.pGx3PvvGfKHnPcOMlVT6fyeC.JDKa8C"
						+ "\", "
				+ "\"surname\": \"KATUOKA\"}";
		final String representative_12 = "{\"districtId\": 12, " + " \"email\": \"Edmundas@gmail.com\", "
				+ "\"id\": null, " + "\"loginName\": \"ES\", " + "\"name\": \"Edmundas\", " + "\"password\": \""
						+ "$2a$10$Xd5CPq3bh68N8bD6y.XaRuvE2IeqqEUGFlkjQ.cY6J8WoME893DX2"
						+ "\", "
				+ "\"surname\": \"SAKALAUSKAS\"}";

		createOrUpdateRepresentativeTest(stringToJson(representative_01));
		createOrUpdateRepresentativeTest(stringToJson(representative_02));
		createOrUpdateRepresentativeTest(stringToJson(representative_03));
		createOrUpdateRepresentativeTest(stringToJson(representative_04));
		createOrUpdateRepresentativeTest(stringToJson(representative_05));
		createOrUpdateRepresentativeTest(stringToJson(representative_06));
		createOrUpdateRepresentativeTest(stringToJson(representative_07));
		createOrUpdateRepresentativeTest(stringToJson(representative_08));
		createOrUpdateRepresentativeTest(stringToJson(representative_09));
		createOrUpdateRepresentativeTest(stringToJson(representative_10));
		createOrUpdateRepresentativeTest(stringToJson(representative_11));
		createOrUpdateRepresentativeTest(stringToJson(representative_12));

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
		public RepresentativeRepository constRepo() {
			return new RepresentativeRepository();
		}
	}
}
