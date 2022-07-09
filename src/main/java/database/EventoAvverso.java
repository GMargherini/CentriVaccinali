package database;

public class EventoAvverso {
	private String sintomo;
	private String user_id;
	private int severita;
	private String note;
	private String nomeCentro;
	private String comuneCentro;
	
	public EventoAvverso(String sintomo,String id,int severita,String note,String nome,String comune) {
		this.sintomo=sintomo;
		user_id=id;
		this.severita=severita;
		this.note=note;
		nomeCentro=nome;
		comuneCentro=comune;
	}
	public String getSintomo() {
		return sintomo;
	}
	public String getUserId() {
		return user_id;
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
