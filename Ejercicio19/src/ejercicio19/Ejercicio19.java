package ejercicio19;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio19 {
	
	static Scanner teclado = new Scanner(System.in);
	private static int tamagnoNombre = 15;
	private static int tamagnoLocalidad = 15;
	public static void main(String[] args) throws IOException {
			
			RandomAccessFile mifichero = null;
			try {
				mifichero = new RandomAccessFile("src\\ejercicio19\\departamentos.dat", "rw");
			} catch (FileNotFoundException e) {

				System.err.println("No existe el fichero");
				e.printStackTrace();
			}
			System.out.println("Dime el numero del departamento que quieres modificar");
			int num = teclado.nextInt();
			
			mostrarDepartamento(num, mifichero);
			escribirDepartamento(num, mifichero);
			
			
			
		}

	private static void mostrarDepartamento(int num, RandomAccessFile mifichero) throws IOException {
		mifichero.seek(funcion(num));
		System.out.println("El numero de departamento es: " + mifichero.readInt());
		
		System.out.println("El nombre viejo del departamento era: " + obtenerString(mifichero, tamagnoNombre));
		
		System.out.println("La localidad vieja era: " + obtenerString(mifichero, tamagnoLocalidad));
	}
	
	private static void escribirDepartamento(int num, RandomAccessFile mifichero) throws IOException {
		mifichero.seek(funcion(num));
		System.out.println("Dime su nuevo nombre");
		String nombreNuevo = teclado.nextLine();
		
		StringBuffer bNombre = new StringBuffer();
		bNombre.append(nombreNuevo);
		bNombre.setLength(tamagnoNombre);
		new String (bNombre);
		teclado.nextLine();
		
		System.out.println("Dime su nueva localidad");
		String localidadNueva = teclado.nextLine();
		
		StringBuffer bLocalidad = new StringBuffer();
		bLocalidad.append(localidadNueva);
		bLocalidad.setLength(tamagnoLocalidad);
		new String (bLocalidad);
		
		
		
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
