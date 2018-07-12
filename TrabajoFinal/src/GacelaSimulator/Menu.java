package GacelaSimulator;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import GacelaSimulator.Poblacion;

public class Menu {

	public static void main(String[] args) throws IOException {
		GacelaReader gr = new GacelaReader();
		Poblacion pob = new Poblacion();
		Scanner scanner = new Scanner(System.in); 
		
		drawBienvenida();
		
		while(!gr.getGoodPath()) {
			pob.setPoblacionInicial(gr.createGacelas(gr.readFile(getInput(scanner)), pob));
		}
		
		inicializacion(scanner);

		while(InputDoer(getInput(scanner), pob, scanner)) { //Ciclo principal del programa
			drawMenu();			
		}

		finalizacion(scanner);

	}

	public static void nuevaGen(Scanner scanner, Poblacion pob) {
		System.out.println("Por favor ingrese base a modificar"); 
		String viejo = getInput(scanner);
		if(viejo.matches("(A|C|G|T|a|c|g|t)")) {
			System.out.println("Por favor ingrese nueva base");
			String nuevo = getInput(scanner);
			if(nuevo.matches("(A|C|G|T|a|g|c|t)")) {
				pob.nuevaGeneracion(viejo.toUpperCase().charAt(0), nuevo.toUpperCase().charAt(0));
			} else {
				System.out.println("Debe ingresar una de las 4 bases nitrogenadas");
				System.out.println("Ingrese cualquier cosa para volver al menu");
				scanner.next();
			}	
		} else {
			System.out.println("Debe ingresar una de las 4 bases nitrogenadas");
			System.out.println("Ingrese cualquier cosa para volver al menu");
			scanner.next();
		}		
	}

	public static void listarMuertas(List<Gacela> gacelasMuertas, Poblacion pob) {
		HashMap<Integer,String> deathCauses = new HashMap<Integer,String>();	
		deathCauses.put(1, "Comida de leones");
		deathCauses.put(2, "Comida de cocodrilos");
		deathCauses.put(3, "Enfermedad");
		deathCauses.put(4, "Hambruna");
		deathCauses.put(5, "Alergia");
		deathCauses.put(6, "Vejez");
		System.out.println("La cantidad de gacelas muertas es: " + gacelasMuertas.size());	
		if(gacelasMuertas.size() == 0) {
			System.out.println();
		}
		else {
			System.out.println("Las causas de muerte son: ");
			for (Gacela gacela : gacelasMuertas) {		
				if(gacela.getDeathCause() == 1) {
					System.out.println(deathCauses.get(1));
				}
				else if(gacela.getDeathCause() == 2) {
					System.out.println(deathCauses.get(2));
				}
				else if(gacela.getDeathCause() == 3) {
					System.out.println(deathCauses.get(3));
				}
				else if(gacela.getDeathCause() == 4) {
					System.out.println(deathCauses.get(4));
				}
				else if(gacela.getDeathCause() == 5) {
					System.out.println(deathCauses.get(5));
				}
				else if(gacela.getDeathCause() == 6) {
					System.out.println(deathCauses.get(6));
				}
				else  {

				}
			}
		}
	}

	public static void drawBienvenida() {
		System.out.println("Bienvenido a Gacela Simulator"); 
		System.out.println("Por favor ingrese el path a su archivo.txt");
	}
	
	public static void drawMenu() {
		System.out.println("\tMenu del programa:");
		System.out.println("\tIngrese 1 para simular una nueva generacion de gacelas");
		System.out.println("\tIngrese 2 para listar las gacelas aun vivas, clasificadas por generacion");
		System.out.println("\tIngrese 3 para listar las gacelas muertas, clasificadas por motivo de muerte");
		System.out.println("\tIngrese Q para terminar la simulacion");
	}
	
	public static void inicializacion(Scanner sc) {	
		sc.useDelimiter(Pattern.compile(System.getProperty("line.separator")));
		drawMenu();
	}
	
	public static void finalizacion(Scanner sc) {
		System.out.println("Gracias por utilizar este programa!");
		System.out.println("Hasta Luego!");
		sc.close();
	}
	
	public static String getInput(Scanner sc) {
		return sc.next(); 
	}

	public static boolean InputDoer(String opcion, Poblacion pob, Scanner scanner) throws IOException {
		if (opcion.equalsIgnoreCase("1")) {
			nuevaGen(scanner, pob);
			return true;
		} 
		else if (opcion.equalsIgnoreCase("2")) {		
			pob.listarVivas();
			guardar(scanner, pob);
			return true;
		} 
		else if (opcion.equalsIgnoreCase("3")) {
			listarMuertas(pob.getMuertas(), pob);
			if(pob.getMuertas().size() == 0) {
				return true;
			}
			guardarMuertas(scanner, pob);
			return true;
		} 
		else if (opcion.equalsIgnoreCase("q")) {
			return false;
		} 
		else {
			System.out.println("No ingreso una opcion correcta");
			System.out.println("Ingrese cualquier cosa para volver al menu");
			scanner.next();
			
			return true;
		}
	}

	private static void guardarMuertas(Scanner scanner, Poblacion pob) throws IOException {
		System.out.println("Si desea guardar este listado, ingrese G, sino ingrese cualquier otra cosa para continuar.");
		String rta = scanner.next();
		if(rta.matches("(G|g)")) {
			System.out.println("Ingrese el path de destino.");
			rta = scanner.next();
			GenerarGacelaFile.writeToFile(rta, pob.listaDeMuertas());
		}else {
		}
	}

	private static void guardar(Scanner scanner, Poblacion pob) throws IOException {
		System.out.println("Si desea guardar este listado, ingrese G, sino ingrese cualquier otra cosa para continuar.");
		String rta = scanner.next();
		if(rta.matches("(G|g)")) {
			System.out.println("Ingrese el path de destino.");
			rta = scanner.next();			
			GenerarGacelaFile.writeToFile(rta, pob.listaDeVivas());
		}else {
		}
	}
}
