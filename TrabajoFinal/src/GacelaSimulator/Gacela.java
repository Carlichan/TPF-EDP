package GacelaSimulator;

import java.util.HashMap;

public class Gacela {
	private String sequence;
	private int cualidad;
	private int generacion;
	private int deathCause;

	public void deathCauses() {
		HashMap<Integer,String> fixedSequence = new HashMap<Integer,String>();
		fixedSequence.put(1,"ACGGTAAAC");  //comida leones
		fixedSequence.put(2, "AACACGTTG"); // comida cocos
		fixedSequence.put(3, "GGCTTATGA"); // enfermedad
		fixedSequence.put(4, "CTCATGTTA"); // hambruna
		fixedSequence.put(5, "ACTTTACGA"); // alergia
		fixedSequence.put(6, "CCGATATGT"); // esteril
		fixedSequence.put(7, "GGTTAAACG"); // 1 hijo
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public void setCualidad(int cualidad) {
		this.cualidad = cualidad;
	}
	public void setGeneracion(int generacion) {
		this.generacion = generacion;
	}
	public String getSequence() {
		return this.sequence;
	}
	public int getGeneracion() {
		return this.generacion;
	}
	public int getCualidad() {
		return this.cualidad;
	}
	public void setDeathCause(int cause) {
		this.deathCause = cause;
	}
	public int getDeathCause() {
		return this.deathCause;
	}

	public Gacela mutacion(String viejo , String nuevo , Gacela gacela) {
		if(nuevo.matches("[A*C*G*T*]*")) {
			if (gacela.getSequence().contains(viejo)) {		
				gacela.getSequence().replaceAll(viejo, nuevo);		
			}
		}
		return gacela;
	}
}
