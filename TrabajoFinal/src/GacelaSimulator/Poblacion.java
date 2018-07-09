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
		if(cause > 7 || cause < 1){
			System.out.println(("Esta causa de muerte no esta contemplada por el programa"));
		}
		gacela.setDeathCause(cause);
		muertas.add(gacela);
		vivas.remove(gacela);
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
			//SETIE LA SECUENCIA DE LOS DOS HIJOS Y LAS AGREGE A LA LISTA HIJOS
			// HAY QUE TENER EN CUENTA QUE SI NO TIENEN NINGUN GEN DE LOS SELEC. VAN A TENER 2 HIJOS 

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

	public void reproduccion(char viejo, char nuevo) {
		gacelasParaReproduccion();
		//ya se reproducen, falta seleccionar al 50 % de los hijos y mutarlos 
		//o sea seleccionar a la mitad y a cada gacela modificarla para volverla a meter en hijos
		List<Gacela> listRep = new LinkedList<Gacela>();
		listRep = hijos;
		int aSacar = (int) (listRep.size() * 0.5);
		for(int i = 0; i < aSacar; i++) {
			listRep.remove(GenerarGacelaFile.getRandomIntBetween(0, listRep.size()-1));
		}
		for(Gacela hijo : listRep) {
			hijo.mutar(viejo,nuevo);
		}
	}


	public void gacelasParaReproduccion() { // arma una lista de mamas y papas y hace que tengan hijos
		List<Gacela> listRep = new LinkedList<Gacela>();
		List<Gacela> momList = new LinkedList<Gacela>();
		List<Gacela> dadList = new LinkedList<Gacela>();
		listRep = vivas;
		int aSacar = (int) (listRep.size() * 0.2);

		for(int i = 0; i < aSacar; i++) {
			listRep.remove(GenerarGacelaFile.getRandomIntBetween(0, listRep.size()-1));
		}

		if(listRep.size() % 2 == 1) {
			listRep.remove(GenerarGacelaFile.getRandomIntBetween(0, listRep.size()-1));
		}

		int vecesARepartir = listRep.size() - 1;
		for(int i = 0; i < vecesARepartir; i ++) {
			if(listRep.size()==1) {
				momList.add(listRep.get(0));
				listRep.remove(0);
			}else if(listRep.size()>1) {
				int randomMom = GenerarGacelaFile.getRandomIntBetween(0, listRep.size() - 1);
				momList.add(listRep.get(randomMom));
				listRep.remove(randomMom);
			}
			if(listRep.size()==1) {
				dadList.add(listRep.get(0));
				listRep.remove(0);
			}else if(listRep.size()>1){
				int randomDad = GenerarGacelaFile.getRandomIntBetween(0, listRep.size() - 1);
				dadList.add(listRep.get(randomDad));
				listRep.remove(randomDad);
			}
		}
		if(momList.size() == dadList.size()) {
			for(int i = 0; i<momList.size(); i++) {
				bornGacela(dadList.get(i), momList.get(i));
			}
		}
	}


	public void setPoblacionInicial(List<Gacela> list) {
		vivas = list;
	}
}