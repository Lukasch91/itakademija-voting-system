package test;

import static org.hamcrest.CoreMatchers.is;

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
import vs.admin.features.candidate.model.Candidate;
import vs.admin.features.candidate.model.CandidateRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {Be_CandidateIT.Config.class,
		Application.class })
public class Be_CandidateIT {

	private static final String URI = "/api/candidate";

	@Autowired
	private TestRestTemplate restTemplate;

	private List<Candidate> findAllCandidatesTest() {
		ParameterizedTypeReference<List<Candidate>> candidates = new ParameterizedTypeReference<List<Candidate>>() {
		};
		ResponseEntity<List<Candidate>> response = restTemplate.exchange(URI, HttpMethod.GET, null, candidates);
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

		return response.getBody();
	}

	private Candidate deleteCandidateByIdTest(final int id) {
		ParameterizedTypeReference<Candidate> candidate = new ParameterizedTypeReference<Candidate>() {
		};
		ResponseEntity<Candidate> response = restTemplate.exchange(URI + "/" + id, HttpMethod.DELETE, null, candidate);
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));

		return response.getBody();
	}

	private Candidate findCandidateByIdTest(final int id) {
		ParameterizedTypeReference<Candidate> candidate = new ParameterizedTypeReference<Candidate>() {
		};
		ResponseEntity<Candidate> response = restTemplate.exchange(URI + "/" + id, HttpMethod.GET, null, candidate);
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

		return response.getBody();
	}

	@Ignore
	@Test
	public void findAllUndeletedCandidates() {
		List<Candidate> candidates = findAllCandidatesTest();
		Assert.assertThat(candidates.size(), is(8));
	}

	
	@Ignore
	@Test
	public void deleteCandidate() {

		//
	}


	@Ignore
	@Test
	public void findCandidate() {

		Candidate foundById = findCandidateByIdTest(1);

		// Assert.assertThat(foundById.getId(), is(null));

	}

	@TestConfiguration
	static class Config {
		@Bean
		@Primary
		public CandidateRepository constRepo() {
			return new CandidateRepository();
		}
	}
}
