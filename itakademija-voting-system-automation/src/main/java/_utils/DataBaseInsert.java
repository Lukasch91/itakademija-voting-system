package _utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Giedrius Mauza on 2017-02-06.
 */
public class DataBaseInsert {

	private static Connection conn = null;
	private static Statement stmt = null;

	private static String url = "jdbc:h2: ../../../db/h2/database-dev;MV_STORE=true;AUTO_SERVER=TRUE;IFEXISTS=TRUE";

	public static void openDataBase() throws ClassNotFoundException, SQLException {

		String user = "sa";
		String pass = "";
		final String JDBC_DRIVER = "org.h2.Driver";

		// STEP 1: Register JDBC driver
		Class.forName(JDBC_DRIVER);

		// STEP 2: Open a connection
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(url, user, pass);

		// STEP 3: Execute a query
		System.out.println("Updating database...");
		// showAccounts(conn);
		conn.setAutoCommit(false);
	}

	public static void insertDataToDataBaseFromFile(String dataFile)
			throws ClassNotFoundException, SQLException, InterruptedException {
		deleteAllDataInDataBase();

		stmt = conn.createStatement();

		for (String data : FileReaderMain.getTestDataFromTxtFile(dataFile)) {
			stmt.execute(data);

		}

		conn.commit();
		conn.setAutoCommit(true);
	}
	


	public static void deleteAllDataInDataBase() throws ClassNotFoundException, SQLException, InterruptedException {

		stmt = conn.createStatement();

		for (String data : FileReaderMain.getTestDataFromTxtFile(DataBaseCommands.deleteAllData)) {
			stmt.execute(data);

		}

		conn.commit();
		conn.setAutoCommit(true);
	}

	public static void closeDataBase() throws ClassNotFoundException, SQLException {
		// showAccounts(conn);
		// STEP 4: Clean-up environment
		stmt.close();
		conn.close();
		System.out.println("Finished...");

	}

}
