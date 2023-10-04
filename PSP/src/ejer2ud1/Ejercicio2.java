package ejer2ud1;

public class Ejercicio2 {

	public static void main(String[] args) {
		
		Runtime objeto = Runtime.getRuntime();
		int procesadores = objeto.availableProcessors();
		
		System.out.println("Número de procesadores: " + procesadores);
		
	}
}
