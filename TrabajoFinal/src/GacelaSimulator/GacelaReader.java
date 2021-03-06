package GacelaSimulator;

import java.io.BufferedReader;
import GacelaSimulator.Gacela;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GacelaReader {
	public static void main(String[] args) throws IOException {
		String text = readFile("C:\\Users\\User\\Desktop\\GacelaFile.txt");
		System.out.println("el string es: " + text);
		System.out.println(createGacelas(text));
	}
	public static List<Gacela> createGacelas(String text) {
		String[] sequence = text.split("\r\n");
		List<Gacela> gacelaList = new LinkedList<Gacela>();

		//HAY QUE VOLVER A VER EL ALGORITMO XQ SI ES ESTERIL O TIENE UN HIJO 
		//PUED LLEGAR A TENER  2 CUALIDADES 
		// ENTONCES HAY QUE VER SI EL SET LO ALMACENAMOS EN UNA
		//LITA O ALGO ASI, XQ SINO SE VAN A SOBREESCRIBIR LOS GENES

		//ACL: ESTE ALG. SOLO SE TIENE EN CUENTA UNA CAUSA DE MUERTE A PESAR DE QUE LA GACELA 
		//PUDE LLEGAR A TENER VARIAS
		for(int j = 0; j < sequence.length ; j++ ) {

			Gacela gacela = new Gacela();
			String s = sequence[j];

			if(s.matches("[A*C*T*G*]*ACGGTAAAC[A*C*T*G*]*")) {//1 comida de leones
				gacela.setSequence(sequence[j]);
				gacela.setCualidad(1);
				gacelaList.add(gacela);
			}
			else if(s.matches("[A*C*T*G*]*AACACGTTG[A*C*T*G*]*")) {//2 comida de cocos
				gacela.setSequence(sequence[j]);
				gacela.setCualidad(2);
				gacelaList.add(gacela);
			}
			else if(s.matches("[A*C*T*G*]*GGCTTATGA[A*C*T*G*]*")) {//3 enfermedad
				gacela.setSequence(sequence[j]);
				gacela.setCualidad(3);
				gacelaList.add(gacela);
			}
			else if(s.matches("[A*C*T*G*]*CTCATGTTA[A*C*T*G*]*")) {//4 hambruna
				gacela.setSequence(sequence[j]);
				gacela.setCualidad(4);
				gacelaList.add(gacela);
			}
			else if(s.matches("[A*C*T*G*]*ACTTTACGA[A*C*T*G*]*")) {//5 alergia
				gacela.setSequence(sequence[j]);
				gacela.setCualidad(5);
				gacelaList.add(gacela);
			}
			else if(s.matches("[A*C*T*G*]*CCGATATGT[A*C*T*G*]*")) {//6 esteril
				//SOFI: EN ESTA PARTE TENGO DUDA XQ ESTA RECORRIEDO LOS CASOS DE MUERTE DE NUEVO
				//CUANDO YA LOS HABIA RECORRIDO ANTERIORMENTE,
				//PARA MI HABRIA QUE BORRARLOS Y PONER EL CASO ESTERI Y UN HIJO A PARTE, SIN EL ELSE DE LOS CASOS
				//DE MUERTE
				if(s.matches("[A*C*T*G*]*ACGGTAAAC[A*C*T*G*]*")) {
					gacela.setSequence(sequence[j]);
					gacela.setCualidad(1);
					gacelaList.add(gacela);
				}
				else if(s.matches("[A*C*T*G*]*AACACGTTG[A*C*T*G*]*")) {
					gacela.setSequence(sequence[j]);
					gacela.setCualidad(2);
					gacelaList.add(gacela);
				}
				else if(s.matches("[A*C*T*G*]*GGCTTATGA[A*C*T*G*]*")) {
					gacela.setSequence(sequence[j]);
					gacela.setCualidad(3);
					gacelaList.add(gacela);
				}
				else if(s.matches("[A*C*T*G*]*CTCATGTTA[A*C*T*G*]*")) {
					gacela.setSequence(sequence[j]);
					gacela.setCualidad(4);
					gacelaList.add(gacela);
				}
				else if(s.matches("[A*C*T*G*]*ACTTTACGA[A*C*T*G*]*")) {
					gacela.setSequence(sequence[j]);
					gacela.setCualidad(5);
					gacelaList.add(gacela);
				}else {
					gacela.setSequence(sequence[j]);
					gacela.setCualidad(6);
					gacelaList.add(gacela);
				}
			}
			else 
				if(s.matches("[A*C*T*G*]*GGTTAAACG[A*C*T*G*]*")) {//7 1 hijo

				if(s.matches("[A*C*T*G*]*ACGGTAAAC[A*C*T*G*]*")) {
					gacela.setSequence(sequence[j]);
					gacela.setCualidad(1);
					gacelaList.add(gacela);
				}
				else if(s.matches("[A*C*T*G*]*AACACGTTG[A*C*T*G*]*")) {
					gacela.setSequence(sequence[j]);
					gacela.setCualidad(2);
					gacelaList.add(gacela);
				}
				else if(s.matches("[A*C*T*G*]*GGCTTATGA[A*C*T*G*]*")) {
					gacela.setSequence(sequence[j]);
					gacela.setCualidad(3);
					gacelaList.add(gacela);
				}
				else if(s.matches("[A*C*T*G*]*CTCATGTTA[A*C*T*G*]*")) {
					gacela.setSequence(sequence[j]);
					gacela.setCualidad(4);
					gacelaList.add(gacela);
				}
				else if(s.matches("[A*C*T*G*]*ACTTTACGA[A*C*T*G*]*")) {
					gacela.setSequence(sequence[j]);
					gacela.setCualidad(5);
					gacelaList.add(gacela);
				}
				else if(s.matches("[A*C*T*G*]*CCGATATGT[A*C*T*G*]*")) {
					gacela.setSequence(sequence[j]);
					gacela.setCualidad(6);
					gacelaList.add(gacela);
				}
				else {
					gacela.setSequence(sequence[j]);
					gacela.setCualidad(7);
					gacelaList.add(gacela);
				}
			}
			else {
				//TENIENDO EN CUENTA QUE SE PUEDE TENER GACELAS SIN GENES ESPECIF
				// HICE UNA EXP REGULAR PARA EL CASO DE QUE  NO SEA NINGUNA DE LAS LETRAS
				//ESPECIFICADAS (ACGT), ENTONCES NO ES UNA GACELA 
				if(s.matches("(B*[D-F]*[H-S]*[U-Z]*)*"){
					System.out.println(("Esto no es una gacela"));
				}
			}
			return gacelaList;
		}


		public static String readFile(String path) throws IOException {
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(path));
			} catch (FileNotFoundException e) {
				//De llegar aquí la ejecución, significa que el archivo no existe
			}
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
			}
			reader.close();
			return builder.toString();
		}
}
