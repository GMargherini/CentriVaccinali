package database;
import java.sql.*;
import java.util.ArrayList;

public class Database {
	private static Database database;
	private static Connection connection;
	private static Statement statement;
	
	private Database(String user, String password, String host) throws SQLException {
		connection=DriverManager.getConnection("jdbc:postgresql://"+host+"/centrivaccinali",user,password);
		statement=connection.createStatement();
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
				+ "    user_id VARCHAR(30) NOT NULL REFERENCES cittadini_registrati,\n"
				+ "    severita INTEGER CHECK(severita>=1 AND severita <=5),\n"
				+ "    note VARCHAR(256),\n"
				+ "    nome VARCHAR(50) NOT NULL,\n"
				+ "    comune VARCHAR(35) NOT NULL,\n"
				+ "    PRIMARY KEY(sintomo,user_id),\n"
				+ "    FOREIGN KEY(nome,comune) REFERENCES centri_vaccinali\n"
				+ ");\n"
				+ "\n"
				+ "CREATE TABLE aggregazioni_eventi(\n"
				+ "    sintomo VARCHAR(30) NOT NULL REFERENCES eventi_avversi,\n"
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
		if ( statement.execute(query) ) {
			return statement.getResultSet();
		}
		return null;
	}
	
	public Boolean insertCentroVaccinale(CentroVaccinale centro) {
		String query="INSERT INTO centri_vaccinali (nome, comune, indirizzo, tipo)\n"
					+ "VALUES (?,?,?,?);";
		PreparedStatement pstmnt;
		try {
			pstmnt = connection.prepareStatement(query);
			pstmnt.setString(0, centro.getNome());
			pstmnt.setString(1, centro.getComune());
			pstmnt.setString(2, centro.getIndirizzo());
			pstmnt.setString(3, centro.getTipo());
			pstmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
		
	}
	public ArrayList<CentroVaccinale> listCentriVaccinali(String nome){
		String query="SELECT nome, comune\n"
					+ "FROM centri_vaccinali\n"
					+ "WHERE nome LIKE('%?%')";
		try {
			PreparedStatement pstmnt=connection.prepareStatement(query);
			pstmnt.setString(0, nome);
			ResultSet rs=pstmnt.executeQuery();
			ArrayList<CentroVaccinale> centri=new ArrayList<CentroVaccinale>();
			rs.first();
			while(rs.next()) {
				centri.add(new CentroVaccinale(rs.getString("nome"), rs.getString("comune")));
			}
			return centri;
		} catch (SQLException e) {
			return null;
		}
	}
	public ArrayList<CentroVaccinale> listCentriVaccinali(String comune,String tipo){
		String query="SELECT nome, comune\n"
					+ "FROM centri_vaccinali\n"
					+ "WHERE comune=? AND tipo=?";
		try {
			PreparedStatement pstmnt=connection.prepareStatement(query);
			pstmnt.setString(0, comune);
			pstmnt.setString(1, tipo);
			ResultSet rs=pstmnt.executeQuery();
			ArrayList<CentroVaccinale> centri=new ArrayList<CentroVaccinale>();
			rs.first();
			while(rs.next()) {
				centri.add(new CentroVaccinale(rs.getString("nome"), rs.getString("comune")));
			}
			return centri;
		} catch (SQLException e) {
			return null;
		}
	}
	public CentroVaccinale selectCentroVaccinale(String nome,String comune) {
		String query="SELECT *\n"
					+ "FROM centri_vaccinali\n"
					+ "WHERE nome=? AND comune=?";
		try {
			PreparedStatement pstmnt=connection.prepareStatement(query);
			pstmnt.setString(0, nome);
			pstmnt.setString(1, comune);
			ResultSet rs=pstmnt.executeQuery();
			if(rs.first()) {
				CentroVaccinale centro = new CentroVaccinale(
						rs.getString("nome"),
						rs.getString("comune"),
						rs.getString("indirizzo"),
						rs.getString("tipo"),
						rs.getInt("totale_segnalazioni"),
						rs.getDouble("media_generale"));
				return centro;
			}
			return null;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public Boolean insertVaccinato(Vaccinato vaccinato) {
		String query="INSERT INTO vaccinati\n"
				+ "VALUES (?,?,?,?,?,?);";
		PreparedStatement pstmnt;
		try {
			pstmnt = connection.prepareStatement(query);
			pstmnt.setShort(0, vaccinato.getIdVaccinazione());
			pstmnt.setString(1, vaccinato.getCodiceFiscale());
			pstmnt.setString(2, vaccinato.getNomeCognome());
			pstmnt.setString(3, vaccinato.getNomeCentro());
			pstmnt.setString(4, vaccinato.getComuneCentro());
			pstmnt.setDate(5, vaccinato.getDataVaccinazione());
			pstmnt.setString(6, vaccinato.getTipoVaccino());
			pstmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	public Vaccinato selectVaccinato(short id) {
		String query = "SELECT *\n"
					+"FROM vaccinati\n"
					+"WHERE id_vaccinazione=?";
		try {
			PreparedStatement pstmnt=connection.prepareStatement(query);
			pstmnt.setShort(0, id);
			ResultSet rs=pstmnt.executeQuery();
			if(rs.first()) {
				Vaccinato vaccinato = new Vaccinato(
						rs.getShort("id_vaccinazione"),
						rs.getString("codice_fiscale"),
						rs.getString("nome_cognome"),
						rs.getString("nome"),
						rs.getString("comune"),
						rs.getDate("data_vaccinazione"),
						rs.getString("tipo_vaccino"));
				return vaccinato;
			}
			return null;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public Boolean insertCittadinoRegistrato(CittadinoRegistrato cr) {
		String query = "INSERT INTO cittadini_registrati\n"
					+ "VALUES (?,?,?,?);";
		try {
			PreparedStatement pstmnt=connection.prepareStatement(query);
			pstmnt.setShort(0, cr.getIdVaccinazione());
			pstmnt.setString(1, cr.getUserId());
			pstmnt.setString(2, cr.getPassword());
			pstmnt.setString(3, cr.getEmail());
			pstmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	public CittadinoRegistrato selectCittadinoRegistrato(String id) {
		String query = "SELECT *\n"
					+"FROM cittadini_registrati\n"
					+"WHERE user_id=?";
		try {
			PreparedStatement pstmnt=connection.prepareStatement(query);
			pstmnt.setString(0, id);
			ResultSet rs=pstmnt.executeQuery();
			if(rs.first()) {
				CittadinoRegistrato cittadinoRegistrato = new CittadinoRegistrato(
						rs.getShort("id_vaccinazione"),
						rs.getString("user_id"),
						rs.getString("password"),
						rs.getString("email")
						);
				return cittadinoRegistrato;
			}
			return null;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public Boolean insertEventoAvverso(EventoAvverso ea) {
		String query = "INSERT INTO eventi_avversi\n"
					+ "VALUES (?,?,?,?,?,?);";
		try {
			PreparedStatement pstmnt=connection.prepareStatement(query);
			pstmnt.setString(0, ea.getSintomo());
			pstmnt.setString(1, ea.getUserId());
			pstmnt.setInt(2, ea.getSeverita());
			pstmnt.setString(3, ea.getNote());
			pstmnt.setString(4, ea.getNomeCentro());
			pstmnt.setString(5, ea.getComuneCentro());
			pstmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	public EventoAvverso selectEventoAvverso(String sintomo,String id) {
		String query = "SELECT *\n"
				+"FROM eventi_avversi\n"
				+"WHERE user_id=? AND sintomo=?";
		try {
			PreparedStatement pstmnt=connection.prepareStatement(query);
			pstmnt.setString(0, id);
			pstmnt.setString(1, sintomo);
			ResultSet rs=pstmnt.executeQuery();
			if(rs.first()) {
				EventoAvverso eventoAvverso = new EventoAvverso(
						rs.getString("sintomo"),
						rs.getString("user_id"),
						rs.getInt("severita"),
						rs.getString("note"),
						rs.getString("nome"),
						rs.getString("comune")
						);
				return eventoAvverso;
			}
			return null;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
