package GacelaSimulator;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import GacelaSimulator.Gacela;

public class Poblacion {
	private List<Gacela> muertas = new LinkedList<Gacela>();
	private List<Gacela> vivas = new LinkedList<Gacela>();	
	private List<Gacela> hijos = new LinkedList<Gacela>();	
	private int cantidadDeGeneraciones;
	private Stack<List<Gacela>> generaciones = new Stack<>();

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

	//PENSE COMO HACER LO DE LAS GENERACIONES, 
	//TENIENDO EN CUENTA NECESITAMOS UNA ESTRUCTURA QUE LAS ALMACENE 
	//PENSE EN PONER LAS LISTAS EN UNA PILA "GENERACIONES" Y QUE LAS VAYA ALMACENANDO 
	//ASI EN NUESTRO CASO BASE, PRIMERO ALMACENAMOS LAS VIVAS, QUE SERIA NUESTRA GENERACION 0 
	//Y DESPUES SIEMPRE 
	// AGREGAMOS LA LISTA HIJAS CUANDO TERMINA LA PARTE DE REPRODUCCION, SIENDO
	// ESTA LA NUEVA GENERACION 1,2,3,...
	//DESPUES HICE UN METODO QUE DEVUELVE LA GENERACION, O SEA, LA POSICION EN LA PILA 
	//(ESTA PROBADO Y FUNCIONA) Y OTRO QUE INSERTA LA LISTA EN LA PILA 
	//UN PROBLEMA QUE SE ME OCURRE ES QUE, LA PILA DE GENERACIONES SE VA A TENER QUE MANTENER EN EL TIEMPO
	//MIENTRAS QUE LAS LISTAS VIVAS Y HIJOS SE MODIFICAN CONSTANTEMENTE
	//
	// DECIME QUE TE PARECE  Y SINO PENSAMOS OTRA COSA


	//
	//	public void insertGeneracion(List<Gacela> Gen) {
	//		generaciones.push(Gen);
	//	}
	//
	//	public int getGeneracion(List<Gacela> gen) {	
	//		Stack<List<Gacela>> generacionesCopia = generaciones;
	//		Stack<List<Gacela>> moment = new Stack<>();
	//		int size = generaciones.size();
	//		int generacion = 0;
	//		for(int i = 0 ; i < size; i++) {		
	//			if( gen != generacionesCopia.peek() ) {
	//				moment.push(generacionesCopia.pop());
	//			} else {
	//				generacion = generacionesCopia.size() - 1;
	//				return generacion;
	//			}		
	//		} 
	//		return -1;
	//-1 significa que no existe la lista gen,en la pila generaciones 		
	//}

	//ACA HICE EL LISTAR VIVAS
	//PRIMERO IMPRIME LA GENERACION Y LUEGO LA LISTA DE LAS GACELAS QUE PERTENECEN A ESA GENERACION
	//LO UNCICO QUE NO SE COMO ARREGLAR, ES COMO HACER PARA QUE NO IMPRIMA LAS GENERACIONES QUE YA SE MURIERON
	//LO UNICO QUE SE ME OCURRIO ES QUE EN EL METODO LISTAR VIVAS, SI ENCUENTA UNA LISTA VACIA 
	//QUE NO LA IMPRIMA,



	//	public void listarVivas1() {
	//
	//		for(int i = 0; i < generaciones.size(); i++) {
	//
	//			List<Gacela> lista = generaciones.peek(); 
	//			System.out.println("la generacion es: " + getGeneracion(lista));
	//
	//			for(int j = 0 ; j < lista.size(); j++) {
	//
	//				System.out.println(lista.get(j).getSequence());
	//			}
	//
	//
	//		}
	//
	//	}
	//

	//HASTA ACA LLEGA LO DE LA GENERACION



