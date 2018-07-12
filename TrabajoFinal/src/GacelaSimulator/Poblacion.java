package GacelaSimulator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


import GacelaSimulator.Gacela;

public class Poblacion {
	private List<Gacela> muertas = new LinkedList<Gacela>();
	private List<Gacela> vivas = new LinkedList<Gacela>();	
	private List<Gacela> hijos = new LinkedList<Gacela>();	
	private int cantidadDeGeneraciones;

	public List<Gacela> getMuertas(){
		return this.muertas;
	}
	public void aumentarCantDeGeneraciones() {
		this.cantidadDeGeneraciones++;
	}
	public int getCantDeGeneraciones() {
		return this.cantidadDeGeneraciones;
	}
	public List<Gacela> getVivas(){
		return this.vivas;
	}
	public List<Gacela> getHijos(){
		return this.hijos;
	}	
	public int cantVivas() {
		return this.vivas.size();
	}

	public int cantMuertas() {
		return this.muertas.size();
	}

	public void setPoblacionInicial(List<Gacela> list) {	
		if(list == null) {
			return;
		}	
		vivas = copiarLista(list);
	}

	public void listarVivas() {			// hace la impresion de la opcion 2
		for(Gacela gacelita : ordenarLista(this.vivas)) {	
			System.out.println("La secuencia de la gacela es: " + gacelita.getSequence() + " y su generacion es: " + gacelita.getGeneracion());
		}
		System.out.println();
	}

	public String listaDeVivas(){		// hace la lista para guardar en la opcion 2
		String result = "";
		for(Gacela gacelita : ordenarLista(this.vivas)) {
			result += gacelita.getSequence() + "  " + gacelita.getGeneracion() + "\n";
		}
		return result;
	}

	public String listaDeMuertas() {	// hace la lista para guardar la opcion 3
		HashMap<Integer,String> death = new HashMap<Integer,String>();
		death.put(1,"Comida de leones");  
		death.put(2, "Comida de cocodrilos"); 
		death.put(3, "Enfermedad"); 
		death.put(4, "Hambruna"); 
		death.put(5, "Alergia"); 
		death.put(6, "Vejez"); 
		String result = "";
		for(Gacela gacelita : ordenarLista(this.muertas)) {
			result += gacelita.getSequence() + "  " + death.get(gacelita.getDeathCause()) + "\n";
		}
		return result;
	}

	public List<Gacela> ordenarLista(List<Gacela> list){	// ordena una lista de gacelas por la generacion de las mismas
		List<Gacela> ordenada = new LinkedList<Gacela>();

		if(this.getCantDeGeneraciones() == 0 || this.getCantDeGeneraciones() == 1) {
			return list;
		}

		for(int i = 0; i < this.getCantDeGeneraciones()+1; i++ ) {

			for(Gacela gacelita : list) {
				if(gacelita.getGeneracion() == i) {
					ordenada.add(gacelita);
				}
			}
		}
		return ordenada;
	}

	public void killGacela(Gacela gacela, int cause) { 	 	// mata una gacela y le da un motivo de muerte
		if(cause > 6 || cause < 1){
			System.out.println(("Esta causa de muerte no esta contemplada por el programa"));
		}
		gacela.setDeathCause(cause);
		muertas.add(gacela);
		vivas.remove(gacela);
		if(hijos.contains(gacela)) {
			hijos.remove(gacela);
		}	
	}

