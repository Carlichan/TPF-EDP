package GacelaSimulator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class GenerarGacelaFile {
	private final static String NORMALIZATED_FILE_SEPARATOR = "/";

	public static void main(String[] args) {
		String gacelas ="";
		for(int i = 0; i < 10 ; i++) {
			gacelas += generarGacela();
			gacelas += '\n';
		}
		System.out.println(gacelas);
		try {
			writeToFile("C:\\Users\\User\\Desktop\\GacelaFile.txt", gacelas);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static String generarGacela() {
		String gacela = "";
		HashMap<Integer,String> fixedSequence = new HashMap<Integer,String>();
		fixedSequence.put(1,"ACGGTAAAC");  //comida leones
		fixedSequence.put(2, "AACACGTTG"); // comida cocos
		fixedSequence.put(3, "GGCTTATGA"); // enfermedad
		fixedSequence.put(4, "CTCATGTTA"); // hambruna
		fixedSequence.put(5, "ACTTTACGA"); // alergia
		fixedSequence.put(6, "CCGATATGT"); // esteril
		fixedSequence.put(7, "GGTTAAACG"); // 1 hijo
		//fixedSequence.put(8, "AAGCCTTCG"); // 2 hijos

		for(int i = 0; i<23 ; i++) {
			int a = getRandomIntBetween(1, 4);
			if(a == 1) {
				gacela += 'A';
			}
			else if(a == 2) {
				gacela += 'C';
			}
			else if(a == 3) {
				gacela += 'G';
			}
			else if(a == 4) {
				gacela += 'T';
			}
		}
		int cualidad = getRandomIntBetween(1, 20);
		if(cualidad > 7)
			cualidad = 7;
		gacela += fixedSequence.get(cualidad);
		
		for(int i = 0; i<18 ; i++) {
			int a = getRandomIntBetween(1, 4);
			if(a == 1) {
				gacela += 'A';
			}
			else if(a == 2) {
				gacela += 'C';
			}
			else if(a == 3) {
				gacela += 'G';
			}
			else if(a == 4) {
				gacela += 'T';
			}
		}
		
		return gacela;
	}

	public static int getRandomIntBetween(int min, int max) {
		if (max < min) {
			throw new IllegalArgumentException();
		}
		return (int) Math.floor(Math.random()*(max-min+1)+min);
	}

	public static void writeToFile(String path, String fileContent) throws IOException {
		String normalizatedFileName = normalizateFileName(path);                
		int slashPosition = normalizatedFileName.lastIndexOf(NORMALIZATED_FILE_SEPARATOR);
		if (slashPosition >= 0)  {
			File aFile = new File(normalizatedFileName.substring(0, slashPosition));
			if (!aFile.exists()) {
				aFile.mkdirs();
			}
		}
		FileWriter fileWriter = new FileWriter(path);
		fileWriter.write(fileContent);
		fileWriter.close();
	}

	private static String normalizateFileName(String path) {
		String normalizatedFileName;
		if (File.separator.equals("\\")) {
			normalizatedFileName = path.replaceAll("\\\\",NORMALIZATED_FILE_SEPARATOR );
		} else {
			normalizatedFileName = path;
		}
		return normalizatedFileName;
	}
}
