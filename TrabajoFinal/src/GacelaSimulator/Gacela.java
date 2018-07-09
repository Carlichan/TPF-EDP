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

	public void mutar(char viejo , char nuevo) { //por ejemplo cambia a por g y g por a		
				String sec = this.getSequence();
				sec = sec.replace(viejo, 'z');
				sec = sec.replace(nuevo, 'y');	
				sec =sec.replace('z', nuevo);
				sec =sec.replace('y', viejo);
				this.setSequence(sec);	
	}
	
	public static void main(String[] args) {
		String a = "aga";
		Gacela g = new Gacela();
		g.setSequence(a);
		g.mutar('t', 'c');
		System.out.println(g.getSequence());
		
	}

}
