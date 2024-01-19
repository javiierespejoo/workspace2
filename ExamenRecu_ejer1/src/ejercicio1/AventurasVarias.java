package ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AventurasVarias {

	public static void main(String[] args) {
		try {
			ArrayList<Cliente> listaClientes = new ArrayList<>();
			File mifichero = new File("src//ejercicio1//AventurasVarias.txt");
			
			FileReader lector = new FileReader(mifichero);
			BufferedReader cestaTemporal = new BufferedReader(lector);
			
			String linea = cestaTemporal.readLine();
			int cont = 0;
			while(linea != null) {
				cont++;
				String[] campos = linea.split(";");
				Cliente c = null;
				int vip;
				if(campos[5] != "") {
					vip = 1;
				}else {
					vip = 0;
				}
				try {
					c = new Cliente(cont, campos[0], campos[1], campos[2], campos[3], campos[4], vip, campos[6]);
				}catch(NumberFormatException e) {
					c = new Cliente(null);
				}
				listaClientes.add(c);
				linea = cestaTemporal.readLine();
			}
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:src//ejercicio1//AventurasVarias.db");
			Statement stm = conexion.createStatement();
			
			
			for(Cliente c : listaClientes) {
				System.out.println(c.toString());
				
				stm.executeUpdate("INSERT INTO 'Clientes' VALUES('"+c.getId()+"' ,'"+c.getNombre()+"', '"+c.getApellidos()+"', '"+c.getTelefono()+"', '"+c.getDireccion()+"', '"+c.getEmail()+"', '"+c.isVip()+"', '"+c.getNacionalidad()+"')");
				
			}
			
			stm.close();
			conexion.close();
			System.out.println("Se han añadido los clientes a la base de datos");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
