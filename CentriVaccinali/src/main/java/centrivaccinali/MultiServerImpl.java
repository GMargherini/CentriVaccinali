package centrivaccinali;

import datamodel.*;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class MultiServerImpl extends UnicastRemoteObject implements MultiServer{
	public MultiServerImpl() throws RemoteException {
		super();
	}
	
	public ArrayList<CentroVaccinale> nomeCentriServ(String nome) throws RemoteException; {
		ArrayList<CentroVaccinale> centri = null;
		try {
			centri = DatabaseProxy.listCentriVaccinali(nome);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return centri;
	}

	public ArrayList<CentroVaccinale> comuneTipoCentriServ(String comune, String tipo) throws RemoteException; {
		ArrayList<CentroVaccinale> centro = null;
		try {
			centro = DatabaseProxy.listCentriVaccinali(comune, tipo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return centro;
	}

	public CentroVaccinale centroVaccinaleServ(String nome, String comune) throws RemoteException; {
		CentroVaccinale centroScelto = null;
		try {
			centroScelto = DatabaseProxy.selectCentroVaccinale(nome, comune);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return centroScelto;
	}	

	public Vaccinato vaccinatoServ(short id) throws RemoteException; {
		Vaccinato vax = null;
		try {
			vax = DatabaseProxy.selectVaccinato(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vax;		
	}

	public Boolean newCittadinoRegistrato(CittadinoRegistrato cittadinoRegistrato) throws RemoteException; {
		Boolean cr = false;
		try {
			DatabaseProxy.insertCittadinoRegistrato(cittadinoRegistrato);
			cr = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cr;
	}

	public CittadinoRegistrato cittadinoRegistratoServ(String id) throws RemoteException; {
		CittadinoRegistrato crScelto = null;
		try {
			crScelto = DatabaseProxy.selectCittadinoRegistrato(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return crScelto;				
	}

	public Boolean newEventoAvverso(EventoAvverso eventoAvverso) throws RemoteException; {
		Boolean ea = false;
		try {
			DatabaseProxy.insertEventoAvverso(eventoAvverso);
			ea = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ea;		
	}

	public EventoAvverso eventoAvversoServ(String sintomo, String id) throws RemoteException; {
		EventoAvverso eaScelto = null;
		try {
			eaScelto = DatabaseProxy.selectEventoAvverso(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eaScelto;		
	}

	public Boolean newAggregazioneEventi() throws RemoteException; {
		Boolean ae = false;
		try {
			DatabaseProxy.insertAggregazioneEventi();
			ae = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ae;		
	}
	
	public ArrayList<EventoAvverso> eventiAvversi(String id) throws RemoteException; {
		ArrayList<EventoAvverso> eventiAvversi = null;
		try {
			eventiAvversi = DatabaseProxy.listEventiAvversi(id);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventiAvversi;
	}

	public AggregazioneEventi aggregazioneEventi(String sintomo, String nome, String comune) throws RemoteException; {
		AggregazioneEventi aeScelto = null;
		try {
			aeScelto = DatabaseProxy.selectAggregazioneEventi(sintomo, nome, comune);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aeScelto;			
	}

	public Boolean updateAggregazioneEventiServ() throws RemoteException; {
		Boolean upAe = false;
		try {
			DatabaseProxy.updateAggregazioniEventi();
			upAe = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return upAe;			
	}

	public Boolean updateCentriVaccinaliServ() throws RemoteException; {
		Boolean upCv = false;
		try {
			DatabaseProxy.updateCentriVaccinali();
			upCv = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return upCv;			
	}
	
	
	
	public static void main (String[] args) throws RemoteException {
		try {
			MultiServerImpl server = new MultiServerImpl();
			Registry registro = LocateRegistry.createRegistry(1099);
			registro.rebind("Server", server);
			System.out.println("Server ready");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}