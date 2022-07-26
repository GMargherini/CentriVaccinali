package datamodel;

import java.io.Serializable;

public class CittadinoRegistrato implements Serializable {
	private short idVaccinazione;
	private String userId;
	private String password;
	private String email;
	
	public CittadinoRegistrato(short id, String user,String pwd, String email) {
		idVaccinazione=id;
		userId=user;
		password=pwd;
		this.email=email;
	}
	
	public short getIdVaccinazione() {
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

	public String[] toArray(){
		String[] res=new String[4];
		res[0]=String.valueOf(idVaccinazione);
		res[1]=userId;
		res[2]=password;
		res[3]=email;
		return res;
	}
}