	public void bornGacela(Gacela dad, Gacela mom, char viejo, char nuevo) {	// pueden nacer 1 o 2 gacelas
		int mid = dad.getSequence().length()/2;

		String ultimapartedad = dad.getSequence().substring(mid);
		String primerapartedad =  dad.getSequence().substring(0, mid);

		String ultimapartemom =  mom.getSequence().substring(mid);
		String primerapartemom =  mom.getSequence().substring(0, mid);

		if(dad.getCualidad() == 6 || mom.getCualidad() == 6) {		 // algun padre es esteril

		}		
		else if(dad.getCualidad() == 7 && mom.getCualidad() == 7) {  // ambos tienen gen de 1 hijo
			Gacela hijo = new Gacela();
			hijo.setSequence(primerapartedad.concat(ultimapartemom));
			hijo.setGeneracion(this.cantidadDeGeneraciones);
			hijos.add(hijo);
		}
		else if(dad.getCualidad() == 7 || mom.getCualidad() == 7) {  // al menos un padre tiene gen de 1 hijo
			int a = GenerarGacelaFile.getRandomIntBetween(0, 1);
			if(a == 0) {											 // tienen un solo hijo
				Gacela hijo = new Gacela();
				hijo.setSequence(primerapartedad.concat(ultimapartemom));
				hijo.setGeneracion(this.cantidadDeGeneraciones);
				hijos.add(hijo);
			}else {													 // tienen dos hijos
				Gacela hijo = new Gacela();
				Gacela hijo2 = new Gacela();
				hijo.setSequence(primerapartedad.concat(ultimapartemom));
				hijo.setGeneracion(this.cantidadDeGeneraciones);
				hijo2.setSequence(primerapartemom.concat(ultimapartedad));
				hijo2.setGeneracion(this.cantidadDeGeneraciones);
				hijos.add(hijo);
				hijos.add(hijo2);
			}		
		}
		else{														 // tienen 2 hijos
			Gacela hijo = new Gacela();
			Gacela hijo2 = new Gacela();
			hijo.setSequence(primerapartedad.concat(ultimapartemom));
			hijo.setGeneracion(this.cantidadDeGeneraciones);
			hijo2.setSequence(primerapartemom.concat(ultimapartedad));
			hijo2.setGeneracion(this.cantidadDeGeneraciones);
			hijos.add(hijo);
			hijos.add(hijo2);
		}
	}

	public void hijosPorMorir(List<Gacela> lista) {		// ejecuta la muerte de los mas debiles
		for(int i = 0; i < lista.size(); i++) {
			Gacela gacela = lista.get(i);
			String s = gacela.getSequence();
			if(s.matches("[A*C*T*G*]*ACGGTAAAC[A*C*T*G*]*")) {//1 comida de leones
				killGacela(gacela, 1);
			}
			else if(s.matches("[A*C*T*G*]*AACACGTTG[A*C*T*G*]*")) {//2 comida de cocos
				killGacela(gacela, 2);
			}
			else if(s.matches("[A*C*T*G*]*GGCTTATGA[A*C*T*G*]*")) {//3 enfermedad
				killGacela(gacela, 3);
			}
			else if(s.matches("[A*C*T*G*]*CTCATGTTA[A*C*T*G*]*")) {//4 hambruna
				killGacela(gacela, 4);
			}
			else if(s.matches("[A*C*T*G*]*ACTTTACGA[A*C*T*G*]*")) {//5 alergia
				killGacela(gacela, 5);
			}
			else if(s.matches("[A*C*T*G*]*CCGATATGT[A*C*T*G*]*")) {//6 esteril
				vivas.add(gacela);
				hijos.remove(gacela);
				gacela.setCualidad(6);
			}		
			else if(s.matches("[A*C*T*G*]*GGTTAAACG[A*C*T*G*]*")) {//7 1 hijo
				vivas.add(gacela);
				hijos.remove(gacela);
				gacela.setCualidad(7);
			}
			else {
				vivas.add(gacela);
				hijos.remove(gacela);
			}
		}
	}

	public void nuevasGacelas(char viejo, char nuevo) { // muta mitad de los hijos
		List<Gacela> hijosNoMutados = new LinkedList<Gacela>();
		List<Gacela> listRep = new LinkedList<Gacela>();
		listRep = copiarLista(hijos);
		int aSacar = (int) (listRep.size() * 0.5); 		// preparacion para mutar
		for(int i = 0; i < aSacar; i++) {
			hijosNoMutados.add(listRep.remove(GenerarGacelaFile.getRandomIntBetween(0, listRep.size()-1)));
		}
		for(Gacela hijo : listRep) {  					//  mutacion
			System.out.println("Hijo antes de mutar: " + hijo.getSequence());
			hijo.mutar(viejo,nuevo);
		}
		for(Gacela hijo : listRep) {
			System.out.println("Hijo despues de mutar: " + hijo.getSequence());
		}
		hijosPorMorir(hijosNoMutados); 					// muerte de los mas debiles no mutados
		hijosPorMorir(listRep);		   					// muerte de los mas debiles mutados
	}

