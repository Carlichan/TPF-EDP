package GacelaSimulator;

import java.util.LinkedList;
import java.util.List;
import GacelaSimulator.Gacela;

public class Poblacion {
	private List<Gacela> muertas = new LinkedList<Gacela>();
	private List<Gacela> vivas = new LinkedList<Gacela>();	
	private int cantidadDeGeneraciones;

	public void aumentarCantDeGeneraciones() {
		this.cantidadDeGeneraciones++;
	}
	public int getCantDeGeneraciones() {
		return this.cantidadDeGeneraciones;
	}
	public void killGacela(Gacela gacela, int cause) {
		if(cause>7 || cause<1){
			throw new IllegalArgumentException("Esta causa de muerte no esta contemplada por el programa");
		}
		vivas.remove(gacela);
		gacela.setDeathCause(cause);
		muertas.add(gacela);
	}
	public Gacela bornGacela(Gacela dad, Gacela mom) {

		int mid = dad.getSequence().length()/2;

		String ultimapartedad = dad.getSequence().substring(mid);
		String primerapartedad =  dad.getSequence().substring(0, mid);

		String ultimapartemom =  mom.getSequence().substring(mid);
		String primerapartemom =  mom.getSequence().substring(0, mid);

//		if(tienen 2 hijos){
//
//			String hijo1 = primerapartedad.concat(ultimapartemom);
//			String hijo2 = primerapartemom.concat(ultimapartedad);
//		}
		if(tiene 1 hijo) {
			Gacela hijo = new Gacela();
			hijo.setSequence(primerapartedad.concat(ultimapartemom));
	}
		return hijo;
	}
	
	public void addGacela(Gacela gacela) {
		vivas.add(gacela);
	}
	public int cantVivas() {
		return this.vivas.size();
	}
	public int cantMuertas() {
		return this.muertas.size();
	}

	public void setPoblacionInicial(List<Gacela> list) {
		for (Gacela gacela : list) {
			this.vivas.add(gacela);
		}
	}
}