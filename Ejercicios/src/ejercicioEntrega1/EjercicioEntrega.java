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
		
			
			
			int caracter = lector.read();
			int cont = 0;
			while(caracter != '\n') {
				if (caracter < 0) {
					String cadena = "";
					while(caracter != ',') {
						cadena = cadena + (char)caracter;
						caracter = lector.read();
					}
					cont++;
				
					if (cont == 1) {
						cestaEscritura.write("Nombre: "+ cadena);
						cestaEscritura.newLine();
					}else if (cont == 2) {
						cestaEscritura.write("Apellidos: "+ cadena);
						cestaEscritura.newLine();
					}else if (cont == 3) {
						cestaEscritura.write("Fecha Nacimiento "+ cadena);
						cestaEscritura.newLine();
					}else {
						cestaEscritura.write("Teléfono: "+ cadena);
						cestaEscritura.newLine();
					}
					caracter = lector.read();
				}
			}
			cestaEscritura.newLine();
			cestaLectura.close();
			cestaEscritura.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}