	public List<Gacela> getTodoMenosUltGen(List<Gacela> lista){		// devuelve las gacelas que pueden morir de vejez
		List<Gacela> newList = new LinkedList<Gacela>();
		for(Gacela gacelita : lista) {
			if(gacelita.getGeneracion() <= cantidadDeGeneraciones-2) {
				newList.add(gacelita);
			}
		}
		return newList;
	}

	public void muertePorVejez() {		// mata gacelas por vejez
		List<Gacela> lista = new LinkedList<Gacela>();
		lista = copiarLista(getTodoMenosUltGen(vivas)); // lista de gacelas viejas
		int aSacar = (int) (lista.size() * 0.1);		// 10% de la lista
		for(int i = 0; i < aSacar ; i++) {
			killGacela(lista.remove(GenerarGacelaFile.getRandomIntBetween(0, lista.size()-1)), 6);
		}
	}

	public void nuevaGeneracion(char viejo, char nuevo) { // reproduccion, mutacion, muerte de los debiles, muerte por vejez
		this.aumentarCantDeGeneraciones();
		gacelasParaReproduccion(viejo, nuevo); 	// mamas y papas tienen hijos
		nuevasGacelas(viejo, nuevo);			// mitad de los hijos mutan y hace la muerte de los mas debiles
		if(cantidadDeGeneraciones>1) {			// muerte por vejez
			muertePorVejez();
		}
	}

	public List<Gacela> ultimasDosGen(List<Gacela> lista) { // obtiene una lista con las ultimas dos gen de vivas
		List<Gacela> list = new LinkedList<Gacela>();
		for(Gacela gacela : lista) {
			if(gacela.getGeneracion() == cantidadDeGeneraciones-2 || gacela.getGeneracion() == cantidadDeGeneraciones-1) {
				list.add(gacela);
			}
		}
		return list;
	}

	public void gacelasParaReproduccion(char viejo, char nuevo) { // arma una lista de mamas y papas y hace que tengan hijos
		List<Gacela> listRep = new LinkedList<Gacela>();
		List<Gacela> momList = new LinkedList<Gacela>();
		List<Gacela> dadList = new LinkedList<Gacela>();
		if(cantidadDeGeneraciones>2) {					// solo las ultimas dos gen tienen hijos
			listRep = copiarLista(ultimasDosGen(vivas));// obtencion de gacelas que pueden reproducirse
		}else {
			listRep = copiarLista(vivas);				// todas son aptas para reproducirse 
		}

		int aSacar = (int) (listRep.size() * 0.2);

		for(int i = 0; i < aSacar; i++) {				// obtencion do 80% de las gacelas que pueden reproducirse
			listRep.remove(GenerarGacelaFile.getRandomIntBetween(0, listRep.size()-1));
		}

		if(listRep.size() % 2 == 1) {
			listRep.remove(GenerarGacelaFile.getRandomIntBetween(0, listRep.size()-1));
		}

		int vecesARepartir = listRep.size()/2;
		for(int i = 0; i < vecesARepartir; i ++) {
			int randomMom = GenerarGacelaFile.getRandomIntBetween(0, listRep.size() - 1);
			momList.add(listRep.get(randomMom));
			listRep.remove(randomMom);

			if(listRep.size()==1) {
				dadList.add(listRep.get(0));
				listRep.remove(0);
			}else{
				int randomDad = GenerarGacelaFile.getRandomIntBetween(0, listRep.size() - 1);
				dadList.add(listRep.get(randomDad));
				listRep.remove(randomDad);
			}
		}
		for(int i = 0; i<momList.size(); i++) {
			bornGacela(dadList.get(i), momList.get(i), viejo, nuevo);
		}
	}

	public List<Gacela> copiarLista(List<Gacela> copia){
		List<Gacela> lista = new LinkedList<Gacela>();
		for(Gacela gacelita : copia) {
			lista.add(gacelita);
		}
		return lista;
	}

}