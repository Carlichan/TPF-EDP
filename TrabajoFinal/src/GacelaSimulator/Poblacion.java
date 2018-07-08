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
		else if(dad.getCualidad() == 7 || mom.getCualidad() == 7) {
			hijo.setSequence(primerapartedad.concat(ultimapartemom));
			hijos.add(hijo);
		}
		else{
			hijo.setSequence(primerapartedad.concat(ultimapartemom));
			hijo2.setSequence(primerapartemom.concat(ultimapartedad)); 
			hijos.add(hijo);
			hijos.add(hijo2);
		}
	}

	public int cantVivas() {
		return this.vivas.size();
	}

	public int cantMuertas() {
		return this.muertas.size();
	}

	public void reproduccion() {
		gacelasParaReproduccion();
		for(Gacela gacelita : hijos) {
			//mutar(input 1,, input 2);
		}
	}


	public void gacelasParaReproduccion() { // arma una lista de mamas y papas y hace que tengan hijos
		List<Gacela> listRep = new LinkedList<Gacela>();
		List<Gacela> momList = new LinkedList<Gacela>();
		List<Gacela> dadList = new LinkedList<Gacela>();
		listRep = vivas;
		int aSacar = (int) (listRep.size() * 0.2);

		for(int i = 0; i < aSacar; i++) {
			listRep.remove(Menu.getRandomIntBetween(0, listRep.size()-1));
		}

		if(listRep.size() % 2 == 1) {
			listRep.remove(Menu.getRandomIntBetween(0, listRep.size()-1));
		}

		int vecesARepartir = listRep.size() - 1;
		for(int i = 0; i < vecesARepartir; i ++) {
			int randomMom = Menu.getRandomIntBetween(0, vecesARepartir);
			momList.add(listRep.get(randomMom));
			listRep.remove(randomMom);
			int randomDad = Menu.getRandomIntBetween(0, vecesARepartir);
			dadList.add(listRep.get(randomDad));
			listRep.remove(randomDad);

		}
		if(momList.size() == dadList.size()) {
			for(int i = 0; i<momList.size(); i++) {
				bornGacela(dadList.get(i), momList.get(i));
			}
			//		}else if (momList.size() < dadList.size()){
			//			dadList.remove(0);
			//			for(int i = 0; i<momList.size(); i++) {
			//				bornGacela(dadList.get(i), momList.get(i));
			//			}
			//		} else if (momList.size() > dadList.size()){
			//			momList.remove(0);
			//			for(int i = 0; i<momList.size(); i++) {
			//				bornGacela(dadList.get(i), momList.get(i));
			//			}
		}
	}
	public void setPoblacionInicial(List<Gacela> list) {
		for (Gacela gacela : list) {
			this.vivas.add(gacela);
		}
	}
}