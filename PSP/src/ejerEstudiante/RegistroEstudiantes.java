package ejerEstudiante;

import java.util.*;

public class RegistroEstudiantes{
	private ArrayList<Estudiante> registro = new ArrayList<Estudiante>();
	Scanner teclado = new Scanner(System.in);
	String nombre, matricula;
	int edad;
	
	public void agregarEstudiante() {
		System.out.println("nombre");
		nombre = teclado.nextLine();
		System.out.println("edad");
		edad = teclado.nextInt();
		teclado.nextLine();
		System.out.println("matricula");
		matricula = teclado.nextLine();
		Estudiante e = new Estudiante(nombre, edad, matricula);
		registro.add(e);
	}
	
	public void mostrarEstudiantes() {
		for(Estudiante e : registro) {
			System.out.println("Nombre: " + e.getNombre());
			System.out.println("Edad: " + e.getEdad());
			System.out.println("Matricula: " + e.getMatricula());
			System.out.println("\n");
		}
	}
	
	public void buscarEstudiante() {
		String busqueda;
		int cont = 0;
		System.out.println("Dime una matricula para buscar a ese estudiante y mostrar sus datos");
		busqueda = teclado.nextLine();
		for(Estudiante e : registro) {
			if (e.getMatricula().equalsIgnoreCase(busqueda)){
				System.out.println("Nombre: " + e.getNombre());
				System.out.println("Edad: " + e.getEdad());
				System.out.println("Matricula: " + e.getMatricula());
				cont++;
			}
			if (cont == 0) {
				System.out.println("No hay ningun estudiante registrado con esa matricula");
			}
		}
	}
	
	public void eliminarEstudiante() {
		String busqueda;
		System.out.println("Dime una matricula para buscar a ese estudiante y eliminarlo");
		busqueda = teclado.nextLine();
		for(Estudiante e : registro) {
			if (e.getMatricula().equalsIgnoreCase(busqueda)){
				registro.remove(e);
				System.out.println("Se ha eliminado correctamente");
			}else 
				System.out.println("No hay ningun estudiante registrado con esa matricula");
		}
	}
}
