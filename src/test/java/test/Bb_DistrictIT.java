package test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import java.util.List;

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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import vs.Application;
import vs.admin.features.admin.district.District;
import vs.admin.features.admin.district.DistrictRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Bb_DistrictIT.Config.class,
		Application.class })
public class Bb_DistrictIT {

	private static final String URI = "/api/district";

	@Autowired
	private TestRestTemplate restTemplate;


	private List<District> findAllDistrictsTest() {
		// Setup
		ParameterizedTypeReference<List<District>> districts = new ParameterizedTypeReference<List<District>>() { // keisti++
		};
		// Execute
		ResponseEntity<List<District>> response = restTemplate.exchange(URI, HttpMethod.GET, null, districts); // keisti++

		// Verify
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

		return response.getBody();
	}

	private District deleteDistrictByIdTest(final int id) {
		// Setup
		ParameterizedTypeReference<District> district = new ParameterizedTypeReference<District>() {
		};
		// Exercise
		ResponseEntity<District> response = restTemplate.exchange(URI + "/" + id, HttpMethod.PUT, null, 
				district);
		// Verify
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK)); 

		return response.getBody(); 
	}

	@Ignore
	@Test
	public void findAllUndeletedConstituencies() {

		List<District> districts = findAllDistrictsTest();

		Assert.assertThat(districts.size(), is(2));
	}

	@Ignore
	@Test
	public void deleteDistrict() {

		District deletedById = deleteDistrictByIdTest(2);

		Assert.assertThat(deletedById.getDeletedTime(), is((not(null))));
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
