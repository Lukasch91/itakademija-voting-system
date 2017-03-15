package preloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Giedrius Mauza on 2017-02-06. 
 * Updated by Sagg 2017-03-13.
 */
public class DeletePrefillDb {
	Connection conn = null;
	Statement stmt = null;

	@Ignore
	@Test
	public void prefillH2Database() throws ClassNotFoundException, SQLException {
		Assert.assertTrue(fillDb());
	}

	private boolean fillDb() throws ClassNotFoundException, SQLException {
		String user = "sa";
		String pass = "";
		final String JDBC_DRIVER = "org.h2.Driver";
		final String url = "jdbc:h2:./db/h2/database-dev;MV_STORE=true;AUTO_SERVER=TRUE;IFEXISTS=TRUE";
		
		
		
		Class.forName(JDBC_DRIVER);
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(url, user, pass);
		System.out.println("Updating database...");
		conn.setAutoCommit(false);
		stmt = conn.createStatement();

		try {
			deleteAllDataInDataBase();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// ============================================================================

		@SuppressWarnings("unused")
		List<String> admin = getTestDataFromTxtFile("db/DATA/Admin.txt");
		List<String> constituencies = getTestDataFromTxtFile("db/DATA/Constituencies.txt");
		List<String> districts = getTestDataFromTxtFile("db/DATA/Districts.txt");
		List<String> parties = getTestDataFromTxtFile("db/DATA/Parties.txt");
		List<String> representatives = getTestDataFromTxtFile("db/DATA/Representatives.txt");
		List<String> consCandidates = getTestDataFromTxtFile("db/DATA/ConsCandidates.txt");
		List<String> partyCandidates = getTestDataFromTxtFile("db/DATA/PartyCandidates.txt");
		List<String> multiVotes = getTestDataFromTxtFile("db/DATA/MultiVotes.txt");
		List<String> singleVotes = getTestDataFromTxtFile("db/DATA/SingleVotes.txt");
		List<String> spoiltVotes = getTestDataFromTxtFile("db/DATA/SpoiltVotes.txt");
		
		
		
		// for (String sqlValues : admin) {
		// stmt.execute("INSERT INTO ADMIN (ID , LOGIN_NAME, PASSWORD ) VALUES
		// ("+ sqlValues +");");
		// }
		for (String sqlValues : constituencies) {
			stmt.execute("INSERT INTO CONSTITUENCY(ID, TITLE) VALUES (" + sqlValues + ");");
		}
		for (String sqlValues : districts) {
			stmt.execute(
					"INSERT INTO DISTRICTS " + "(ID, ADDRESS, CONSTITUENCY_ID, DELETED_TIME, NUMBER_OF_VOTERS, TITLE ) "
							+ "VALUES (" + sqlValues + ");");
		}
		for (String sqlValues : representatives) {
			stmt.execute(
					"INSERT INTO REPRESENTATIVES (ID, DISTRICT_ID, EMAIL, LOGIN_NAME, NAME, PASSWORD, SURNAME) "
							+ "VALUES (" + sqlValues + ");");
		}
		for (String sqlValues : parties) {
			stmt.execute(
					"INSERT INTO PARTIES (ID, DELETED_TIME, PARTY_ABBREVIATION, TITLE) "
							+ "VALUES (" + sqlValues + ");");
		}
		for (String sqlValues : consCandidates) {
			stmt.execute(
					"INSERT INTO CANDIDATES (CANDIDATEID, CANDIDATE_DATE_OF_BIRTH, CANDIDATE_DELETED_DATE, CANDIDATE_DESCRIPTION, CANDIDATE_NAME, CANDIDATE_NUMBER_IN_PARTY, CANDIDATE_PERSONALID, CANDIDATE_SURNAME, CANDIDATE_CONSTITUENCY, CANDIDATE_PARTY) "
							+ "VALUES (" + sqlValues + ");");
		}
		for (String sqlValues : partyCandidates) {
		stmt.execute(
				"INSERT INTO CANDIDATES (CANDIDATEID, CANDIDATE_DATE_OF_BIRTH, CANDIDATE_DELETED_DATE, CANDIDATE_DESCRIPTION, CANDIDATE_NAME, CANDIDATE_NUMBER_IN_PARTY, CANDIDATE_PERSONALID, CANDIDATE_SURNAME, CANDIDATE_CONSTITUENCY, CANDIDATE_PARTY) "
						+ "VALUES (" + sqlValues + ");");
		}
		for (String sqlValues : multiVotes) {
		stmt.execute(
				"INSERT INTO MULTI_MEMBER_VOTES (ID, DELETED_DATE, ENTERED_DATE, PUBLISHED_DATE, VOTES, DISTRICT_ID, PARTY_ID) "
						+ "VALUES (" + sqlValues + ");");
		}
		for (String sqlValues : singleVotes) {
		stmt.execute(
				"INSERT INTO SINGLE_MEMBER_VOTES (SINGLE_ID,SINGLE_DELETED_DATE, SINGLE_ENTERED_DATE, SINGLE_PUBLISHED_DATE, SINGLE_VOTES, SINGLE_CANDIDATE, SINGLE_DISTRICT ) "
						+ "VALUES (" + sqlValues + ");");
		}		
		for (String sqlValues : spoiltVotes) {
		stmt.execute(
				"INSERT INTO CORRUPTED_VOTES (ID, DELETED_DATE, ENTERED_DATE, PUBLISHED_DATE, TYPE_MULTI, VOTES, DISTRICT_ID) "
						+ "VALUES (" + sqlValues + ");");		
		}		
		
		
		
		
		
		
		// ============================================================================
		conn.commit();
		conn.setAutoCommit(true);
		stmt.close();
		conn.close();
		System.out.println("Finished...");

		return true;
	}

	private static List<String> getTestDataFromTxtFile(String fileName) {
		List<String> records = new ArrayList<String>();

		try {
			File fileDir = new File(fileName);

			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF-8"));
			String str;

			while ((str = in.readLine()) != null) {
				records.add(str);
			}
			in.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return records;
	}

	private void deleteAllDataInDataBase() throws ClassNotFoundException, SQLException, InterruptedException {
		stmt = conn.createStatement();

		stmt.execute("DELETE FROM SINGLE_MEMBER_VOTES;");
		stmt.execute("DELETE FROM MULTI_MEMBER_VOTES;");
		stmt.execute("DELETE FROM CORRUPTED_VOTES;");
		stmt.execute("DELETE FROM CANDIDATES;");
		stmt.execute("DELETE FROM REPRESENTATIVES;");
		stmt.execute("DELETE FROM DISTRICTS;");
		stmt.execute("DELETE FROM CONSTITUENCY;");
		stmt.execute("DELETE FROM PARTIES;");
//		stmt.execute("DELETE FROM ADMIN;");
		
		conn.commit();
		conn.setAutoCommit(true);
	}
}