package centrivaccinali;

import database.DatabaseProxy;
import datamodel.*;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;


public class MultiServerImpl extends UnicastRemoteObject implements MultiServer{
	DatabaseProxy db;

	public MultiServerImpl() throws RemoteException, SQLException {
		super();
	}

	public ArrayList<CentroVaccinale> nomeCentriServ(String nome) throws RemoteException {
		ArrayList<CentroVaccinale> centri = null;
		centri = db.listCentriVaccinali(nome);
		return centri;
	}

	public ArrayList<CentroVaccinale> comuneTipoCentriServ(String comune, String tipo) throws RemoteException {
		ArrayList<CentroVaccinale> centro = null;
		centro = db.listCentriVaccinali(comune, tipo);
		return centro;
	}

	public CentroVaccinale centroVaccinaleServ(String nome, String comune) throws RemoteException {
		CentroVaccinale centroScelto = null;
		centroScelto = db.selectCentroVaccinale(nome, comune);
		return centroScelto;
	}	

	public Vaccinato vaccinatoServ(short id) throws RemoteException {
		Vaccinato vax = null;
		vax = db.selectVaccinato(id);
		return vax;
	}

	public Boolean newCittadinoRegistrato(CittadinoRegistrato cittadinoRegistrato) throws RemoteException {
		Boolean cr = false;
		db.insertCittadinoRegistrato(cittadinoRegistrato);
		cr = true;
		return cr;
	}

	public CittadinoRegistrato cittadinoRegistratoServ(String id) throws RemoteException {
		CittadinoRegistrato crScelto = null;
		crScelto = db.selectCittadinoRegistrato(id);
		return crScelto;
	}

	public Boolean newEventoAvverso(EventoAvverso eventoAvverso) throws RemoteException {
		Boolean ea = false;
		db.insertEventoAvverso(eventoAvverso);
		ea = true;
		return ea;
	}

	public EventoAvverso eventoAvversoServ(String sintomo, String id) throws RemoteException {
		EventoAvverso eaScelto = null;
		eaScelto = db.selectEventoAvverso(sintomo,id);
		return eaScelto;
	}

	public Boolean newAggregazioneEventi() throws RemoteException {
		Boolean ae = false;
		db.insertAggregazioneEventi();
		ae = true;
		return ae;
	}
	
	public ArrayList<EventoAvverso> eventiAvversi(short id) throws RemoteException {
		ArrayList<EventoAvverso> eventiAvversi = null;
		eventiAvversi = db.listEventiAvversi(id);
		return eventiAvversi;
	}

	public ArrayList<AggregazioneEventi> aggregazioniEventi(String nome, String comune) throws RemoteException {
		ArrayList<AggregazioneEventi> aeScelto = null;
		aeScelto = db.listAggregazioniEventi(nome, comune);
		return aeScelto;
	}

	public Boolean updateAggregazioneEventiServ() throws RemoteException {
		Boolean upAe = false;
		db.updateAggregazioniEventi();
		upAe = true;
		return upAe;
	}

	public Boolean updateCentriVaccinaliServ() throws RemoteException {
		Boolean upCv = false;
		db.updateCentriVaccinali();
		upCv = true;
		return upCv;
	}

	public static void main (String[] args) throws RemoteException {
		try {
			MultiServerImpl server = new MultiServerImpl();
			server.db=new DatabaseProxy(args[0],args[1],args[2]);
			Registry registro = LocateRegistry.createRegistry(1099);
			registro.rebind("Server", server);
			System.out.println("Server ready");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}