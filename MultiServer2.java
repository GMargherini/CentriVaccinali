import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MultiServer extends Remote {
//  Inserisce un centro vaccinale nel database e ritorna true se l'operazione va a buon fine
//  Boolean insertCentroVaccinale(CentroVaccinale centro);
	String newCentroVaccinale() throws RemoteException;
	
//  Ritorna un ArrayList di centri con "nome" nel nome
//	ArrayList<CentroVaccinale> listCentriVaccinali(String nome);
	String[] listCentriVaccinaliServ() throws RemoteException;

//  Same come prima ma con "comune" e "tipo"
//	ArrayList<CentroVaccinale> listCentriVaccinali(String comune,String tipo);
	String[] listCentriVaccinaliServ() throws RemoteException;

//  Partendo da nome e comune restituisce un oggetto di tipo CentroVaccinale	
//	CentroVaccinale selectCentroVaccinale(String nome,String comune);
	String centroVaccinaleServ(CentroVaccinale centro) throws RemoteException;
	
//  Inserisce un vaccinato nel DB e restituisce true se l'operazione va a buon fine
//	Boolean insertVaccinato(Vaccinato vaccinato);
	String newVaccinato() throws RemoteException;
	
//  Restituisce un oggetto di tipo Vaccinato se esiste nel DB	
//	Vaccinato selectVaccinato(short id) throws RemoteException;
	String vaccinatoServ(Vaccinato vaccinato) throws RemoteException;
	
//  Inserisce un cittadino registrato nel DB e restituisce true se l'operazione va a buon fine	
//	Boolean insertCittadinoRegistrato(CittadinoRegistrato cr);
	String newCittadinoRegistrato() throws RemoteException;
	
//  Ritorna un oggetto di tipo CittadinoRegistrato se esiste	
//	CittadinoRegistrato selectCittadinoRegistrato(String id);
	String cittadinoRegistratoServ(CittadinoRegistrato cittadinoRegistrato) throws RemoteException;
	
//	Boolean insertEventoAvverso(EventoAvverso ea);
	String newEventoAvverso() throws RemoteException;
	
//	EventoAvverso selectEventoAvverso(String sintomo,String id);
	String eventoAvversoServ(EventoAvverso eventoAvverso) throws RemoteException;
	
//	Boolean insertAggregazioneEventi();
	String newAggregazioneEventi() throws RemoteException;
	
//	AggregazioneEventi selectAggregazioneEventi(String sintomo, String nome,String comune);
	String aggregazioneEventi(AggregazioneEventi aggregazioneEventi) throws RemoteException;
	
//	Boolean updateAggregazioniEventi();
	String updateAggregazioneEventiServ() throws RemoteException;
	
//	Boolean updateCentriVaccinali();
	String updateAggregazioneEventiServ() throws RemoteException;
	
}