package ejercicio18;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio18b {
	
	private static int tamagnoNombre = 15;
	private static int tamagnoLocalidad = 15;
	public static void main(String[] args) throws IOException {
		
		RandomAccessFile mifichero = null;
		try {
			mifichero = new RandomAccessFile("src\\ejercicio18\\departamentos.dat", "rw");
		} catch (FileNotFoundException e) {

			System.err.println("No existe el fichero");
			e.printStackTrace();
		}
		
		mifichero.seek(funcion(2));
		System.out.println("El numero de departamento es: " + mifichero.readInt());
		
		System.out.println("El nombre del departamento es: " + obtenerString(mifichero, tamagnoNombre));
		
		System.out.println("La localidad es: " + obtenerString(mifichero, tamagnoLocalidad));
		
	}
	
	private static int funcion(int numero) {
		int pos = 0;
		int tamagnoRegistro = 64;
		pos = tamagnoRegistro * (numero - 1) + 1;
		return pos;
	}

	private static String obtenerString(RandomAccessFile mifichero, int tamagno) throws IOException {
		char datos[] = new char[tamagno];
		for (int i = 0; i < tamagno; i++) {
			datos[i] = mifichero.readChar();
		}
		return new String(datos);
	}
}
