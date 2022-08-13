package database;
import java.sql.*;
import java.util.ArrayList;

public class Database {
	private static Database database;
	private static Connection connection;
	
	private Database(String user, String password, String host) throws SQLException {
		connection=DriverManager.getConnection("jdbc:postgresql://"+host+"/centrivaccinali",user,password);
	}
	
	// Sfrutto il pattern Singleton per assicurarmi di gestire la 
	// comunicazione con il db in modo centralizzato.
	/**
	 * Restituisce un'istanza del database.
	 * @param user L'user del database.
	 * @param password La password del database.
	 * @param host L'host al quale collegarsi.
	 * @return Un'istanza del databse.
	 */
	public static Database getInstance(String user, String password, String host) throws SQLException {
		if (database == null) {
			database = new Database(user,password,host);
		}
		return database;
	}
	
	private void createDatabase() {
		String query= """
				CREATE TABLE centri_vaccinali(
				      nome VARCHAR(50) NOT NULL,
				      comune VARCHAR(35) NOT NULL,
				      indirizzo VARCHAR(50) NOT NULL,
				      tipo VARCHAR(20) NOT NULL,
				      totale_segnalazioni NUMERIC,
				      media_generale NUMERIC,
				      PRIMARY KEY(nome,comune)
				  );
				  
				  CREATE TABLE vaccinati(
				      ID_vaccinazione INTEGER PRIMARY KEY CHECK (ID_vaccinazione>=0 AND ID_vaccinazione<56535),
				      codice_fiscale CHAR(16) UNIQUE NOT NULL CHECK(codice_fiscale ~ '[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]'),
				      nome_cognome VARCHAR(40),
				      nome VARCHAR(50) NOT NULL,
				      comune VARCHAR(35) NOT NULL,
				      data_vaccinazione DATE DEFAULT CURRENT_DATE,
				      tipo_vaccino VARCHAR(20),
				      FOREIGN KEY(nome,comune) REFERENCES centri_vaccinali
				  );
				  
				  CREATE TABLE cittadini_registrati(
				      ID_vaccinazione INTEGER PRIMARY KEY REFERENCES vaccinati,
				      user_ID VARCHAR(30) NOT NULL UNIQUE,
				      password VARCHAR(30) NOT NULL,
				      email VARCHAR(50) NOT NULL CHECK(email ~* '^[-\\w.]+@[A-Z_.]+?[A-Z]{2,4}$')
				  );
				  
				  CREATE TABLE eventi_avversi(
				      sintomo VARCHAR(30) NOT NULL,
				      id_vaccinazione INTEGER NOT NULL REFERENCES cittadini_registrati,
				      severita INTEGER CHECK(severita>=1 AND severita <=5),
				      note VARCHAR(256),
				      nome VARCHAR(50) NOT NULL,
				      comune VARCHAR(35) NOT NULL,
				      PRIMARY KEY(sintomo,id_vaccinazione),
				      FOREIGN KEY(nome,comune) REFERENCES centri_vaccinali
				  );
				  
				  CREATE TABLE aggregazioni_eventi(
				      sintomo VARCHAR(30) NOT NULL,
				      nome VARCHAR(50) NOT NULL,
				      comune VARCHAR(35) NOT NULL,
				      numero_segnalazioni INTEGER,
				      media_severita NUMERIC,
				      PRIMARY KEY(sintomo,nome,comune),
				      FOREIGN KEY(nome,comune) REFERENCES centri_vaccinali
				  );""";
		try {
			PreparedStatement pstmnt=connection.prepareStatement(query);
			pstmnt.execute();
		} catch (SQLException e) {}
	}
	
	private ResultSet executeQuery(String query) throws SQLException{
		Statement statement=connection.createStatement();
		if ( statement.execute(query) ) {
			return statement.getResultSet();
		}
		return null;
	}
	/**
	 * Chiude la connessione al database.
	 */
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {}
	}
	/**
	 * Restituisce una connessione al database.
	 * @return La connessione al database.
	 */
	public Connection getConnection() {
		return connection;
	}
	
}
