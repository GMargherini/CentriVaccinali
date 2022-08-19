package datamodel;

import java.io.Serializable;

public class CentroVaccinale implements Serializable {
	private final String nome;
	private final String comune;
	private String indirizzo;
	private String tipo;
	private int totaleSegnalazioni;
	private double mediaGenerale;

	/**
	 * Crea un oggetto di tipo <code>CentroVaccinale</code>.
	 * @param nome Il nome del centro.
	 * @param comune Il comune del centro.
	 * @param indirizzo L'indirizzo del centro.
	 * @param tipo La tipologia del centro.
	 * @param segnalazioni Il numero di segnalazioni totale per il centro.
	 * @param media La media delle severità delle segnalazioni.
	 */
	public CentroVaccinale(String nome,String comune, String indirizzo,String tipo, int segnalazioni, double media ) {
		this.nome=nome;
		this.comune=comune;
		this.indirizzo=indirizzo;
		this.tipo=tipo;
		totaleSegnalazioni=segnalazioni;
		mediaGenerale=media;
	}
	/**
	 * Crea un oggetto di tipo <code>CentroVaccinale</code>.
	 * @param nome Il nome del centro.
	 * @param comune Il comune del centro.
	 */
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
	public String getTipo() {return tipo;}
	public int getTotaleSegnalazioni() {
		return totaleSegnalazioni;
	}
	public double getMediaGenerale() {
		return mediaGenerale;
	}
	/**
	 * Crea un array contenente le informazioni dell'oggetto.
	 * @return Un array di <code>String</code>.
	 */
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