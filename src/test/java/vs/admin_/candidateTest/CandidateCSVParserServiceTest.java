package vs.admin_.candidateTest;

import org.hamcrest.core.IsNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import vs.admin_.candidate.CandidateCSVParserService;

public class CandidateCSVParserServiceTest {

	private CandidateCSVParserService parser = new CandidateCSVParserService();

	@Test
	public void test_no_quote() {

		String line = "10,AU,Australia";
		List<String[]> result = parser.csvReader(line);

		assertThat(result, IsNull.notNullValue());
		assertThat(result.get(0).length, is(3));
		assertThat(result.get(0)[0], is("10"));
		assertThat(result.get(0)[1], is("AU"));
		assertThat(result.get(0)[2], is("Australia"));
	}

	@Test
	public void test_no_quote_but_double_quotes_in_column() throws Exception {

		String line = "10,AU,Aus\"\"tralia";

		List<String[]> result = parser.csvReader(line);
		assertThat(result, IsNull.notNullValue());
		assertThat(result.get(0).length, is(3));
		assertThat(result.get(0)[0], is("10"));
		assertThat(result.get(0)[1], is("AU"));
		assertThat(result.get(0)[2], is("Aus\"tralia"));
	}

	@Test
	public void test_double_quotes() {

		String line = "\"10\",\"AU\",\"Australia\"";
		List<String[]> result = parser.csvReader(line);

		assertThat(result, IsNull.notNullValue());
		assertThat(result.get(0).length, is(3));
		assertThat(result.get(0)[0], is("10"));
		assertThat(result.get(0)[1], is("AU"));
		assertThat(result.get(0)[2], is("Australia"));
	}

	@Test
	public void test_double_quotes_but_double_quotes_in_column() {

		String line = "\"10\",\"AU\",\"Aus\"\"tralia\"";
		List<String[]> result = parser.csvReader(line);

		assertThat(result, IsNull.notNullValue());
		assertThat(result.get(0).length, is(3));
		assertThat(result.get(0)[0], is("10"));
		assertThat(result.get(0)[1], is("AU"));
		assertThat(result.get(0)[2], is("Aus\"tralia"));
	}

	@Test
	public void test_custom_separator() {

		String line = "10|AU|Australia";
		List<String[]> result = parser.csvReader(line, '|');

		assertThat(result, IsNull.notNullValue());
		assertThat(result.get(0).length, is(3));
		assertThat(result.get(0)[0], is("10"));
		assertThat(result.get(0)[1], is("AU"));
		assertThat(result.get(0)[2], is("Australia"));
	}

	@Test
	public void test_custom_separator_and_quote() {

		String line = "10;AU;\"Aust,;ralia\"";
		List<String[]> result = parser.csvReader(line, ';');

		assertThat(result, IsNull.notNullValue());
		assertThat(result.get(0).length, is(3));
		assertThat(result.get(0)[0], is("10"));
		assertThat(result.get(0)[1], is("AU"));
		assertThat(result.get(0)[2], is("Aust,;ralia"));
	}

}
