package ejercicio20;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio20 {
	/**
	 * A partir del fichero binario “departamentos.dat” creado en el ejercicio 18, realiza un programa
Java que permita eliminar un departamento. El programa recibirá desde la línea de comandos
el número de departamento a eliminar. Si el departamento no existe, visualiza un mensaje
indicándolo. Visualiza también el número total de departamentos que existen en el fichero.
	 * @param args
	 * @throws IOException
	 */
	
	public static void main(String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		int tamagnoNombre = 15, tamagnoLocalidad = 15;
		RandomAccessFile mifichero = null;
		try {
			mifichero = new RandomAccessFile("src\\ejercicio20\\departamentos.dat", "rw");
		} catch (FileNotFoundException e) {

			System.err.println("No existe el fichero");
			e.printStackTrace();
		}
		
		System.out.println("Hay " + numDeps(mifichero) + " departamentos.");
		System.out.println("Dime el id del departamento que quieras eliminar");
		int id = teclado.nextInt();
		
		mifichero.seek(funcion(id));
		if (mifichero.readInt() != 0) {
			mifichero.seek(funcion(id));
			System.out.println("El numero de departamento es: " + mifichero.readInt());
			System.out.println("El nombre del departamento es: " + obtenerString(mifichero, tamagnoNombre));
			System.out.println("La localidad es: " + obtenerString(mifichero, tamagnoLocalidad));
			
			mifichero.seek(funcion(id));
			mifichero.writeInt(0);
			
			String nombreNuevo = "";
			String localNueva = "";
			
			StringBuffer bNombre = new StringBuffer();
			bNombre.append(nombreNuevo);
			bNombre.setLength(tamagnoNombre);
			nombreNuevo = new String (bNombre);
			mifichero.writeChars(nombreNuevo);
			
			StringBuffer bLocal = new StringBuffer();
			bLocal.append(localNueva);
			bLocal.setLength(tamagnoLocalidad);
			localNueva = new String (bLocal);
			mifichero.writeChars(localNueva);
			
			
			System.out.println("Este departamento ha sido eliminado");
			
		} else {
			System.out.println("El departamento escogido no existe");
		}
		
		
		
	}

	private static int funcion(int numero) {
		int pos = 0;
		int tamagnoRegistro = 64;
		pos = tamagnoRegistro * (numero - 1) + 1;
		return pos;
	}
	private static int numDeps(RandomAccessFile mifichero) throws IOException {
		int cont = 0;
		
		//Este bucle solo sirve para saber la cantidad de departamentos que
		// hay escritos en el fichero
		
		for(int i = 1; funcion(i) < mifichero.length(); i++) {
			mifichero.seek(funcion(i));
			if (mifichero.readInt() != 0) {
				cont++;
			}
		}
		System.out.println(cont);
		return cont;
	}
	private static String obtenerString(RandomAccessFile mifichero, int tamagno) throws IOException {
		char datos[] = new char[tamagno];
		for (int i = 0; i < tamagno; i++) {
			datos[i] = mifichero.readChar();
		}
		return (new String(datos)).trim();
	}
}
