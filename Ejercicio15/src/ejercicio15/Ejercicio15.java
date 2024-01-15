package ejercicio15;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio15 {
/**
 * Este es el ejercicio 15 del tema 1:
 * Escribe un programa Java que pida al usuario los datos de una serie de empleados y que los
guarde en un fichero binario. El usuario introducirá datos de empleados hasta que indique que
no quiere añadir ninguno más. Los empleados se almacenarán en un fichero binario como
objetos de una clase Empleado que deberá contener nombre, sueldo, año de nacimiento y
antigüedad. 
 * @param args
 * @throws IOException
 */
	public static void main(String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		File mifichero = new File("Ejercicio15.txt");
		ArrayList<Empleados> listaEmpleados = new ArrayList<>();
		int opcion = 0;
		
		FileOutputStream escritorBinario = new FileOutputStream(mifichero);
		ObjectOutputStream os = new ObjectOutputStream(escritorBinario);
		
		while(opcion != 2) {
			System.out.println("Seleccione una opción: ");
			System.out.println("1.- Añadir empleado");
			System.out.println("2.- Finalizar");
			opcion = teclado.nextInt();
			
			switch(opcion) {
			case 1 :
				System.out.println("Dime su nombre");
				String nombre = teclado.nextLine();
				
			break;
			}
		}
	}

}
