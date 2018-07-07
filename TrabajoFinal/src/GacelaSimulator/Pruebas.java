package GacelaSimulator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Pruebas {
	public static void main(String[] args) throws IOException {
		String text = readFile("C:\\Users\\User\\Desktop\\GacelaFile.txt");
		System.out.println(text);
		String[]  a= text.split("\r\n");
		for (int i = 0; i<a.length ; i++) {
			System.out.println("a es " + a[i]);
			if(a[i].matches(".*ACTTTACGA.*")) {
				System.out.println("YAAAAY");
			}
		}
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
