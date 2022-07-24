package datamodel;

public class EventoAvverso {
	private String sintomo;
	private short id_vaccinazione;
	private int severita;
	private String note;
	private String nomeCentro;
	private String comuneCentro;
	
	public EventoAvverso(String sintomo,short id,int severita,String note,String nome,String comune) {
		this.sintomo=sintomo;
		id_vaccinazione=id;
		this.severita=severita;
		this.note=note;
		nomeCentro=nome;
		comuneCentro=comune;
	}
	public String getSintomo() {
		return sintomo;
	}
	public short getIdVaccinazione() {
		return id_vaccinazione;
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
	
}
