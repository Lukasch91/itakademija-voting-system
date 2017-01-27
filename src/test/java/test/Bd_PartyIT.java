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
import vs.admin.features.party.model.Party;
import vs.admin.features.party.model.PartyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Bd_PartyIT.Config.class,
		Application.class })
public class Bd_PartyIT {

	private static final String URI = "/api/party";

	@Autowired
	private TestRestTemplate restTemplate;

	private List<Party> findAllPartiesTest() {
		ParameterizedTypeReference<List<Party>> parties = new ParameterizedTypeReference<List<Party>>() {
		}; // Setup
		ResponseEntity<List<Party>> response = restTemplate.exchange(URI, HttpMethod.GET, null, parties); // Execute
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK)); // Verify

		return response.getBody();
	}

	private Party deletePartyByIdTest(final int id) {
		ParameterizedTypeReference<Party> party = new ParameterizedTypeReference<Party>() {
		}; // Setup
		ResponseEntity<Party> response = restTemplate.exchange(URI + "/" + id, HttpMethod.PUT, null, party); // Exercise
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK)); // Verify

		return response.getBody();
	}

	private Party findPartyByIdTest(final int id) {
		ParameterizedTypeReference<Party> party = new ParameterizedTypeReference<Party>() {
		}; // Setup
		ResponseEntity<Party> response = restTemplate.exchange(URI + "/" + id, HttpMethod.GET, null, party); // Exercise
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK)); // Verify

		return response.getBody();
	}
	
	@Test
	public void testPriority() {
		findAllUndeletedParties(10);
		findParty(2);
		deleteParty(1);
		findAllUndeletedParties(9);
	}

	//@Test
	public void findAllUndeletedParties(int size) {
		List<Party> parties = findAllPartiesTest();
		Assert.assertThat(size, is(parties.size()));
	}

	//@Test
	public void deleteParty(int idD) {
		
		Party deleteById = deletePartyByIdTest(idD);			//delete party, if ok, PASS
		Assert.assertNull(deleteById); 					//verify delete date, if ok , PASS	
	}

	//@Test
	public void findParty(int idF) {

		Party foundById = findPartyByIdTest(idF);								
		Assert.assertThat(foundById.getParty_abbreviation(), is("LHP"));	
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
