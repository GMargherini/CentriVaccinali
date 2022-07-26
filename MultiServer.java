import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MultiServer extends Remote {

	Boolean newCentroVaccinale() throws RemoteException;
	
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
	
}
