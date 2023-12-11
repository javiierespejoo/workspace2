package ejercicio1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LeeTodo {

	private static int tamagnoRegistro = 56;
	private static int tamagnoNombre = 20;
	public static void main(String[] args) throws IOException {
		RandomAccessFile ficheroAleatorio = null;
		try {
			ficheroAleatorio = new RandomAccessFile("src\\ejercicio1\\Trabajadores.dat", "rw");
		} catch (FileNotFoundException e) {

			System.err.println("No existe el fichero");
			e.printStackTrace();
		}
		
		for(int i = 1; funcion(i) < ficheroAleatorio.length(); i++) {
			ficheroAleatorio.seek(funcion(i));
			
			if(ficheroAleatorio.readInt() != 0) {
				
				ficheroAleatorio.seek(funcion(i));
				
				System.out.println("ID del trabajador: " + ficheroAleatorio.readInt());
				System.out.println("Nombre del trabajador: " + obtenerString(ficheroAleatorio,tamagnoNombre));
				System.out.println("Salario del trabajador: " + ficheroAleatorio.readDouble());
				System.out.println("ID de su sindicato: " + ficheroAleatorio.readInt());
				System.out.println("--------------------------------------------");
			}
		}

	}
	
	private static int funcion(int numero) {
		int pos = 0;
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
