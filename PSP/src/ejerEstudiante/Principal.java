package ejerEstudiante;

import java.util.Scanner;

public class Principal {
	static String nombre;
	static int edad;
	static String matricula;

	
	public static void main(String[] args) {
		RegistroEstudiantes registro = new RegistroEstudiantes();
		Scanner teclado = new Scanner(System.in);
		int opcion;
		
		do {
			System.out.println("Selecciona 1 opcion:");
			System.out.println("1.- Agregar estudiante");
			System.out.println("2.- Mostrar estudiantes");
			System.out.println("3.- Buscar estudiante");
			System.out.println("4.- Eliminar estudiante");
			System.out.println("5.- Salir");
			opcion = teclado.nextInt();
			
			switch(opcion) {
			case 1:
				registro.agregarEstudiante();
			break;
			case 2:
				registro.mostrarEstudiantes();
			break;
			case 3:
				registro.buscarEstudiante();
			break;
			case 4:
				registro.eliminarEstudiante();
			break;
			case 5:
				System.out.println("-------Ha eligido finalizar el programa-------");
				System.out.println("Hasta prontooo ;)");
			}//switch
		}//do-while
		while(opcion != 5);
		
		
	}

	

}