package ejercicioEntrega1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EjercicioEntrega {
/**
 * Este es el primer ejercicio de entrega del primer trimestre:
 * Escribe un programa en Java que lea el fichero datos.txt y los guarde en otro fichero llamado 
 * @param args
 * @throws IOException
 */
	public static void main(String[] args) throws IOException {
		
		File mifichero = new File("datos.txt");
		File fichero2 = new File("tarjetas.txt");
		try {
			FileReader lector = new FileReader(mifichero);
			BufferedReader cestaLectura = new BufferedReader(lector);
		
			FileWriter escritor = new FileWriter(fichero2,true);
			BufferedWriter cestaEscritura = new BufferedWriter(escritor);
		
			
			
			int caracter = lector.read();
			int cont = 0;
			while(caracter > 0 ) {
					String cadena = "";
					while(caracter != ',' && caracter != '\n' && caracter > 0) {
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
					
					}else if(cont == 4){
						cestaEscritura.write("Fecha Nacimiento: "+ cadena);
						cestaEscritura.newLine();
					}else {
						cestaEscritura.write("Teléfono: "+ cadena);
						cestaEscritura.newLine();
						cont=0;
					}
					caracter = lector.read();
				
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

