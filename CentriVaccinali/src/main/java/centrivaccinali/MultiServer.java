package centrivaccinali;

import datamodel.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MultiServer extends Remote {

	ArrayList<CentroVaccinale> nomeCentriServ(String nome) throws RemoteException;

	ArrayList<CentroVaccinale> comuneTipoCentriServ(String comune, String tipo) throws RemoteException;

	CentroVaccinale centroVaccinaleServ(String nome, String comune) throws RemoteException;

	Vaccinato vaccinatoServ(String id) throws RemoteException;

	Boolean newCittadinoRegistrato(CittadinoRegistrato cittadinoRegistrato) throws RemoteException;

	CittadinoRegistrato cittadinoRegistratoServ(String id) throws RemoteException;

	Boolean newEventoAvverso(EventoAvverso eventoAvverso) throws RemoteException;

	EventoAvverso eventoAvversoServ(String sintomo, String id) throws RemoteException;

	Boolean newAggregazioneEventi() throws RemoteException;

	AggregazioneEventi aggregazioneEventi(String sintomo, String nome, String comune) throws RemoteException;

	Boolean updateAggregazioneEventiServ() throws RemoteException;

	Boolean updateCentriVaccinaliServ() throws RemoteException;

}