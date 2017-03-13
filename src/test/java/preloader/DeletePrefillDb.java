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
import org.junit.Test;

/**
 * Created by Giedrius Mauza on 2017-02-06. 
 * Updated by Sagg 2017-03-13.
 */
public class DeletePrefillDb {
	Connection conn = null;
	Statement stmt = null;

	@Test
	public void prefillH2Database() throws ClassNotFoundException, SQLException {
		Assert.assertTrue(fillDb());
	}

	private boolean fillDb() throws ClassNotFoundException, SQLException {
		final String constituencies = "db/DATA/Constituencies.txt";
		final String districts = "db/DATA/Districts.txt";
		final String admin = "db/DATA/Admin.txt";

		String user = "sa";
		String pass = "";
		final String JDBC_DRIVER = "org.h2.Driver";
		final String
		// url =
		// "jdbc:h2:/home/giedrius/Projects/Eclipse/itakademija-voting-system-master/db/h2/database-dev;MV_STORE=true;AUTO_SERVER=TRUE;IFEXISTS=TRUE";
		url = "jdbc:h2:C:\\Users\\Sagg\\Desktop\\Prgrmmng\\_JAVA\\Practice\\voting-system\\db\\h2\\database-dev;MV_STORE=true;AUTO_SERVER=TRUE;IFEXISTS=TRUE";

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
		List<String> adm = getTestDataFromTxtFile(admin);
		List<String> constit = getTestDataFromTxtFile(constituencies);
		List<String> dist = getTestDataFromTxtFile(districts);

		// for (String aaa : adm) {
		// stmt.execute("INSERT INTO ADMIN (ID , LOGIN_NAME, PASSWORD ) VALUES
		// ("+ aaa +");");
		// }

		for (String sqlValues : constit) {
			stmt.execute("INSERT INTO CONSTITUENCY(ID, TITLE) VALUES (" + sqlValues + ");");
		}

		for (String sqlValues : dist) {
			stmt.execute(
					"INSERT INTO DISTRICTS " + "(ID, ADDRESS, CONSTITUENCY_ID, DELETED_TIME, NUMBER_OF_VOTERS, TITLE ) "
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

		// turim 9 lenteles db // trinam 8 - admin paliekam
		stmt.execute("DELETE FROM SINGLE_MEMBER_VOTES;");
		stmt.execute("DELETE FROM MULTI_MEMBER_VOTES;");
		stmt.execute("DELETE FROM CORRUPTED_VOTES;");
		stmt.execute("DELETE FROM CANDIDATES;");
		stmt.execute("DELETE FROM REPRESENTATIVES;");
		stmt.execute("DELETE FROM DISTRICTS;");
		stmt.execute("DELETE FROM CONSTITUENCY;");
		stmt.execute("DELETE FROM PARTIES;");

		conn.commit();
		conn.setAutoCommit(true);
	}
}