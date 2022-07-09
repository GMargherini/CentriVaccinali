package database;

public class CittadinoRegistrato {
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
	protected String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
}
