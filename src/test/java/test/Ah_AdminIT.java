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
import vs.admin.AdminRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
		Ah_AdminIT.Config.class, Application.class })
public class Ah_AdminIT {

	private static final String URI = "/api/ADMIN/createadmin";
	JSONParser parser = new JSONParser(0);

	@Autowired
	private TestRestTemplate restTemplate;

	private void saveOrUpdateAdminTest(final JSONObject createAdmin) {
		// Exercise
		ResponseEntity<Void> response = restTemplate.postForEntity(URI, createAdmin, Void.class);
		// Verify
		Assert.assertThat(response.getStatusCode(), CoreMatchers.is(HttpStatus.CREATED));
	}

	@Test
	public void createAdmin() {

		final String admin = "{\"loginName\": \"admin\", " + "\"password\": \""
						+ "$2a$10$0N.9fxcdGWcjgloMYRhoUuHKHFvEpmsIyXSHW4haeAPLhrtZ9Ab/S"
						+ "\"}";


		saveOrUpdateAdminTest(stringToJson(admin));


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
		public AdminRepository constRepo() {
			return new AdminRepository();
		}
	}
}
