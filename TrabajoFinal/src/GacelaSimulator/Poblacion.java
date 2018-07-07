package GacelaSimulator;

import java.util.LinkedList;
import java.util.List;
import GacelaSimulator.Gacela;

public class Poblacion {
	private List<Gacela> muertas = new LinkedList<Gacela>();
	private List<Gacela> vivas = new LinkedList<Gacela>();	
	private List<Gacela> hijos = new LinkedList<Gacela>();	
	
	private int cantidadDeGeneraciones;

	public void aumentarCantDeGeneraciones() {
		this.cantidadDeGeneraciones++;
	}
	public int getCantDeGeneraciones() {
		return this.cantidadDeGeneraciones;
	}
	public void killGacela(Gacela gacela, int cause) {
		if(cause>7 || cause<1){
			System.out.println(("Esta causa de muerte no esta contemplada por el programa"));
		}
		vivas.remove(gacela);
		gacela.setDeathCause(cause);
		muertas.add(gacela);
	}
	public void bornGacela(Gacela dad, Gacela mom) {
		Gacela hijo = new Gacela();
		Gacela hijo2 = new Gacela();
		int mid = dad.getSequence().length()/2;
		String ultimapartedad = dad.getSequence().substring(mid);
		String primerapartedad =  dad.getSequence().substring(0, mid);

		String ultimapartemom =  mom.getSequence().substring(mid);
		String primerapartemom =  mom.getSequence().substring(0, mid);
		if(dad.getCualidad() == 6 || mom.getCualidad() == 6) {
		}
		else if(dad.getCualidad() == 7 && mom.getCualidad() == 7) {
			hijo.setSequence(primerapartedad.concat(ultimapartemom));
			hijos.add(hijo);
		}
		else{
			hijo.setSequence(primerapartedad.concat(ultimapartemom));
			hijo2 = primerapartemom.concat(ultimapartedad);
		}
	}

	public int cantVivas() {
		return this.vivas.size();
	}

	public int cantMuertas() {
		return this.muertas.size();
	}

	public void reproduccion() {
		if(getCantDeGeneraciones() == 1) {
			for(int i = 0; i<(vivas.size())*(0.8); i++) {

			}
		}
		for(int i = 0; i<(vivas.size())*(0.8); i++) {

		}
	}
	public void gacelasParaReproduccion() {
		List<Gacela> listRep = new LinkedList<Gacela>();
		List<Gacela> momList = new LinkedList<Gacela>();
		List<Gacela> dadList = new LinkedList<Gacela>();
		listRep = vivas;
		for(int i = 0; i<(listRep.size())*(0.8); i++) {
			int randomMom = Menu.getRandomIntBetween(0, listRep.size()-1);
			momList.add(listRep.get(randomMom));
			listRep.remove(randomMom);
			int randomDad = Menu.getRandomIntBetween(0, listRep.size()-1);
			dadList.add(listRep.get(randomDad));
			listRep.remove(randomDad);
		}

		if(momList.size() == dadList.size()) {
			for(int i = 0; i<momList.size(); i++) {
				bornGacela(dadList.get(i), momList.get(i));
			}

		}
	}
	public void setPoblacionInicial(List<Gacela> list) {
		for (Gacela gacela : list) {
			this.vivas.add(gacela);
		}
	}
}