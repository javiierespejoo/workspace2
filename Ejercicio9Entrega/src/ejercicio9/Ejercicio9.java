package ejercicio9;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Ejercicio9 {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://192.168.56.102/biblioteca", "javier", "Pass!123456");
			
			System.out.println("Estos son todos los socios de la biblioteca:\n");
			
			Statement stm = (Statement) conexion.createStatement();
			ResultSet resultado = stm.executeQuery("SELECT * FROM socio");
			mostrarTablaEntera(resultado);
			resultado.close();
			
			String opcion = "5";
			int op = Integer.parseInt(opcion);
			
			while(op != 0) {
				System.out.println("¿Que quieres hacer?");
				System.out.println("1.- Modificar un empleado");
				System.out.println("0.- Salir");
				while(true) {
					try {
						opcion = teclado.nextLine();
						op = Integer.parseInt(opcion);
						break;
					} catch (NumberFormatException e) {
						System.err.println("Error: Debes introducir un numero entero");
					}
				}
				
				switch(op) {
				case 1:
					modificarSocio(teclado, conexion, stm);
				break;
				case 0:
					stm.close();
					System.out.println("Ha seleccionado salir del programa");
					break;
				default:

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

	/**
	 * @param teclado
	 * @param conexion
	 * @param stm
	 * @throws SQLException
	 */
	private static void modificarSocio(Scanner teclado, Connection conexion, Statement stm) throws SQLException {
		String sql = "UPDATE `socio` SET `Domicilio`= ?,`Telefono`= ? WHERE Codigo = ?";
		PreparedStatement pstm = conexion.prepareStatement(sql);
		
		boolean existe = false;
		
		while(!existe) {
			System.out.println("Dime el codigo del socio que quieras modificar");
			int cod;
			while(true) {
				try {
					cod = teclado.nextInt(); teclado.nextLine();
					break;
				} catch (InputMismatchException e) {
					System.err.println("Error: Debes introducir un numero entero");
					teclado.next();
				}

			}
			
			ResultSet existeSocio = stm.executeQuery("SELECT Codigo FROM socio WHERE socio.Codigo = " + cod);
			if(!existeSocio.next()) {
			System.out.println("El empleado introducido no existe");
			} else {
				pstm.setInt(3, cod);
			
				System.out.println("¿Cual es su nuevo domicilio?");
				String dom = teclado.nextLine();
				pstm.setString(1, dom);
			
				System.out.println("¿Cual es su nuevo telefono?");
				String telf = teclado.nextLine();
				pstm.setString(2, telf);
			
				int filas = pstm.executeUpdate();
				System.out.println("Filas modificadas: " + filas);
				existeSocio.close();
				pstm.close();
				existe = true;
			}
		}
	}
	
	private static void mostrarTablaEntera(ResultSet resultado) throws SQLException {
		ResultSetMetaData rsmd;
		rsmd = resultado.getMetaData();
		
		int numColumnas = rsmd.getColumnCount();
		System.out.println("Se han devuelto " + numColumnas + " columnas");
		
		System.out.println("Todos los datos de la tabla: " + rsmd.getCatalogName(1) + "\n");
		while(resultado.next()) {
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
