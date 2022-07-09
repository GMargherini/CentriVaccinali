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
	public static Database getInstance(String user, String password, String host) throws SQLException {
		if (database == null) {
			database = new Database(user,password,host);
		}
		return database;
	}
	public void createDatabase() {
		String query="CREATE TABLE centri_vaccinali(\n"
				+ "    nome VARCHAR(50) NOT NULL,\n"
				+ "    comune VARCHAR(35) NOT NULL,\n"
				+ "    indirizzo VARCHAR(50) NOT NULL,\n"
				+ "    tipo VARCHAR(20) NOT NULL,\n"
				+ "    totale_segnalazioni NUMERIC,\n"
				+ "    media_generale NUMERIC,\n"
				+ "    PRIMARY KEY(nome,comune)\n"
				+ ");\n"
				+ "\n"
				+ "CREATE TABLE vaccinati(\n"
				+ "    ID_vaccinazione INTEGER PRIMARY KEY CHECK (ID_vaccinazione>=0 AND ID_vaccinazione<56535),\n"
				+ "    codice_fiscale CHAR(16) UNIQUE NOT NULL CHECK(codice_fiscale ~ '[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]'),\n"
				+ "    nome_cognome VARCHAR(40),\n"
				+ "    nome VARCHAR(50) NOT NULL,\n"
				+ "    comune VARCHAR(35) NOT NULL,\n"
				+ "    data_vaccinazione DATE DEFAULT CURRENT_DATE,\n"
				+ "    tipo_vaccino VARCHAR(20),\n"
				+ "    FOREIGN KEY(nome,comune) REFERENCES centri_vaccinali\n"
				+ ");\n"
				+ "\n"
				+ "CREATE TABLE cittadini_registrati(\n"
				+ "    ID_vaccinazione INTEGER NOT NULL REFERENCES vaccinati,\n"
				+ "    user_ID VARCHAR(30) PRIMARY KEY,\n"
				+ "    password VARCHAR(30) NOT NULL,\n"
				+ "    email VARCHAR(50) NOT NULL CHECK(email ~* '^[-\\w.]+@[A-Z_.]+?[A-Z]{2,4}$')\n"
				+ ");\n"
				+ "\n"
				+ "CREATE TABLE eventi_avversi(\n"
				+ "    sintomo VARCHAR(30) NOT NULL,\n"
				+ "    id_vaccinazione INTEGER NOT NULL REFERENCES cittadini_registrati,\n"
				+ "    severita INTEGER CHECK(severita>=1 AND severita <=5),\n"
				+ "    note VARCHAR(256),\n"
				+ "    nome VARCHAR(50) NOT NULL,\n"
				+ "    comune VARCHAR(35) NOT NULL,\n"
				+ "    PRIMARY KEY(sintomo,id_vaccinazione),\n"
				+ "    FOREIGN KEY(nome,comune) REFERENCES centri_vaccinali\n"
				+ ");\n"
				+ "\n"
				+ "CREATE TABLE aggregazioni_eventi(\n"
				+ "    sintomo VARCHAR(30) NOT NULL,\n"
				+ "    nome VARCHAR(50) NOT NULL,\n"
				+ "    comune VARCHAR(35) NOT NULL,\n"
				+ "    numero_segnalazioni INTEGER,\n"
				+ "    media_severita NUMERIC,\n"
				+ "    PRIMARY KEY(sintomo,nome,comune),\n"
				+ "    FOREIGN KEY(nome,comune) REFERENCES centri_vaccinali\n"
				+ ");";
		try {
			PreparedStatement pstmnt=connection.prepareStatement(query);
			pstmnt.execute();
		} catch (SQLException e) {}
	}
	
	public ResultSet executeQuery(String query) throws SQLException{
		Statement statement=connection.createStatement();
		if ( statement.execute(query) ) {
			return statement.getResultSet();
		}
		return null;
	}
	
	public static void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}
	
}
