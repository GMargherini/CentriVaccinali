/*
 * Antonicelli Sandy, 744947, VA
 * Caffi Nicolò, 745391, VA
 * Margherini Giorgio, 744148, VA
 */
package interfaces;

import datamodel.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 */
public interface CentriVaccinaliInt extends Remote {
	/**
	 * Inserisce un nuovo centro vaccinale nel database.
	 * @param centroVaccinale Il centro da inserire.
	 * @return <code>true</code> se l'operazione va a buon fine, <code>false</code> altrimenti.
	 * @throws RemoteException Se è avvenuta un'eccezione nella chiamata remota.
	 */
	Boolean registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException;
	/**
	 * Restituisce una lista dei centri vaccinali con nome contenete <code>nome</code>.
	 * @param nome La stringa cercare.
	 * @return Una <code>ArrayList</code> di <code>CentroVaccinale</code>.
	 * @throws RemoteException Se è avvenuta un'eccezione nella chiamata remota.
	 */
	ArrayList<CentroVaccinale> cercaCentroVaccinale(String nome) throws RemoteException;

	/**
	 * Restituisce una lista dei centri vaccinali con comune uguale a <code>comune</code> e tipo uguale a <code>tipo</code>.
	 * @param comune Il comune del centro
	 * @param tipo il tipo del centro
	 * @return Una <code>ArrayList</code> di <code>CentroVaccinale</code>.
	 * @throws RemoteException Se è avvenuta un'eccezione nella chiamata remota.
	 */
	ArrayList<CentroVaccinale> cercaCentroVaccinale(String comune, String tipo) throws RemoteException;

	/**
	 * Restituisce il centro vaccinale con nome uguale a <code>nome</code> e comune uguale a <code>comune</code>.
	 * @param nome Il nome del centro.
	 * @param comune il comune del centro.
	 * @return Un oggetto di tipo <code>CentroVaccinale</code>.
	 * @throws RemoteException Se è avvenuta un'eccezione nella chiamata remota.
	 */
	CentroVaccinale visualizzaInfoCentroVaccinale(String nome, String comune) throws RemoteException;
	/**
	 * Inserisce un nuovo vaccinato nel database.
	 * @param vaccinato Il vaccinato da inserire.
	 * @return <code>true</code> se l'operazione va a buon fine, <code>false</code> altrimenti.
	 * @throws RemoteException Se è avvenuta un'eccezione nella chiamata remota.
	 */
	Boolean registraVaccinato(Vaccinato vaccinato) throws RemoteException;
	/**
	 * Restituisce il vaccinato con codice identificativo uguale a <code>id</code>.
	 * @param id Il codice univoco del vaccinato
	 * @return Un oggetto di tipo <code>>Vaccinato</code>.
	 * @throws RemoteException Se è avvenuta un'eccezione nella chiamata remota.
	 */
	Vaccinato visualizzaInfoVaccinato(int id) throws RemoteException;

	/**
	 * Inserisce un nuovo cittadino registrato nel database.
	 * @param cittadinoRegistrato Il cittadino da inserire.
	 * @return <code>true</code> se l'operazione va a buon fine, <code>false</code> altrimenti.
	 * @throws RemoteException Se è avvenuta un'eccezione nella chiamata remota.
	 */
	Boolean registraCittadino(CittadinoRegistrato cittadinoRegistrato) throws RemoteException;

	/**
	 * Restituisce il cittadino registrato con username uguale a <code>id</code>.
	 * @param id L'username del cittadino.
	 * @return Un oggetto di tipo <code>CittadinoRegistrato</code>.
	 * @throws RemoteException Se è avvenuta un'eccezione nella chiamata remota.
	 */
	CittadinoRegistrato visualizzaInfoCittadinoRegistrato(String id) throws RemoteException;

	/**
	 * Inserisce un nuovo evento avverso nel database.
	 * @param eventoAvverso L'evento da inserire.
	 * @return <code>true</code> se l'operazione va a buon fine, <code>false</code> altrimenti.
	 * @throws RemoteException Se è avvenuta un'eccezione nella chiamata remota.
	 */
	Boolean newEventoAvverso(EventoAvverso eventoAvverso) throws RemoteException;

	/**
	 * Restituisce una lista di eventi avversi per il vaccinato identificato da <code>id</code>.
	 * @param id Il codice della vaccinazione del vaccinato.
	 * @return Una <code>ArrayList</code> di <code>EventoAvverso</code>.
	 * @throws RemoteException Se è avvenuta un'eccezione nella chiamata remota.
	 */
	ArrayList<EventoAvverso> eventiAvversi(int id) throws RemoteException;

	/**
	 * Restituisce una lista di eventi avversi aggregati per sintomo per il centro vaccinale identificato da <code>nome</code> e <code>comune</code>.
	 * @param nome Il nome del centro vaccinale.
	 * @param comune Il comune del centro vaccinale.
	 * @return Una <code>ArrayList</code> di <code>AggregazioneEventi</code>.
	 * @throws RemoteException Se è avvenuta un'eccezione nella chiamata remota.
	 */
	ArrayList<AggregazioneEventi> aggregazioniEventi (String nome, String comune) throws RemoteException;

	/**
	 * Aggiorna la media delle severità e il numero di segnalazioni delle aggregazioni di eventi.
	 * @return <code>true</code> se l'operazione va a buon fine, <code>false</code> altrimenti.
	 * @throws RemoteException Se è avvenuta un'eccezione nella chiamata remota.
	 */
	Boolean updateAggregazioneEventiServ() throws RemoteException;

	/**
	 * Aggiorna la media delle severità e il numero di segnalazioni dei centri vaccinali.
	 * @return <code>true</code> se l'operazione va a buon fine, <code>false</code> altrimenti.
	 * @throws RemoteException Se è avvenuta un'eccezione nella chiamata remota.
	 */
	Boolean updateCentriVaccinaliServ() throws RemoteException;

}