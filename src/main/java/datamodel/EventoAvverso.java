package datamodel;

import java.io.Serializable;

public class EventoAvverso implements Serializable {
	private final String sintomo;
	private final int idVaccinazione;
	private final int severita;
	private final String note;
	private final String nomeCentro;
	private final String comuneCentro;

	/**
	 * Crea un oggetto di tipo <code>EventoAvverso</code>.
	 * @param sintomo Il sintomo dell'evento.
	 * @param id L'id univoco della vaccinazione dell'utente che segnala l'evento compreso tra 0 e 65536.
	 * @param severita La severità dell'evento, compresa tra 1 e 5.
	 * @param note Note opzionali relative all'evento, massimo 256 caratteri.
	 * @param nome Il nome del centro in cui è stata effettuata la vaccinazione.
	 * @param comune Il comune del centro vaccinale.
	 */
	public EventoAvverso(String sintomo,int id,int severita,String note,String nome,String comune) throws IllegalArgumentException{
		this.sintomo=sintomo;
		idVaccinazione =id;
		this.severita=severita;
		this.note=note;
		nomeCentro=nome;
		comuneCentro=comune;
		if(idVaccinazione<0||idVaccinazione>65536)
			throw new IllegalArgumentException();
		if(severita<1||severita>5)
			throw new IllegalArgumentException();

		if(note.length()>256)
			throw new IllegalArgumentException();


	}
	public String getSintomo() {
		return sintomo;
	}
	public int getIdVaccinazione() {
		return idVaccinazione;
	}
	public int getSeverita() {
		return severita;
	}
	public String getNote() {
		return note;
	}
	public String getNomeCentro() {
		return nomeCentro;
	}
	public String getComuneCentro() {
		return comuneCentro;
	}
	/**
	 * Crea un array contenente le informazioni dell'oggetto.
	 * @return Un array di <code>String</code>.
	 */
	public String[] toArray(){
		String[] res=new String[6];
		res[0]=sintomo;
		res[1]=String.valueOf(idVaccinazione);
		res[2]=String.valueOf(severita);
		res[3]=note;
		res[4]=nomeCentro;
		res[5]=comuneCentro;
		return res;
	}
	
}
