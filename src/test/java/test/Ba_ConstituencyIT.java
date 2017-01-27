package test;

import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.junit.Assert;
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
import vs.admin.features.admin.constituency.Constituency;
import vs.admin.features.admin.constituency.ConstituencyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Ba_ConstituencyIT.Config.class,
		Application.class })
public class Ba_ConstituencyIT {

	private static final String URI = "/api/constituency";

	@Autowired
	private TestRestTemplate restTemplate;

	private List<Constituency> findAllConstituenciesTest() {
		// Setup
		ParameterizedTypeReference<List<Constituency>> constituencies = new ParameterizedTypeReference<List<Constituency>>() {
		};
		// Execute
		ResponseEntity<List<Constituency>> response = restTemplate.exchange(URI, HttpMethod.GET, null, constituencies);

		// Verify
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

		return response.getBody();
	}

	private Constituency findConstituencyById(final int id) {
		// Setup
		ParameterizedTypeReference<Constituency> constituency = new ParameterizedTypeReference<Constituency>() {
		};
		// Exercise
		ResponseEntity<Constituency> response = restTemplate.exchange(URI + "/" + id, HttpMethod.GET, null,
				constituency);
		// Verify
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

		return response.getBody();
	}

	@Test
	public void findAllUndeletedConstituencies() {

		List<Constituency> constituencies = findAllConstituenciesTest();

		Assert.assertThat(constituencies.size(), is(8));
	}

	@Test
	public void findConstituency() {

		Constituency foundById = findConstituencyById(3);

		Assert.assertThat(foundById.getTitle(), is("Antakalnio"));
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
