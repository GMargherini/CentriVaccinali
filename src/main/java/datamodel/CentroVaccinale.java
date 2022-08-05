package datamodel;

import java.io.Serializable;

public class CentroVaccinale implements Serializable {
	private String nome;
	private String comune;
	private String indirizzo;
	private String tipo;
	private int totaleSegnalazioni;
	private double mediaGenerale;
	
	public CentroVaccinale(String nome,String comune, String indirizzo,String tipo, int segnalazioni, double media ) {
		this.nome=nome;
		this.comune=comune;
		this.indirizzo=indirizzo;
		this.tipo=tipo;
		totaleSegnalazioni=segnalazioni;
		mediaGenerale=media;
	}
	public CentroVaccinale(String nome,String comune) {
		this.nome=nome;
		this.comune=comune;
	}
	public String getNome() {
		return nome;
	}
	public String getComune() {
		return comune;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public String getTipo() {
		return tipo;
	}

	protected void setTotaleSegnalazioni(int totale) {
		totaleSegnalazioni=totale;
	}
	public int getTotaleSegnalazioni() {
		return totaleSegnalazioni;
	}
	
	protected void setMediaGenerale(double media) {
		mediaGenerale=media;
	}
	public double getMediaGenerale() {
		return mediaGenerale;
	}

	public String[] toArray(){
		String[] res=new String[6];
		res[0]=nome;
		res[1]=comune;
		res[2]=indirizzo;
		res[3]=tipo;
		res[4]=String.valueOf(totaleSegnalazioni);
		res[5]=String.valueOf(mediaGenerale);
		return res;
	}
}