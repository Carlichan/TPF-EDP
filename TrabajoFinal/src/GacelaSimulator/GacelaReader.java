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
			else if(s.matches("[A*C*T*G*]*GGTTAAACG[A*C*T*G*]*")) {//7 1 hijo
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
				throw new IllegalArgumentException("this is not a gazelle");
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
