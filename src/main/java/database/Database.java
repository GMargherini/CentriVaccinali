/*
 * Antonicelli Sandy, 744947, VA
 * Caffi Nicolò, 745391, VA
 * Margherini Giorgio, 744148, VA
 */
package database;
import java.sql.*;

/**
 *
 */
public class Database {
	private static Database database;
	private static Connection connection;
	
	private Database(String user, String password, String host) throws SQLException {
		connection=DriverManager.getConnection("jdbc:postgresql://"+host+"/centrivaccinali",user,password);
	}

	/**
	 * Restituisce un'istanza del database.
	 * @param user L'user del database.
	 * @param password La password del database.
	 * @param host L'host al quale collegarsi.
	 * @return Un'istanza del database.
	 */
	public static Database getInstance(String user, String password, String host) throws SQLException {
		if (database == null) {
			database = new Database(user,password,host);
		}
		return database;
	}

	/**
	 * Chiude la connessione al database.
	 */
	public static void closeConnection() {
		try {
			connection.close();
		} catch (SQLException ignored) {}
	}
	/**
	 * Restituisce una connessione al database.
	 * @return La connessione al database.
	 */
	public Connection getConnection() {
		return connection;
	}
	
}
