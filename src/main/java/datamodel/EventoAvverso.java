package datamodel;

import java.io.Serializable;

public class EventoAvverso implements Serializable {
	private String sintomo;
	private short idVaccinazione;
	private int severita;
	private String note;
	private String nomeCentro;
	private String comuneCentro;
	
	public EventoAvverso(String sintomo,short id,int severita,String note,String nome,String comune) {
		this.sintomo=sintomo;
		idVaccinazione =id;
		this.severita=severita;
		this.note=note;
		nomeCentro=nome;
		comuneCentro=comune;
	}
	public String getSintomo() {
		return sintomo;
	}
	public short getIdVaccinazione() {
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
