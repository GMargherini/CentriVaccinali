package cittadini;

import gui.Gui;
import datamodel.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Cittadini  {
    private MultiServer server;
	private Gui gui;
    private CittadinoRegistrato utente;
    private Boolean logged=false;

    public void setUtente(CittadinoRegistrato user){
        utente=user ;
        logged=true;
    }
    public CittadinoRegistrato getUtente(){
        return utente;
    }
	
    public static void main (String[] args){  
    	new Cittadini().exec();
    }
    public void exec(){
        Gui gui=new Gui(this);
    }
    public ArrayList<CentroVaccinale> cercaCentroVaccinale(String nome){
        try {
            return server.nomeCentriServ(nome);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<CentroVaccinale> cercaCentroVaccinale(String comune, String tipo){
        try {
            return server.comuneTipoCentriServ(comune,tipo);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public CentroVaccinale visualizzaInfoCentroVaccinale(String nome, String comune ){
        try {
            return server.centroVaccinaleServ(nome,comune);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public Boolean registraCittadino(String email, String user, String password, short idVaccinazione){
        CittadinoRegistrato cittadino=new CittadinoRegistrato(idVaccinazione,user,password,email);
        try {
            return server.newCittadinoRegistrato(cittadino);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean inserisciEventoAvverso(String sintomo,int severita, String note,String nome, String comune){
        EventoAvverso evento=new EventoAvverso(sintomo,utente.getIdVaccinazione(),severita,note,nome,comune);
        try {
            return server.newEventoAvverso(evento);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public  CittadinoRegistrato visualizzaInfoUtente(String userId){
        try {
            return server.cittadinoRegistratoServ(userId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public  Vaccinato visualizzaInfoVaccinato(String idVaccinazione){
        try {
            return server.vaccinatoServ(idVaccinazione);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public Boolean isLoggedIn(){
        return logged;
    }
    public void setLogged(Boolean isLogged){
        logged=isLogged;
    }
}
