package ejercicioEntrega1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EjercicioEntrega {

	public static void main(String[] args) throws IOException {
		
		File mifichero = new File("datos.txt");
		File fichero2 = new File("tarjetas.txt");
		try {
			FileReader lector = new FileReader(mifichero);
			BufferedReader cestaLectura = new BufferedReader(lector);
		
			FileWriter escritor = new FileWriter(fichero2,true);
			BufferedWriter cestaEscritura = new BufferedWriter(escritor);
		
			String frase = cestaLectura.readLine();
		
			while(frase != null) {
			
				cestaEscritura.write(frase);
				cestaEscritura.newLine();
				frase = cestaLectura.readLine();	
			}
		
			cestaLectura.close();
			cestaEscritura.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}

