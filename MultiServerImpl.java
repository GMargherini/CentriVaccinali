/* bozza

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MultiServerImpl extends UnicastRemoteObject implements MultiServer{
	public MultiServerImpl() throws RemoteException {
		super();
	}
	
	public Boolean newCentroVaccinale() throws RemoteException;
	
	String[][] nomeCentriServ() throws RemoteException;

	String[][] comuneTipoCentriServ() throws RemoteException;

	String[] centroVaccinaleServ(String centro) throws RemoteException;
	
	Boolean newVaccinato() throws RemoteException;
	
	String[] vaccinatoServ(String vaccinato) throws RemoteException;
	
	Boolean newCittadinoRegistrato() throws RemoteException;
	
	String[] cittadinoRegistratoServ(String cittadinoRegistrato) throws RemoteException;
	
	Boolean newEventoAvverso() throws RemoteException;
	
	String[] eventoAvversoServ(String eventoAvverso) throws RemoteException;
	
	Boolean newAggregazioneEventi() throws RemoteException;
	
	String[] aggregazioneEventi(String aggregazioneEventi) throws RemoteException;
	
	Boolean updateAggregazioneEventiServ() throws RemoteException;
	
	Boolean updateCentriVaccinaliServ() throws RemoteException;

	
	
	public static void main (String[] args) throws RemoteException {
		try {
			MultiServerImpl server = new MultiServerImpl();
			Registry registro = LocateRegistry.createRegistry(1099);
			registro.rebind("Server", server);
			System.out.println("Server pronto");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

*/
