package centrivaccinali;

import database.DatabaseProxy;
import datamodel.*;
import interfaces.MultiServer;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class CentriVaccinali extends UnicastRemoteObject implements MultiServer {
	DatabaseProxy db;

	public CentriVaccinali() throws RemoteException{
		super();
	}

	public ArrayList<CentroVaccinale> nomeCentriServ(String nome) throws RemoteException {
		return db.listCentriVaccinali(nome);
	}

	public ArrayList<CentroVaccinale> comuneTipoCentriServ(String comune, String tipo) throws RemoteException {
		return db.listCentriVaccinali(comune, tipo);
	}

	public CentroVaccinale centroVaccinaleServ(String nome, String comune) throws RemoteException {
		return db.selectCentroVaccinale(nome, comune);
	}	

	public Vaccinato vaccinatoServ(int id) throws RemoteException {
		return db.selectVaccinato(id);
	}

	public Boolean newCittadinoRegistrato(CittadinoRegistrato cittadinoRegistrato) throws RemoteException {
		return db.insertCittadinoRegistrato(cittadinoRegistrato);
	}

	public CittadinoRegistrato cittadinoRegistratoServ(String id) throws RemoteException {
		return db.selectCittadinoRegistrato(id);
	}

	public Boolean newEventoAvverso(EventoAvverso eventoAvverso) throws RemoteException {
		return db.insertEventoAvverso(eventoAvverso);
	}

	public EventoAvverso eventoAvversoServ(String sintomo, String id) throws RemoteException {
		return db.selectEventoAvverso(sintomo,id);
	}

	public Boolean newAggregazioneEventi() throws RemoteException {
		return db.insertAggregazioneEventi();
	}
	
	public ArrayList<EventoAvverso> eventiAvversi(int id) throws RemoteException {
		return db.listEventiAvversi(id);
	}

	public ArrayList<AggregazioneEventi> aggregazioniEventi(String nome, String comune) throws RemoteException {
		return db.listAggregazioniEventi(nome, comune);
	}

	public Boolean updateAggregazioneEventiServ() throws RemoteException {
		return db.updateAggregazioniEventi();
	}

	public Boolean updateCentriVaccinaliServ() throws RemoteException {
		return db.updateCentriVaccinali();
	}

	public static void main (String[] args) throws RemoteException {
		try {
			CentriVaccinali server = new CentriVaccinali();
			Scanner in=new Scanner(System.in);
			String host,password,user;
			System.out.println("Inserire user database: ");
			user=in.next();
			System.out.println("Inserire password database: ");
			password=in.next();
			System.out.println("Inserire host database: ");
			host=in.next();
			try{
				server.db=new DatabaseProxy(user,password,host);
			}catch(SQLException e){
				System.out.println("Accesso al database fallito");
				return;
			}
			Registry registro = LocateRegistry.createRegistry(1099);
			registro.rebind("Server", server);
			System.out.println("Server ready");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}