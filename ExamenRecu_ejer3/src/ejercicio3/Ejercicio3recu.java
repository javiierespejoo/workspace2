package ejercicio3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class Ejercicio3recu {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int opcion = 1;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conexion = DriverManager.getConnection("jdbc:mysql://192.168.56.102/AventurasVarias", "javier", "Pass!123456");
			

			Statement stm = conexion.createStatement();
			
			
			while(opcion != 0) {
				System.out.println("Selecciona una opcion:");
				System.out.println("0.- Finalizar el programa");
				System.out.println("1.- Listar todos los clientes");
				System.out.println("2.- Modificar el numero de telefono de un cliente a su eleccion");
				opcion = teclado.nextInt();
				
				ResultSet resultado;
				
				switch(opcion) {
				case 1:
					resultado = stm.executeQuery("SELECT * FROM Clientes");
					mostrarVips(resultado);
					resultado = stm.executeQuery("SELECT * FROM Clientes");
					mostrarNoVips(resultado);
					resultado.close();
				break;
				case 2:
					resultado = stm.executeQuery("SELECT * FROM Clientes");
					mostrarVips(resultado);
					resultado = stm.executeQuery("SELECT * FROM Clientes");
					mostrarNoVips(resultado);
					
					System.out.println("Dime el id del cliente al que le quieras modificar el numero de telefono");
					int id = teclado.nextInt();
					teclado.nextLine();
					System.out.println("Dime el numero nuevo de telefono");
					String telf = teclado.nextLine();
					
					String sql = "UPDATE 'Clientes' SET 'telefono' = ? WHERE 'id' = ?";
					
					PreparedStatement pstm = conexion.prepareStatement(sql);
		            pstm.setString(1, telf);
		            pstm.setInt(2, 1);
					pstm.executeUpdate();
					
					
					resultado.close();
					stm.close();
					pstm.close();
		            conexion.close();
		         break;
				case 0:
					
				break;
				default:
					System.out.println("Selecciona una opcion:");
					System.out.println("0.- Finalizar el programa");
					System.out.println("1.- Listar todos los clientes");
					System.out.println("2.- Modificar el numero de telefono de un cliente a su eleccion");
					opcion = teclado.nextInt();
				break;
				}
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	private static void mostrarVips(ResultSet resultado) throws SQLException {
		ResultSetMetaData rsmd;
		rsmd = resultado.getMetaData();
		
		int numColumnas = rsmd.getColumnCount();
		System.out.println("Se han devuelto " + numColumnas + " columnas");
		
		System.out.println("Todos los datos de la tabla: " + rsmd.getCatalogName(1));
		
		while(resultado.next()) {
			if(resultado.getInt("vip") == 1) {
				for (int i = 1; i <= numColumnas; i++) {
					if (rsmd.getColumnType(i) == Types.INTEGER) {
						System.out.println(rsmd.getColumnLabel(i) + ": " + resultado.getInt(i));
					}else {
						System.out.println(rsmd.getColumnLabel(i) + ": " + resultado.getString(i));
					}
				
				}
				System.out.println("---------------------------------");
			}
			
		}
	}
	
	private static void mostrarNoVips(ResultSet resultado) throws SQLException {
		ResultSetMetaData rsmd;
		rsmd = resultado.getMetaData();
		
		int numColumnas = rsmd.getColumnCount();
		System.out.println("Se han devuelto " + numColumnas + " columnas");
		
		System.out.println("Todos los datos de la tabla: " + rsmd.getCatalogName(1));
		
		while(resultado.next()) {
			if(resultado.getInt("vip") == 0) {
				for (int i = 1; i <= numColumnas; i++) {
					if (rsmd.getColumnType(i) == Types.INTEGER) {
						System.out.println(rsmd.getColumnLabel(i) + ": " + resultado.getInt(i));
					}else {
						System.out.println(rsmd.getColumnLabel(i) + ": " + resultado.getString(i));
					}
				
				}
				System.out.println("---------------------------------");
			}
			
		}
	}

}