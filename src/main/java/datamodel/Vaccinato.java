package datamodel;
import java.io.Serializable;
import java.util.Date;
public class Vaccinato implements Serializable {
	private short idVaccinazione;
	private String codiceFiscale;
	private String nomeCognome;
	private String nomeCentro;
	private String comuneCentro;
	private Date dataVaccinazione;
	private String tipoVaccino;
	
	public Vaccinato(short id,String cf, String nomeCognome,String nomeCentro,String comune, Date data, String tipo) {
		idVaccinazione=id;
		codiceFiscale=cf;
		this.nomeCognome=nomeCognome;
		this.nomeCentro=nomeCentro;
		comuneCentro=comune;
		dataVaccinazione=data;
		tipoVaccino=tipo;
	}
	public Vaccinato() {
	}
	public short getIdVaccinazione() {
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
