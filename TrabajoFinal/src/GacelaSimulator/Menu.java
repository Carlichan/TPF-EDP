package GacelaSimulator;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import GacelaSimulator.Poblacion;

public class Menu {

	public static void main(String[] args) throws IOException {
		Poblacion pob = new Poblacion();
		Scanner scanner = new Scanner(System.in); 
		drawBienvenida();
		pob.setPoblacionInicial(GacelaReader.createGacelas(GacelaReader.readFile(getInput(scanner))));
		inicializacion(scanner);

		while(InputDoer(getInput(scanner), pob, scanner)) { //Ciclo principal del programa
			
			drawMenu();			
		}

		finalizacion(scanner);

	}
	public static void siguienteGeneracion(Scanner scanner, Poblacion pob) {
		System.out.println("Por favor ingrese base/s a modificar");
		String viejo = getInput(scanner);
		System.out.println("Por favor ingrese nueva/s base/s");
		String nuevo = getInput(scanner);
		pob.reproduccion();
	}

	public static int getRandomIntBetween(int min, int max) {
		if (max < min) {
			throw new IllegalArgumentException();
		}
		return (int) Math.floor(Math.random()*(max-min+1)+min);
	}
	public static void option1(Scanner scanner, Poblacion pob) {
		siguienteGeneracion(scanner, pob);
	}

	public static void option2(String input) {
	}

	public static void option3(List<Gacela> gacelasMuertas, Poblacion pob) {
		HashMap<Integer,String> deathCauses = new HashMap<Integer,String>();
		deathCauses.put(1, "Comida de leones");
		deathCauses.put(2, "Comida de cocodrilos");
		deathCauses.put(3, "Enfermedad");
		deathCauses.put(4, "Hambruna");
		deathCauses.put(5, "Alergia");
		deathCauses.put(6, "Mutacion");
		deathCauses.put(7, "Vejez");

		for (Gacela gacela : gacelasMuertas) {
			System.out.println("La cantidad de gacelas muertas es: " + gacelasMuertas.size());
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
			else if(gacela.getDeathCause() == 7) {
				System.out.println(deathCauses.get(7));
			}
			else if(gacela.getDeathCause() == 8) {
				System.out.println(deathCauses.get(8));
			}
		}
	}

	public static void option4() {
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
	
	public static boolean InputDoer(String opcion, Poblacion pob, Scanner scanner) {
		if (opcion.equalsIgnoreCase("1")) {
			option1(scanner, pob);
			return true;
		} 
		else if (opcion.equalsIgnoreCase("2")) {
			//	listar vivas por generacion
			return true;
		} 
		else if (opcion.equalsIgnoreCase("3")) {
			//option3(, pob);
			return true;
		} 
		else if (opcion.equalsIgnoreCase("q")) {
			return false;
		} 
		else {
			System.out.println("No ingreso una opcion correcta");
			return true;
		}
	}

}
