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

	public void aumentarCantDeGeneraciones() {
		this.cantidadDeGeneraciones++;
	}
	public int getCantDeGeneraciones() {
		return this.cantidadDeGeneraciones;
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



	public void insertGeneracion(List<Gacela> Gen) {
		generaciones.push(Gen);
	}

	public int getGeneracion(List<Gacela> gen) {	
		Stack<List<Gacela>> generacionesCopia = generaciones;
		Stack<List<Gacela>> moment = new Stack<>();
		int size = generaciones.size();
		int generacion = 0;
		for(int i = 0 ; i < size; i++) {		
			if( gen != generacionesCopia.peek() ) {
				moment.push(generacionesCopia.pop());
			} else {
				generacion = generacionesCopia.size() - 1;
				return generacion;
			}		
		} 
		return -1;
		//-1 significa que no existe la lista gen,en la pila generaciones 		
	}

	//ACA HICE EL LISTAR VIVAS
	//PRIMERO IMPRIME LA GENERACION Y LUEGO LA LISTA DE LAS GACELAS QUE PERTENECEN A ESA GENERACION
	//LO UNCICO QUE NO SE COMO ARREGLAR, ES COMO HACER PARA QUE NO IMPRIMA LAS GENERACIONES QUE YA SE MURIERON
	//LO UNICO QUE SE ME OCURRIO ES QUE EN EL METODO LISTAR VIVAS, SI ENCUENTA UNA LISTA VACIA 
	//QUE NO LA IMPRIMA,



	public void listarVivas() {

		for(int i = 0; i < generaciones.size(); i++) {

			List<Gacela> lista = generaciones.peek(); 
			System.out.println("la generacion es: " + getGeneracion(lista));

			for(int j = 0 ; j < lista.size(); j++) {

				System.out.println(lista.get(j).getSequence());
			}


		}

	}


	//HASTA ACA LLEGA LO DE LA GENERACION


	public void killGacela(Gacela gacela, int cause) {
		if(cause > 7 || cause < 1){
			System.out.println(("Esta causa de muerte no esta contemplada por el programa"));
		}
		gacela.setDeathCause(cause);
		muertas.add(gacela);
		vivas.remove(gacela);
	}

	public void bornGacela(Gacela dad, Gacela mom,char viejo, char nuevo) {

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
		List<Gacela> listRep = new LinkedList<Gacela>();
		listRep = hijos;
		int aSacar = (int) (listRep.size() * 0.5);
		for(int i = 0; i < aSacar; i++) {
			listRep.remove(GenerarGacelaFile.getRandomIntBetween(0, listRep.size()-1));
		}
		for(Gacela hijo1 : listRep) {
			hijo1.mutar(viejo,nuevo);
		}
		
		for(int i = 0; i < listRep.size(); i++) {
			Gacela gacela = listRep.get(i);
			String s = gacela.getSequence();
			if(s.matches("[A*C*T*G*]*ACGGTAAAC[A*C*T*G*]*")) {//1 comida de leones
				killGacela(gacela, 1);
				muertas.add(gacela);
			}
			else if(s.matches("[A*C*T*G*]*AACACGTTG[A*C*T*G*]*")) {//2 comida de cocos
				killGacela(gacela, 2);
				muertas.add(gacela);
			}
			else if(s.matches("[A*C*T*G*]*GGCTTATGA[A*C*T*G*]*")) {//3 enfermedad
				killGacela(gacela, 3);
				muertas.add(gacela);
			}
			else if(s.matches("[A*C*T*G*]*CTCATGTTA[A*C*T*G*]*")) {//4 hambruna
				killGacela(gacela, 4);
				muertas.add(gacela);
			}
			else if(s.matches("[A*C*T*G*]*ACTTTACGA[A*C*T*G*]*")) {//5 alergia
				killGacela(gacela, 5);
				muertas.add(gacela);
			}
			else if(s.matches("[A*C*T*G*]*CCGATATGT[A*C*T*G*]*")) {//6 esteril
					vivas.add(gacela);
					gacela.setCualidad(6);
			}		
			else if(s.matches("[A*C*T*G*]*GGTTAAACG[A*C*T*G*]*")) {//7 1 hijo
					vivas.add(gacela);
					gacela.setCualidad(7);
					if(mom.getGeneracion()>= dad.getGeneracion()) {
						gacela.setGeneracion(mom.getGeneracion()+1);
					}else {
						gacela.setGeneracion(dad.getGeneracion()+1);
					}
					
			}
			else {
				vivas.add(gacela);
				if(mom.getGeneracion()>= dad.getGeneracion()) {
					gacela.setGeneracion(mom.getGeneracion()+1);
				}else {
					gacela.setGeneracion(dad.getGeneracion()+1);
				}
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
		gacelasParaReproduccion(viejo,  nuevo);
		//ya se reproducen, falta seleccionar al 50 % de los hijos y mutarlos 
		//o sea seleccionar a la mitad y a cada gacela modificarla para volverla a meter en hijos
		
	}


	public void gacelasParaReproduccion(char viejo, char nuevo) { // arma una lista de mamas y papas y hace que tengan hijos
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
				bornGacela(dadList.get(i), momList.get(i), viejo, nuevo);
			}
		}
	}


	public void setPoblacionInicial(List<Gacela> list) {
		vivas = list;
	}
}