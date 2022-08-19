package datamodel;

import java.io.Serializable;

public class CittadinoRegistrato implements Serializable {
	private final int idVaccinazione;
	private final String userId;
	private final String password;
	private final String email;

	/**
	 * Crea un oggetto di tipo <code>CittadinoRegistrato</code>.
	 * @param id L'id univoco della vaccinazione compreso tra 0 e 65536.
	 * @param user L'username dell'utente.
	 * @param pwd La password dell'utente.
	 * @param email L'indirizzo e-mail dell'utente.
	 */
	public CittadinoRegistrato(int id, String user,String pwd, String email) throws IllegalArgumentException{
		idVaccinazione=id;
		userId=user;
		password=pwd;
		this.email=email;
		if(idVaccinazione<0||idVaccinazione>65536)
			throw new IllegalArgumentException();

	}

	public int getIdVaccinazione() {
		return idVaccinazione;
	}
	public String getUserId() {
		return userId;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	/**
	 * Crea un array contenente le informazioni dell'oggetto.
	 * @return Un array di <code>String</code>.
	 */
	public String[] toArray(){
		String[] res=new String[4];
		res[0]=String.valueOf(idVaccinazione);
		res[1]=userId;
		res[2]=password;
		res[3]=email;
		return res;
	}
}
