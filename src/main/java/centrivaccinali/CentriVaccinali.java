/*
 * Antonicelli Sandy, 744947, VA
 * Caffi Nicolò, 745391, VA
 * Margherini Giorgio, 744148, VA
 */
package centrivaccinali;

import database.*;
import datamodel.*;
import interfaces.CentriVaccinaliInt;
import java.rmi.registry.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.*;

/**
 *
 */
public class CentriVaccinali extends UnicastRemoteObject implements CentriVaccinaliInt {
	private DatabaseProxy db;

	public CentriVaccinali() throws RemoteException{
		super();
	}

	/**
	 * @see CentriVaccinaliInt
	 */
	public Boolean registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException {
		return db.insertCentroVaccinale(centroVaccinale);
	}
	/**
	 * @see CentriVaccinaliInt
	 */
	public ArrayList<CentroVaccinale> cercaCentroVaccinale(String nome) throws RemoteException {
		return db.listCentriVaccinali(nome);
	}
	/**
	 * @see CentriVaccinaliInt
	 */
	public ArrayList<CentroVaccinale> cercaCentroVaccinale(String comune, String tipo) throws RemoteException {
		return db.listCentriVaccinali(comune, tipo);
	}
	/**
	 * @see CentriVaccinaliInt
	 */
	public CentroVaccinale visualizzaInfoCentroVaccinale(String nome, String comune) throws RemoteException {
		return db.selectCentroVaccinale(nome, comune);
	}
	/**
	 * @see CentriVaccinaliInt
	 */
	public Boolean registraVaccinato(Vaccinato vaccinato) throws RemoteException {
		return db.insertVaccinato(vaccinato);
	}
	/**
	 * @see CentriVaccinaliInt
	 */
	public Vaccinato visualizzaInfoVaccinato(int id) throws RemoteException {
		return db.selectVaccinato(id);
	}
	/**
	 * @see CentriVaccinaliInt
	 */
	public Boolean registraCittadino(CittadinoRegistrato cittadinoRegistrato) throws RemoteException {
		return db.insertCittadinoRegistrato(cittadinoRegistrato);
	}
	/**
	 * @see CentriVaccinaliInt
	 */
	public CittadinoRegistrato visualizzaInfoCittadinoRegistrato(String id) throws RemoteException {
		return db.selectCittadinoRegistrato(id);
	}
	/**
	 * @see CentriVaccinaliInt
	 */
	public Boolean newEventoAvverso(EventoAvverso eventoAvverso) throws RemoteException {
		return db.insertEventoAvverso(eventoAvverso);
	}
	/**
	 * @see CentriVaccinaliInt
	 */
	public ArrayList<EventoAvverso> eventiAvversi(int id) throws RemoteException {
		return db.listEventiAvversi(id);
	}
	/**
	 * @see CentriVaccinaliInt
	 */
	public ArrayList<AggregazioneEventi> aggregazioniEventi(String nome, String comune) throws RemoteException {
		return db.listAggregazioniEventi(nome, comune);
	}
	/**
	 * @see CentriVaccinaliInt
	 */
	public Boolean updateAggregazioneEventiServ() throws RemoteException {
		return db.updateAggregazioniEventi();
	}
	/**
	 * @see CentriVaccinaliInt
	 */
	public Boolean updateCentriVaccinaliServ() throws RemoteException {
		return db.updateCentriVaccinali();
	}

	public static void main (String[] args) throws RemoteException {
		try {
			CentriVaccinali server = new CentriVaccinali();
			Scanner in=new Scanner(System.in);
			String host,password,user,exit="";
			System.out.println("Inserire host database: ");
			host=in.next();
			System.out.println("Inserire user database: ");
			user=in.next();
			System.out.println("Inserire password database: ");
			password=in.next();
			server.db=new DatabaseProxy(user,password,host);
			Registry registro = LocateRegistry.createRegistry(1099);
			registro.rebind("Server", server);
			System.out.println("Server ready");
			while(!exit.equals("exit")){
				System.out.println("Digitare \"exit\" per uscire");
				exit=in.next();
			}
			System.out.println("Disconnessione dal database");
			Database.closeConnection();
			System.out.println("Chiusura server");
			unexportObject(server,false);
		} catch (SQLException e) {
			System.out.println("Accesso al database fallito");
			System.exit(1);
		}
	}
}