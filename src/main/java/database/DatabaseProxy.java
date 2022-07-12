package database;

import java.sql.*;
import java.util.ArrayList;

import datamodel.*;

public class DatabaseProxy {
	
	Database db;
	Connection connection;
	PreparedStatement pstmnt;
	
	
	public DatabaseProxy(String user, String password, String host) throws SQLException {
		db=Database.getInstance(user,password,host);
		connection=db.getConnection();
	}
	public Boolean insertCentroVaccinale(CentroVaccinale centro) {
		String query="INSERT INTO centri_vaccinali (nome, comune, indirizzo, tipo)\n"
					+ "VALUES (?,?,?,?);";
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
			pstmnt=connection.prepareStatement(query);
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
			pstmnt=connection.prepareStatement(query);
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
			pstmnt=connection.prepareStatement(query);
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
			pstmnt=connection.prepareStatement(query);
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
			pstmnt=connection.prepareStatement(query);
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
			pstmnt=connection.prepareStatement(query);
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
			pstmnt=connection.prepareStatement(query);
			pstmnt.setString(0, ea.getSintomo());
			pstmnt.setShort(1, ea.getIdVaccinazione());
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
			pstmnt=connection.prepareStatement(query);
			pstmnt.setString(0, id);
			pstmnt.setString(1, sintomo);
			ResultSet rs=pstmnt.executeQuery();
			if(rs.first()) {
				EventoAvverso eventoAvverso = new EventoAvverso(
						rs.getString("sintomo"),
						rs.getShort("id_vaccinazione"),
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
	
	public Boolean insertAggregazioneEventi() {
		String query = "INSERT INTO aggregazioni_eventi(sintomo, nome, comune)\n"
					+ "select distinct sintomo, nome, comune\n"
					+ "from eventi_avversi";
		try {
			pstmnt=connection.prepareStatement(query);
			pstmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	public AggregazioneEventi selectAggregazioneEventi(String sintomo, String nome,String comune) {
		String query = "SELECT *\n"
				+"FROM aggregazioni_eventi\n"
				+"WHERE sintomo=? AND nome=? AND comune=?";
		try {
			pstmnt=connection.prepareStatement(query);
			pstmnt.setString(0, sintomo);
			pstmnt.setString(1, nome);
			pstmnt.setString(2, comune);
			ResultSet rs=pstmnt.executeQuery();
			if(rs.first()) {
				AggregazioneEventi aggregazioneEventi = new AggregazioneEventi(
						rs.getString("sintomo"),
						rs.getString("nome"),
						rs.getString("comune"),
						rs.getInt("numero_segnalazioni"),
						rs.getDouble("media_severita")
						);
				return aggregazioneEventi;
			}
			return null;
		} catch (SQLException e) {
			return null;
		}
	}
	public Boolean updateAggregazioniEventi() {
		String query = "UPDATE aggregazioni_eventi ae\n"
					+ "SET numero_segnalazioni=(SELECT COUNT(*)\n"
					+ "		FROM centri_vaccinali cv, eventi_avversi ea\n"
					+ "		WHERE ea.nome=cv.nome AND ea.comune=cv.comune),\n"
					+ "	media_severita=(SELECT AVG(severita)\n"
					+ "		FROM eventi_avversi ea, centri_vaccinali cv\n"
					+ "		WHERE ea.nome=cv.nome AND ea.comune=cv.comune AND ae.sintomo=ea.sintomo)";
		try {
			pstmnt=connection.prepareStatement(query);
			pstmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	public Boolean updateCentriVaccinali() {
		String query = "update centri_vaccinali cv\n"
					+ "set totale_segnalazioni=(select sum(numero_segnalazioni)\n"
					+ "			from aggregazioni_eventi\n"
					+ "			where nome=cv.nome and comune=cv.comune),\n"
					+ "	media_generale=(select sum(media_severita * numero_segnalazioni)/sum(numero_segnalazioni)\n"
					+ "			from aggregazioni_eventi\n"
					+ "			where nome=cv.nome and comune=cv.comune\n"
					+ "			group by sintomo)";
		try {
			pstmnt=connection.prepareStatement(query);
			pstmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	public void closeConnection() {
		db.closeConnection();
	}
}
