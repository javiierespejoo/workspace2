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
