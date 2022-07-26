import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MultiServer extends Remote {

	Boolean newCentroVaccinale() throws RemoteException;
	
	String[][] nomeCentriServ() throws RemoteException;

	String[][] comuneTipoCentriServ() throws RemoteException;

	String[] centroVaccinaleServ(CentroVaccinale centro) throws RemoteException;
	
	Boolean newVaccinato() throws RemoteException;
	
	String[] vaccinatoServ(Vaccinato vaccinato) throws RemoteException;
	
	Boolean newCittadinoRegistrato() throws RemoteException;
	
	String[] cittadinoRegistratoServ(CittadinoRegistrato cittadinoRegistrato) throws RemoteException;
	
	Boolean newEventoAvverso() throws RemoteException;
	
	String[] eventoAvversoServ(EventoAvverso eventoAvverso) throws RemoteException;
	
	Boolean newAggregazioneEventi() throws RemoteException;
	
	String[] aggregazioneEventi(AggregazioneEventi aggregazioneEventi) throws RemoteException;
	
	Boolean updateAggregazioneEventiServ() throws RemoteException;
	
	Boolean updateCentriVaccinaliServ() throws RemoteException;
	
}