package ejercicio4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ejercicio4 {

	public static void main(String[] args) throws IOException {
		
		Ejercicio4 ej4 = new Ejercicio4();
		
		File mifichero = new File ("ejercicio4.txt");
		String linea = "";
		
		FileWriter escritor = new FileWriter(mifichero, true);
		BufferedWriter cestaEscritura = new BufferedWriter(escritor);
		
		//Leo de teclado hasta encontrar la palabra fin
		while(!linea.equalsIgnoreCase("fin")) {
			
			Scanner teclado = new Scanner(System.in);
			linea = teclado.nextLine();
			
			if (!linea.equalsIgnoreCase("fin")) {
				cestaEscritura.write(linea);
				cestaEscritura.newLine();
			}else {
				cestaEscritura.close();
				escritor.close();
			}
			
		}
		
	}
	
	private String leerLinea(FileReader lector) throws IOException{
		int caracter = lector.read();
		
		if (caracter < 0) return null;
		String cadena = "";
		while(caracter != '\n') {
			cadena = cadena + (char)caracter;
			caracter = lector.read();
		}
		return cadena;
	}
	
	private boolean enFichero2(File mifichero, String frase) {
		FileReader lector;
		try {
			lector = new FileReader(mifichero);
			
			String linea = leerLinea(lector);
			while(linea != null) {
				if(linea.equals(frase)) return true;
				linea = leerLinea(lector);
			}
			lector.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
