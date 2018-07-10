package GacelaSimulator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class GenerarGacelaFile {
	private final static int BASES_QUANTITY = 50;
	private final static int FIXED_SEQUENCE_QUANTITY = 9;
	private final static int GACELA_QUANTITY = 6;
	private final static String NORMALIZATED_FILE_SEPARATOR = "/";

	public static void main(String[] args) {
		String gacela ="";
		for(int i = 0; i < GACELA_QUANTITY ; i++) {
			gacela += generarGacela();
			gacela += '\n';
		}
		System.out.println(gacela);
		try {
		writeToFile("C:\\Users\\User\\Desktop\\GacelaFile.txt", gacela);
		
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
		
		for(int i = 0; i<BASES_QUANTITY-FIXED_SEQUENCE_QUANTITY ; i++) {
			int a = getRandomIntBetween(1, 4);
			switch(a) {
			case 1: 
				gacela += 'A';
				break;
			case 2:
				gacela += 'C';
				break;
			case 3:
				gacela += 'G';
				break;
			case 4:
				gacela += 'T';
				break;
			}
		}
		int cualidad = getRandomIntBetween(1, 7);
		gacela += fixedSequence.get(cualidad);
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
