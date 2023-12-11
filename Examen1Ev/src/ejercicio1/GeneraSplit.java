package ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class GeneraSplit {
	/**
	 * Este ejercicio transformara el fichero Trabajadores.txt en un fichero 
	 * de acceso aleatorio
	 */
	
	private static int tamagnoRegistro = 56;//int(4) + 20 char + double(8) + int(4) = 4+40+8+4 = 56
	private static int tamagnoNombre = 20;
	public static void main(String[] args) {
	try {
		ArrayList<Trabajadores> listaTrabajadores = new ArrayList<>();
		File mifichero = new File("TrabajadoresSplit.txt");
		
		FileReader lector = new FileReader(mifichero);
		BufferedReader cestaTemporal = new BufferedReader(lector);
		
		String linea = cestaTemporal.readLine();
		
		linea = cestaTemporal.readLine();
		while(linea != null) {
			String[] campos = linea.split(",");
			Trabajadores tr = null;
			try {
				tr = new Trabajadores(Integer.parseInt(campos[0]), campos[1], Double.parseDouble(campos[2]), Integer.parseInt(campos[3]));
			}catch(NumberFormatException e) {
				tr = new Trabajadores(-1);
			}
			listaTrabajadores.add(tr);
			linea = cestaTemporal.readLine();
		}
			RandomAccessFile ficheroAleatorio = new RandomAccessFile("src\\ejercicio1\\TrabajadoresSplit.dat", "rw");
			
			for (Trabajadores t : listaTrabajadores) {
				//posiciono el puntero donde empezare a escribir
				ficheroAleatorio.seek(funcion(t.getId()));
				
				//escribo el id
				ficheroAleatorio.writeInt(t.getId());
				
				//Modifico el nombre para que tenga el tamaño escogido
				StringBuffer bNombre = new StringBuffer();
				bNombre.append(t.getNombre());
				bNombre.setLength(tamagnoNombre);
				String nombreNuevo = new String (bNombre);
				t.setNombre(nombreNuevo);
				//Escribo el nombre
				ficheroAleatorio.writeChars(t.getNombre());
				
				//Escribo el salario
				ficheroAleatorio.writeDouble(t.getSalario());
				
				//Escribo el id_sindicato
				ficheroAleatorio.writeInt(t.getId_sindicato());
				
			}
			
			
			
				
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static int funcion(int numero) {
		int pos = 0;
		pos = tamagnoRegistro * (numero - 1) + 1;
		return pos;
	}
	
	private static String obtenerString(RandomAccessFile mifichero, int tamagno) throws IOException {
		char datos[] = new char[tamagno];
		for (int i = 0; i < tamagno; i++) {
			datos[i] = mifichero.readChar();
		}
		return new String(datos);
	}

}
