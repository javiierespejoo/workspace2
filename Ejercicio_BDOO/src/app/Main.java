package app;

import java.util.Scanner;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import datos.Autor;
import datos.Libro;

public class Main {
	static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		boolean salir = false;
		int opcion;
		
		while (!salir) {
			menu();
			opcion = Utilidades.pedirEntero("");
			if (opcion > 0 && opcion < 4) {
				switch (opcion) {
					case 1:
						insertarLibro();
					break;
					case 2:
						insertarAutor();
					break;
					case 3:
						
					break;
					case 4:
						
					break;
					case 5:
						
					break;
					case 6:
						
					break;
					case 0:
						salir = true;
					break;
				}
			} else {
				System.out.println("EL numero tiene que estar comprendido entre 1 y 3");
			}
		}
	}
	
	public static void menu() {
		System.out.println("Seleccione una opcion:");
		System.out.println("0.- Salir.");
		System.out.println("1.- Insertar un libro, sin especificar ningun autor.");
		System.out.println("2.- Insertar un autor, sin especificar ningun libro.");
		System.out.println("3.- Modificar un libro para añadirle un autor existente de la bbdd.");
		System.out.println("4.- Obtener los nombres de los empleados cuyo jefe es Pedro Lopez.");
		System.out.println("5.- Obtener el numero de empleados del departamento de Ventas.");
		System.out.println("6.- Obtener el numero de empleados de cada departamento (OPCIONAL).");
	}

	public static void insertarLibro() {
		ODB baseDatos = ODBFactory.open("bbdd/Biblioteca", "javier", "Pass!123456");
		
		System.out.println("Dime el Titulo");
		String titulo = teclado.nextLine();
		
		System.out.println("Dime su año de publicacion");
		int año = teclado.nextInt();
		teclado.nextLine();
		
		System.out.println("Dime su editorial");
		String editorial = teclado.nextLine();
		
		System.out.println("Dime su numero de paginas");
		int paginas = teclado.nextInt();
		teclado.nextLine();
		
		Libro l = new Libro(titulo, año, editorial, paginas, null);
		baseDatos.store(l);
		
		baseDatos.close();
	}
	
	public static void insertarAutor() {
		ODB baseDatos = ODBFactory.open("bbdd/Biblioteca", "javier", "Pass!123456");
		
			System.out.println("Dime su nombre");
			String nombre = teclado.nextLine();
		
			System.out.println("Dime su año de publicacion");
			String apellidos = teclado.nextLine();
		
			System.out.println("Dime su nacionalidad");
			String nacionalidad = teclado.nextLine();
		
			System.out.println("Dime su numero de paginas");
			int edad = teclado.nextInt();
			teclado.nextLine();
		
			Autor a = new Autor(nombre, apellidos, nacionalidad, edad, null);
			baseDatos.store(a);
			
			baseDatos.close();
	}
	
	public static void añadirAutor() {
		
	}
	
	
	
}
















