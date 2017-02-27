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
import vs.admin_.party.Party;
import vs.admin_.party.PartyRepository;

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
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT)); // Verify

		return response.getBody();
	}

	private Party findPartyByIdTest(final int id) {
		ParameterizedTypeReference<Party> party = new ParameterizedTypeReference<Party>() {
		}; // Setup
		ResponseEntity<Party> response = restTemplate.exchange(URI + "/" + id, HttpMethod.GET, null, party); // Exercise
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK)); // Verify

		return response.getBody();
	}
	
	/*@Test
	public void testPriority() {
		//findAllUndeletedParties(10);
		findParty(2);
		deleteParty(1);
		findAllUndeletedParties(9);
	}*/

	@Test
	public void t1_findAllUndeletedParties() {
		List<Party> parties = findAllPartiesTest();
		Assert.assertThat(parties.size(), is(10));
	}

	
	@Test
	public void t2_findParty() {

		Party foundById = findPartyByIdTest(2);								
		Assert.assertThat(foundById.getParty_abbreviation(), is("LHP"));	
	}
	
	@Test
	public void t3_deleteParty() {
		
		Party deleteById = deletePartyByIdTest(1);			//delete party, if ok, PASS
		Assert.assertNull(deleteById); 					//verify delete date, if ok , PASS	
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