	public void listarVivas() {
		for(Gacela gacelita : ordenarLista(this.vivas)) {	
			System.out.println("La secuencia de la gacela es: " + gacelita.getSequence() + " y su generacion es: " + gacelita.getGeneracion());
			System.out.println("su cualidad es " + gacelita.getCualidad());
		}
		System.out.println();
	}

	public List<Gacela> ordenarLista(List<Gacela> list){
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

	public List<Gacela> getHijos(){
		return this.hijos;
	}

	public void killGacela(Gacela gacela, int cause) {
		if(cause > 7 || cause < 1){
			System.out.println(("Esta causa de muerte no esta contemplada por el programa"));
		}
		gacela.setDeathCause(cause);
		muertas.add(gacela);
		vivas.remove(gacela);
		if(hijos.contains(gacela)) {
			hijos.remove(gacela);
		}	
	}

	public void bornGacela(Gacela dad, Gacela mom, char viejo, char nuevo) {
		int mid = dad.getSequence().length()/2;

		String ultimapartedad = dad.getSequence().substring(mid);
		String primerapartedad =  dad.getSequence().substring(0, mid);

		String ultimapartemom =  mom.getSequence().substring(mid);
		String primerapartemom =  mom.getSequence().substring(0, mid);

		if(dad.getCualidad() == 6 || mom.getCualidad() == 6) {

		}
		else if(dad.getCualidad() == 7 || mom.getCualidad() == 7) {
			Gacela hijo = new Gacela();
			hijo.setSequence(primerapartedad.concat(ultimapartemom));
			hijo.setGeneracion(this.cantidadDeGeneraciones);
			hijos.add(hijo);
		}
		else{
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

	public void hijosPorMorir(List<Gacela> lista) {
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
	public int cantVivas() {
		return this.vivas.size();
	}

	public int cantMuertas() {
		return this.muertas.size();
	}

	public void reproduccion(char viejo, char nuevo) {
		this.aumentarCantDeGeneraciones();
		gacelasParaReproduccion(viejo,  nuevo);

		List<Gacela> hijosNoMutados = new LinkedList<Gacela>();
		List<Gacela> listRep = new LinkedList<Gacela>();
		listRep = copiarLista(hijos);
		int aSacar = (int) (listRep.size() * 0.5); // preparacion para mutar
		for(int i = 0; i < aSacar; i++) {
			hijosNoMutados.add(listRep.remove(GenerarGacelaFile.getRandomIntBetween(0, listRep.size()-1)));
		}
		for(Gacela hijo1 : listRep) {
			hijo1.mutar(viejo,nuevo);
		}
		hijosPorMorir(hijosNoMutados);
		hijosPorMorir(listRep);
	}
	public List<Gacela> copiarLista(List<Gacela> copia){
		List<Gacela> lista = new LinkedList<Gacela>();
		for(Gacela gacelita : copia) {
			lista.add(gacelita);
		}
		return lista;
	}

	public void gacelasParaReproduccion(char viejo, char nuevo) { // arma una lista de mamas y papas y hace que tengan hijos
		List<Gacela> listRep = new LinkedList<Gacela>();
		List<Gacela> momList = new LinkedList<Gacela>();
		List<Gacela> dadList = new LinkedList<Gacela>();
		listRep = copiarLista(vivas);
		int aSacar = (int) (listRep.size() * 0.2);

		for(int i = 0; i < aSacar; i++) {
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

	public void setPoblacionInicial(List<Gacela> list) {
		vivas = copiarLista(list);
	}

	public static void main(String[] args) {
		Poblacion a = new Poblacion();
		Gacela gacela = new Gacela();
		Gacela gacelamama = new Gacela();
		gacela.setSequence("aaaaaaaaaa");
		gacelamama.setSequence("tttttttttt");
		a.vivas.add(gacela);
		a.vivas.add(gacelamama);
		System.out.println(a.getVivas());
		char viejo ='a';
		char nuevo = 'g';
		a.reproduccion(viejo, nuevo);
		System.out.println(a.getHijos());
		System.out.println(a.getVivas());

	}
}