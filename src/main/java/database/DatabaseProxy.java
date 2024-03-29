/*
 * Antonicelli Sandy, 744947, VA
 * Caffi Nicolò, 745391, VA
 * Margherini Giorgio, 744148, VA
 */
package database;

import java.sql.*;
import java.util.ArrayList;
import datamodel.*;

/**
 *
 */
public class DatabaseProxy {
	
	Database db;
	Connection connection;
	PreparedStatement pstmnt;
	
	
	public DatabaseProxy(String user, String password, String host) throws SQLException {
		db=Database.getInstance(user,password,host);
		connection=db.getConnection();
	}
	/**
	 * Inserisce un centro vaccinale nel database.
	 * @param centro il centro da inserire.
	 * @return <code>true</code> se l'operazione va a buon fine,  <code>false</code> altrimenti.
	 */
	public Boolean insertCentroVaccinale(CentroVaccinale centro) {
		String query="INSERT INTO CentriVaccinali (nome, comune, indirizzo, tipo)\n"
					+ "VALUES (?,?,?,?);";
		try {
			pstmnt = connection.prepareStatement(query);
			pstmnt.setString(1, centro.getNome());
			pstmnt.setString(2, centro.getComune());
			pstmnt.setString(3, centro.getIndirizzo());
			pstmnt.setString(4, centro.getTipo());
			pstmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
		
	}
	/**
	 * Restituisce una lista di centri vaccinali che contengono <code>nome</code> nel nome.
	 * @param nome La stringa da cercare
	 * @return Un'<code>ArrayList</code> di <code>CentroVaccinale</code>
	 */
	public ArrayList<CentroVaccinale> listCentriVaccinali(String nome){
		String query= """
				SELECT nome, comune
				FROM CentriVaccinali
				WHERE LOWER(nome) LIKE LOWER(?) AND nome<>'amministrazione'
				ORDER BY comune, nome""";
		try {
			pstmnt=connection.prepareStatement(query);
			pstmnt.setString(1, "%"+nome+"%");
			ResultSet rs=pstmnt.executeQuery();
			ArrayList<CentroVaccinale> centri=new ArrayList<CentroVaccinale>();
			while(rs.next()) {
				centri.add(new CentroVaccinale(rs.getString("nome"), rs.getString("comune")));
			}
			rs.close();
			return centri;
		} catch (SQLException e) {
			return null;
		}
	}
	/**
	 * Restituisce una lista di centri vaccinali con comune uguale a <code>comune</code> e tipo uguale a <code>tipo</code>.
	 * @param comune Il comune del centro da cercare
	 * @param tipo Il tipo del centro da cercare
	 * @return Un'<code>ArrayList</code> di <code>CentroVaccinale</code>
	 */
	public ArrayList<CentroVaccinale> listCentriVaccinali(String comune,String tipo){
		String query= """
				SELECT nome, comune
				FROM CentriVaccinali
				WHERE comune=? AND tipo=? AND nome<>'amministrazione'
				ORDER BY comune, nome""";
		try {
			pstmnt=connection.prepareStatement(query);
			pstmnt.setString(1, comune);
			pstmnt.setString(2, tipo);
			ResultSet rs=pstmnt.executeQuery();
			ArrayList<CentroVaccinale> centri=new ArrayList<CentroVaccinale>();
			while(rs.next()) {
				centri.add(new CentroVaccinale(rs.getString("nome"), rs.getString("comune")));
			}
			rs.close();
			return centri;
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * Restituisce Il centro vaccinale identificato da <code>nome</code> e <code>comune</code>.
	 * @param nome Il nome del centro vaccinale.
	 * @param comune Il comune del centro vaccinale.
	 * @return Un oggetto di tipo <code>CentroVaccinale</code> se il centro esiste nel database, <code>null</code> altrimenti.
	 */
	public CentroVaccinale selectCentroVaccinale(String nome,String comune) {
		String query= """
				SELECT *
				FROM CentriVaccinali
				WHERE nome=? AND comune=?""";
		try {
			pstmnt=connection.prepareStatement(query);
			pstmnt.setString(1, nome);
			pstmnt.setString(2, comune);
			ResultSet rs=pstmnt.executeQuery();
			if(rs.next()) {
				CentroVaccinale centro = new CentroVaccinale(
						rs.getString("nome"),
						rs.getString("comune"),
						rs.getString("indirizzo"),
						rs.getString("tipo"),
						rs.getInt("totale_segnalazioni"),
						rs.getDouble("media_generale"));
				rs.close();
				return centro;
			}
			rs.close();
			return null;
		} catch (SQLException e) {
			return null;
		}
	}
	/**
	 * Inserisce un vaccinato nel database.
	 * @param vaccinato Il vaccinato da inserire.
	 * @return <code>true</code> se l'operazione va a buon fine,  <code>false</code> altrimenti.
	 */
	public Boolean insertVaccinato(Vaccinato vaccinato) {
		String query="INSERT INTO Vaccinati\n"
				+ "VALUES (?,?,?,?,?,?,?);";
		try {
			pstmnt = connection.prepareStatement(query);
			pstmnt.setInt(1, vaccinato.getIdVaccinazione());
			pstmnt.setString(2, vaccinato.getCodiceFiscale());
			pstmnt.setString(3, vaccinato.getNomeCognome());
			pstmnt.setString(4, vaccinato.getNomeCentro());
			pstmnt.setString(5, vaccinato.getComuneCentro());
			pstmnt.setDate(6, new Date(vaccinato.getDataVaccinazione().getTime()));
			pstmnt.setString(7, vaccinato.getTipoVaccino());
			pstmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	/**
	 * Restituisce il Vaccinato identificato da <code>id</code>.
	 * @param id L'id della vaccinazione.
	 * @return Un oggetto di tipo<code>Vaccinato</code> se il vaccinato esiste nel database, <code>null</code> altrimenti.
	 */
	public Vaccinato selectVaccinato(int id) {
		String query = """
				SELECT *
				FROM Vaccinati
				WHERE id_vaccinazione=?""";
		try {
			pstmnt=connection.prepareStatement(query);
			pstmnt.setInt(1, id);
			ResultSet rs=pstmnt.executeQuery();
			if(rs.next()) {
				Vaccinato vaccinato = new Vaccinato(
						rs.getInt("id_vaccinazione"),
						rs.getString("codice_fiscale"),
						rs.getString("nome_cognome"),
						rs.getString("nome"),
						rs.getString("comune"),
						rs.getDate("data_vaccinazione"),
						rs.getString("tipo_vaccino"));
				rs.close();
				return vaccinato;
			}
			rs.close();
			return null;
		} catch (SQLException e) {
			return null;
		}
	}
	/**
	 * Inserisce un cittadino registrato nel database.
	 * @param cr Il cittadino da inserire.
	 * @return <code>true</code> se l'operazione va a buon fine,  <code>false</code> altrimenti.
	 */
	public Boolean insertCittadinoRegistrato(CittadinoRegistrato cr) {
		String query = "INSERT INTO Cittadini_Registrati\n"
					+ "VALUES (?,?,?,?);";
		try {
			pstmnt=connection.prepareStatement(query);
			pstmnt.setInt(1, cr.getIdVaccinazione());
			pstmnt.setString(2, cr.getUserId());
			pstmnt.setString(3, cr.getPassword());
			pstmnt.setString(4, cr.getEmail());
			pstmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	/**
	 * Restituisce il cittadino registrato identificato da <code>id</code>.
	 * @param id L'user id del cittadino.
	 * @return Un oggetto di tipo<code>CittadinoRegistrato</code> se il cittadino esiste nel database, <code>null</code> altrimenti.
	 */
	public CittadinoRegistrato selectCittadinoRegistrato(String id) {
		String query = """
				SELECT *
				FROM Cittadini_Registrati
				WHERE user_id=?""";
		try {
			pstmnt=connection.prepareStatement(query);
			pstmnt.setString(1, id);
			ResultSet rs=pstmnt.executeQuery();
			if(rs.next()) {
				CittadinoRegistrato cittadinoRegistrato = new CittadinoRegistrato(
						rs.getInt("id_vaccinazione"),
						rs.getString("user_id"),
						rs.getString("password"),
						rs.getString("email")
						);
				rs.close();
				return cittadinoRegistrato;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Inserisce un evento avverso nel database.
	 * @param ea L'evento da inserire.
	 * @return <code>true</code> se l'operazione va a buon fine,  <code>false</code> altrimenti.
	 */
	public Boolean insertEventoAvverso(EventoAvverso ea) {
		String query = "INSERT INTO eventi_avversi\n"
					+ "VALUES (?,?,?,?,?,?);";
		try {
			pstmnt=connection.prepareStatement(query);
			pstmnt.setString(1, ea.getSintomo());
			pstmnt.setInt(2, ea.getIdVaccinazione());
			pstmnt.setInt(3, ea.getSeverita());
			pstmnt.setString(4, ea.getNote());
			pstmnt.setString(5, ea.getNomeCentro());
			pstmnt.setString(6, ea.getComuneCentro());
			pstmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}


	/**
	 *  Restituisce una lista di eventi avversi relativi all'utente <code>id</code>.
	 * @param id L'user id del cittadino
	 * @return Un'<code>ArrayList</code> di <code>EventoAvverso</code>
	 */
	public ArrayList<EventoAvverso> listEventiAvversi(int id){
		String query = """
				SELECT *
				FROM eventi_avversi
				WHERE id_vaccinazione=?""";
		try {
			pstmnt=connection.prepareStatement(query);
			pstmnt.setInt(1, id);
			ResultSet rs=pstmnt.executeQuery();
			ArrayList<EventoAvverso> result=new ArrayList<>();
			while(rs.next()) {
				EventoAvverso eventoAvverso = new EventoAvverso(
						rs.getString("sintomo"),
						rs.getInt("id_vaccinazione"),
						rs.getInt("severita"),
						rs.getString("note"),
						rs.getString("nome"),
						rs.getString("comune")
				);
				result.add(eventoAvverso);
			}
			rs.close();
			return result;
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * Restituisce i dati aggregati relativi al centro vaccinale identificato da <code>nome</code> e <code>comune</code>.
	 * @param nome Il nome del centro vaccinale
	 * @param comune il comune del centro vaccinale
	 * @return Un oggetto di tipo<code>AggregazioneEventi</code> se gli eventi relativi al centro specificato esistono nel database, <code>null</code> altrimenti.
	 */
	public ArrayList<AggregazioneEventi> listAggregazioniEventi(String nome,String comune) {
		String query = """
				SELECT *
				FROM aggregazioni_eventi
				WHERE nome=? AND comune=?
				ORDER BY numero_segnalazioni DESC, media_severita DESC""";
		try {
			pstmnt=connection.prepareStatement(query);
			pstmnt.setString(1, nome);
			pstmnt.setString(2, comune);
			ResultSet rs=pstmnt.executeQuery();
			ArrayList<AggregazioneEventi> result=new ArrayList<>();
			while(rs.next()) {
				AggregazioneEventi aggregazioneEventi = new AggregazioneEventi(
						rs.getString("sintomo"),
						rs.getString("nome"),
						rs.getString("comune"),
						rs.getInt("numero_segnalazioni"),
						rs.getDouble("media_severita")
						);
				result.add(aggregazioneEventi);
			}
			rs.close();
			return result;
		} catch (SQLException e) {
			return null;
		}
	}
	/**
	 * Aggiorna gli eventi avversi aggregati calcolando il numero di segnalazioni e la relativa media dei vari sintomi rispetto al centro nel quale sono stati segnalati.
	 * @return <code>true</code> se l'operazione va a buon fine,  <code>false</code> altrimenti.
	 */
	public Boolean updateAggregazioniEventi() {
		String query =
				"""
				delete from aggregazioni_eventi;
				insert into aggregazioni_eventi(sintomo,nome,comune)
				select distinct sintomo,nome,comune from eventi_avversi;
				UPDATE aggregazioni_eventi ae
				SET numero_segnalazioni=(SELECT COUNT(*)
				FROM eventi_avversi ea
				WHERE ea.nome=ae.nome AND ea.comune=ae.comune AND ae.sintomo=ea.sintomo),
				media_severita=(SELECT AVG(severita)
				FROM eventi_avversi ea
				WHERE ea.nome=ae.nome AND ea.comune=ae.comune AND ae.sintomo=ea.sintomo)""";
		try {
			pstmnt=connection.prepareStatement(query);
			pstmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	/**
	 * Aggiorna centri vaccinali calcolando il numero di segnalazioni e la media totale dei vari sintomi rispetto al centro nel quale sono stati segnalati.
	 * @return <code>true</code> se l'operazione va a buon fine,  <code>false</code> altrimenti.
	 */
	public Boolean updateCentriVaccinali() {
		String query = """
				UPDATE CentriVaccinali cv
				SET totale_segnalazioni=(SELECT DISTINCT SUM(numero_segnalazioni)
					FROM aggregazioni_eventi
				    WHERE nome=cv.nome AND comune=cv.comune),
				media_generale=(SELECT DISTINCT SUM(media_severita * numero_segnalazioni)/SUM(numero_segnalazioni)
				    FROM aggregazioni_eventi
				    WHERE nome=cv.nome AND comune=cv.comune)""";
		try {
			pstmnt=connection.prepareStatement(query);
			pstmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
