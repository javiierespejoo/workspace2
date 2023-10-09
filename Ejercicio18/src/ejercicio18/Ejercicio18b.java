package ejercicio18;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio18b {
	private static int tamagnoRegistro = 154;
	public static void main(String[] args) throws IOException {
		
		RandomAccessFile mifichero = null;
		try {
			mifichero = new RandomAccessFile("src\\ejercicio18\\departamentos.dat", "rw");
		} catch (FileNotFoundException e) {

			System.err.println("No existe el fichero");
			e.printStackTrace();
		}
		int pos = 0;
		mifichero.seek(pos);
		System.out.println(mifichero.readInt());
		char nombre[] = new char[tamagnoRegistro];
		for (int i = 0; i < tamagnoRegistro; i++) {
			nombre[i] = mifichero.readChar();
		}
		
	}

}
