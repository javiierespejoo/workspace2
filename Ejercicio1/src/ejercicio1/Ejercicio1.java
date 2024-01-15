package ejercicio1;

import java.io.File;
import java.util.ArrayList;

public class Ejercicio1 {
/**
 * Este es el ejercicio 1 del tema 1:
 * Escribe un programa Java que inicialice cuatro objetos de tipo File: dos que hagan referencia a
ficheros, que uno exista y otro no, y los otros dos a directorios, que uno exista y el otro no. El
programa debe averiguar si existen dichos ficheros o directorios, independientemente de que
sean una cosa o la otra.
También debe averiguar para cada objeto que exista si es un fichero o directorio. A la hora de
visualizar el nombre del fichero o directorio, prueba a usar los diferentes métodos get que
ofrece la clase File para ver la diferencia que hay entre cada uno de ellos.
 * @param args
 */
	public static void main(String[] args) {
		
		File f1 = new File("C:\\Users\\alu\\eclipse-workspace\\ejemplosAD\\Ficheros\\saludo.txt");
		File f2 = new File("C:\\Users\\alu\\eclipse-workspace\\ejemplosAD\\Ficheros\\hola.txt");
		File d1 = new File("C:\\Users\\alu\\eclipse-workspace\\ejemplosAD\\Ficheros");
		File d2 = new File("C:\\Users\\alu\\eclipse-workspace\\ejemplosAD\\Javier");
		
		ArrayList<File> listaFiles = new ArrayList<>();
		listaFiles.add(f1);
		listaFiles.add(f2);
		listaFiles.add(d1);
		listaFiles.add(d2);
		
		for (File file : listaFiles) {
				if(file.exists()) {
					if(file.isDirectory()) {
						System.out.println("El directorio " + file.getName() + ", existe.");
					} else {
						System.out.println("EL fichero " + file.getName() + ", existe.");
					}
				} else {
					System.out.println(file.getName() + " NO existe.");
				}
				System.out.println(" Y su path es: " + file.getPath());
			
		}

	}

}
