package ejercicio18;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Realiza un programa Java que cree un fichero binario para guardar datos de departamentos.
Dale el nombre “departamentos.dat” e introduce varios departamentos, accediendo de forma
aleatoria al fichero.

Para su uso en posteriores ejercicios, el fichero binario “departamentos.dat” deberá contener
varios registros de departamentos ordenados por el número de departamento. Este campo será
un identificador único que se utilizará posteriormente para acceder a la posición que ocupa un
registro dentro del fichero y obtener sus datos.

 */

public class Ejercicio18 {

	private static int tamagnoRegistro = 64; // int(4) + 15 char + 15 char = 4 + 30 + 30 = 64
	public static void main(String[] args) {
		
		ArrayList<Departamentos> listaDepartamentos = new ArrayList<>();
		Departamentos d1 = new Departamentos(101, "Matematicas    ", "Zaragoza       ");
		listaDepartamentos.add(d1);
		Departamentos d2 = new Departamentos(98, "Fisica         ", "Zaragoza       ");
		listaDepartamentos.add(d2);
		Departamentos d3 = new Departamentos(2, "Ingles         ", "Huesca         ");
		listaDepartamentos.add(d3);
		Departamentos d4 = new Departamentos(213, "Fisica         ", "Teruel         ");
		listaDepartamentos.add(d4);
		
		// defino el fichero donde voy a escribir
		RandomAccessFile mifichero = null;
		try {
			mifichero = new RandomAccessFile("src\\ejercicio18\\departamentos.dat", "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("No existe el fichero");
			e.printStackTrace();
		}
		// escribir todos los departamento
		
		for (Departamentos d : listaDepartamentos) {
			try {
				
				// colocar el cursor donde voy a empezar a escribir
				mifichero.seek(funcion(d.getNumero()));
				
				// escribo el numero de departamento
				mifichero.writeInt(d.getNumero());
				
				// escribo el nombre del departamento
				mifichero.writeChars(d.getNombre());
				
				// escribo la localidad
				mifichero.writeChars(d.getLocalidad()); 
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
		}
		
		try {
			mifichero.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Calcula la posicion dentro del fichero en la que debe estar el cursor para leer el registro "numero" 
	 * 
	 * @param numero numero de registro al que quiero acceder
	 * @return la posicion dentro del fichero en la que debe estar el cursor para leer el registro "numero"
	 */
	private static int funcion(int numero) {
		int pos = 0;
		pos = tamagnoRegistro * (numero - 1) + 1;
		return pos;
	}

}
