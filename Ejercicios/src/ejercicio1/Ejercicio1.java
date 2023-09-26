package ejercicio1;

import java.io.File;
import java.util.ArrayList;

public class Ejercicio1 {

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
