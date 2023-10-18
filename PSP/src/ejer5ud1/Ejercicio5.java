package ejer5ud1;

import java.io.File;
import java.io.IOException;

public class Ejercicio5 {

	public static void main(String[] args) {
		
		try {
			System.out.println(System.getProperty("user.dir"));
			String[] comando = {"java" , "-cp" , ".\\src" , "ejer5ud1.Sumador" , "5" , "7"};
			ProcessBuilder pb = new ProcessBuilder(comando);
			pb.redirectOutput(new File("salida_sumador.txt"));
			pb.redirectError(new File("salida_error.txt"));
			pb.start();
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
 