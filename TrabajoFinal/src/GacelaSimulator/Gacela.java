package GacelaSimulator;

import java.util.HashMap;

public class Gacela {
	private String sequence;
	private int cualidad;
	private int generacion;
	private int deathCause;
	
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
}
