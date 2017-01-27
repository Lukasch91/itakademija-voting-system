package test;

import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;//JUnit4.11+ test method ordering
import org.junit.runners.*;//JUnit4.11+ test method ordering
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
import vs.representative.features.single.election.SingleElection;
import vs.representative.features.single.election.SingleElectionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
		Bg_SingleElectionIT.Config.class, Application.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // JUnit4.11+ test method ordering
public class Bg_SingleElectionIT {

	private static final String URI = "/api/singleelection";
	private static final String URI_PUBLISH = "/api/singleelectiondistrict";

	@Autowired
	private TestRestTemplate restTemplate;

	private List<SingleElection> findAllUndeletedSingleElectionResultsTest() {
		ParameterizedTypeReference<List<SingleElection>> singleElections = new ParameterizedTypeReference<List<SingleElection>>() {
		};
		ResponseEntity<List<SingleElection>> response = restTemplate.exchange(URI, HttpMethod.GET, null,
				singleElections);
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK));
		return response.getBody();
	}

	private void deleteSingleElectionByIdTest(final int id) {
		ResponseEntity<Void> response = restTemplate.exchange(URI + "/" + id, HttpMethod.DELETE, null, Void.class);
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
	}

	private SingleElection findSingleElectionByIdTest(final int id) {
		ParameterizedTypeReference<SingleElection> singleElection = new ParameterizedTypeReference<SingleElection>() {
		};
		ResponseEntity<SingleElection> response = restTemplate.exchange(URI + "/" + id, HttpMethod.GET, null,
				singleElection);
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK));
		return response.getBody();
	}

	private void publishSingleElectionResultsByIdTest(final int id) {
		ResponseEntity<Void> response = restTemplate.exchange(URI_PUBLISH + "/" + id, HttpMethod.POST, null,
				Void.class);
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
	}
	
	private void deleteSingleElectionResultsByIdTest(final int id) {
		ResponseEntity<Void> response = restTemplate.exchange(URI_PUBLISH + "/" + id, HttpMethod.DELETE, null,
				Void.class);
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void t01_findAllUndeletedSingleElectionResults() {
		List<SingleElection> singleElections = findAllUndeletedSingleElectionResultsTest();
		Assert.assertThat(singleElections.size(), is(6));
	}

	@Test
	public void t02_findSingleElectionById() {
		SingleElection foundById = findSingleElectionByIdTest(1);
		Assert.assertThat(foundById.getSingleVotes(), is(1000));
	}

	@Test
	public void t03_deleteSingleElectionById() {
		deleteSingleElectionByIdTest(1);
	}

	@Test
	public void t04_publishSingleElectionResultsById() {
		publishSingleElectionResultsByIdTest(7);
	}
	
	@Test
	public void t05_deleteSingleElectionResultsById() {
		deleteSingleElectionResultsByIdTest(7);
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
