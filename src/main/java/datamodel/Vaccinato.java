/*
 * Antonicelli Sandy, 744947, VA
 * Caffi Nicolò, 745391, VA
 * Margherini Giorgio, 744148, VA
 */
package datamodel;
import java.io.Serializable;
import java.util.Date;
public class Vaccinato implements Serializable {
	private final int idVaccinazione;
	private final String codiceFiscale;
	private final String nomeCognome;
	private final String nomeCentro;
	private final String comuneCentro;
	private final Date dataVaccinazione;
	private final String tipoVaccino;

	/**
	 * Crea un oggetto di tipo <code>Vaccinato</code>.
	 * @param id L'id univoco della vaccinazione compreso tra 0 e 65536.
	 * @param cf Il codice fiscale del vaccinato.
	 * @param nomeCognome Nome e cognome del Vaccinato.
	 * @param nomeCentro Il nome del centro vaccinale.
	 * @param comune Il comune del centro vaccinale.
	 * @param data La data della vaccinazione.
	 * @param tipo Il tipo del vaccino.
	 */
	public Vaccinato(int id, String cf, String nomeCognome, String nomeCentro, String comune, Date data, String tipo) {
		idVaccinazione=id;
		codiceFiscale=cf;
		this.nomeCognome=nomeCognome;
		this.nomeCentro=nomeCentro;
		comuneCentro=comune;
		dataVaccinazione=data;
		tipoVaccino=tipo;
		if(idVaccinazione<0||idVaccinazione>65536)
			throw new IllegalArgumentException();
	}
	public int getIdVaccinazione() {
		return idVaccinazione;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public String getNomeCognome() {
		return nomeCognome;
	}
	public String getNomeCentro() {
		return nomeCentro;
	}
	public String getComuneCentro() {
		return comuneCentro;
	}
	public Date getDataVaccinazione() {
		return dataVaccinazione;
	}
	public String getTipoVaccino() {
		return tipoVaccino;
	}
	/**
	 * Crea un array contenente le informazioni dell'oggetto.
	 * @return Un array di <code>String</code>.
	 */
	public String[] toArray(){
		String[] res=new String[7];
		res[0]=String.valueOf(idVaccinazione);
		res[1]=codiceFiscale;
		res[2]=nomeCognome;
		res[3]=nomeCentro;
		res[4]=comuneCentro;
		res[5]=dataVaccinazione.toString();
		res[6]=tipoVaccino;
		return res;
	}
}
