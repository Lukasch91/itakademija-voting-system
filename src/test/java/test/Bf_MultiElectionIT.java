package test;

import static org.hamcrest.CoreMatchers.is;


import java.util.List;
import org.junit.FixMethodOrder;//JUnit4.11+ test method ordering
import org.junit.runners.*;//JUnit4.11+ test method ordering
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
import vs.representative_.multielection.MultiElection;
import vs.representative_.multielection.MultiElectionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Bf_MultiElectionIT.Config.class,
		Application.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // JUnit4.11+ test method ordering
public class Bf_MultiElectionIT {

	private static final String URI = "/api/reg-votes-multi";
	private static final String URI_PUBLISH = "/api/multielectiondistrict";
	@Autowired
	private TestRestTemplate restTemplate;

	private List<MultiElection> findAllElectionTest() {
		ParameterizedTypeReference<List<MultiElection>> multiElection = new ParameterizedTypeReference<List<MultiElection>>() {
		}; // Setup
		ResponseEntity<List<MultiElection>> response = restTemplate.exchange(URI, HttpMethod.GET, null, multiElection); // Execute
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK)); // Verify

		return response.getBody();
	}

	private MultiElection deleteMultiElectionByIdTest(final int id) {
		ParameterizedTypeReference<MultiElection> multiElection = new ParameterizedTypeReference<MultiElection>() {
		}; // Setup
		ResponseEntity<MultiElection> response = restTemplate.exchange(URI + "/" + id, HttpMethod.PUT, null,
				multiElection); // Exercise
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT)); // Verify

		return response.getBody();
	}

	private MultiElection findMultiElectionByIdTest(final int id) {
		ParameterizedTypeReference<MultiElection> multiElection = new ParameterizedTypeReference<MultiElection>() {
		}; // Setup
		ResponseEntity<MultiElection> response = restTemplate.exchange(URI + "/" + id, HttpMethod.GET, null,
				multiElection); // Exercise
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK)); // Verify

		return response.getBody();
	}
	
	private void publishMultiElectionResultsByIdTest(final int id) {
		ResponseEntity<Void> response = restTemplate.exchange(URI_PUBLISH + "/" + id, HttpMethod.POST, null,
				Void.class);
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
	}
	
	private void deleteMultiElectionResultsByIdTest(final int id) {
		ResponseEntity<Void> response = restTemplate.exchange(URI_PUBLISH + "/" + id, HttpMethod.DELETE, null,
				Void.class);
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
	}
/*
	@Test
	public void testPriority() {
		findAllUndeletedElection(5);
		findElectionById(3);
		deleteElection(2);
		findAllUndeletedElection(4);
	}
*/
	@Test
	public void t01_findAllUndeletedElection() {

		List<MultiElection> multiElection = findAllElectionTest();
		int expected = 5;
		Assert.assertThat(expected, is(multiElection.size()));
	}

	@Test
	public void t02_findElectionById() {
		MultiElection foundById = findMultiElectionByIdTest(3);
		Assert.assertThat(foundById.getVotes(), is(300));
	}

	@Test
	public void t03_deleteElection() {
		MultiElection deletedById = deleteMultiElectionByIdTest(2); 	// Delete election if ok , PASS															
		Assert.assertNull(deletedById); 								// Check is election having Delete Date if ok , PASS
	}
	
	@Test
	public void t04_publishMultiElectionResultsById() {
		publishMultiElectionResultsByIdTest(1);
	}
	
	@Test
	public void t05_deleteMultiElectionResultsById() {
		deleteMultiElectionResultsByIdTest(5);
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