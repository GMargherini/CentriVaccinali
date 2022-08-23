/*
 * Antonicelli Sandy, 744947, VA
 * Caffi Nicolò, 745391, VA
 * Margherini Giorgio, 744148, VA
 */
package datamodel;

import java.io.Serializable;

public class AggregazioneEventi implements Serializable {

	private final String sintomo;
	private final String nomeCentro;
	private final String comuneCentro;
	private int numeroSegnalazioni;
	private double mediaSeverita;

	/**
	 * Crea un oggetto di tipo <code>AggregazioneEventi</code>.
	 * @param sintomo Il sintomo degli eventi.
	 * @param nome Il nome del centro vaccinale.
	 * @param comune Il comune del centro vaccinale.
	 */
	public AggregazioneEventi( String sintomo, String nome, String comune) {
		this.sintomo=sintomo;
		nomeCentro=nome;
		comuneCentro=comune;
	}
	/**
	 * Crea un oggetto di tipo <code>AggregazioneEventi</code>.
	 * @param sintomo Il sintomo degli eventi.
	 * @param nome Il nome del centro vaccinale.
	 * @param comune Il comune del centro vaccinale.
	 * @param nSeg Il numero delle segnalazioni relative a <code>sintomo</code>.
	 * @param medSev La media delle severità delle segnalazioni.
	 */
	public AggregazioneEventi(String sintomo, String nome, String comune, int nSeg, double medSev) {
		this.sintomo=sintomo;
		nomeCentro=nome;
		comuneCentro=comune;
		numeroSegnalazioni=nSeg;
		mediaSeverita=medSev;
	}

	/**
	 * Crea un array contenente le informazioni dell'oggetto.
	 * @return Un array di <code>String</code>.
	 */
	public String[] toArray(){
		String[] res=new String[5];
		res[0]=sintomo;
		res[1]=nomeCentro;
		res[2]=comuneCentro;
		res[3]=String.valueOf(numeroSegnalazioni);
		res[4]=String.valueOf(mediaSeverita);
		return res;
	}
	
}
