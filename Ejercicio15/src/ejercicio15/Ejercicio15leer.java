package ejercicio15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Ejercicio15leer {

	public static void main(String[] args) {
		try{
			FileInputStream fis = new FileInputStream("Ejercicio15.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);

			Empleados e = (Empleados) ois.readObject();
			Empleados e2 = (Empleados) ois.readObject();
			System.out.println(e);
			System.out.println(e2);
				  
			  
			  
			  ois.close(); 
			  fis.close();
			}catch(FileNotFoundException e){
			  e.printStackTrace();
			}catch(IOException e){
			  e.printStackTrace();
			}catch(ClassNotFoundException e){
			  e.printStackTrace();
			}

	}

}
