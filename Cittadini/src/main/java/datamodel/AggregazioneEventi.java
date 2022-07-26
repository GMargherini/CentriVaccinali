package datamodel;

public class AggregazioneEventi {
	private String sintomo;
	private String nomeCentro;
	private String comuneCentro;
	private int numeroSegnalazioni;
	private double mediaSeverita;
	
	public AggregazioneEventi( String sintomo, String nome, String comune) {
		this.sintomo=sintomo;
		nomeCentro=nome;
		comuneCentro=comune;
	}
	public AggregazioneEventi(String sintomo, String nome, String comune, int nSeg, double medSev) {
		this.sintomo=sintomo;
		nomeCentro=nome;
		comuneCentro=comune;
		numeroSegnalazioni=nSeg;
		mediaSeverita=medSev;
	}

	public String[] toArray(){
		String[] res=new String[5];
		res[0]=sintomo;
		res[1]=nomeCentro;
		res[2]=comuneCentro;
		res[3]=String.valueOf(numeroSegnalazioni);
		res[4]=String.valueOf(mediaSeverita);
		return res;
	}
	
}
