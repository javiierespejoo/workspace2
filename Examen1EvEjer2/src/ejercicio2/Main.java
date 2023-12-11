package ejercicio2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://192.168.56.102/sindicatos", "javier", "Pass!123456");
			
			System.out.println("Estos son todos los socios de la biblioteca:\n");
			Statement stm = (Statement) conexion.createStatement();
			ResultSet resultado = stm.executeQuery("SELECT * FROM NegociacionesColectivas");
			mostrarTablaEntera(resultado);
			resultado.close();
			
			String opcion = "5";
			int op = Integer.parseInt(opcion);
			
			while(op != 0) {
				System.out.println("Que quieres hacer: ");
				System.out.println("1.- Modificar una negociación colectiva");
				System.out.println("2.- Mostrar los socios que hay en una negociacion sin acuerdo alcanzado");
				System.out.println("0.- Salir");
				while(true) {
					try {
						opcion = teclado.nextLine();
						op = Integer.parseInt(opcion);
						break;
					}catch(NumberFormatException e) {
						System.err.println("Error: Debes introducir un numero entero");
					}
				}
				
				switch(op) {
				case 1:
					modificarNegociacion(teclado, conexion, stm);
				break;
				case 2:
					System.out.println("Estos son todos los socios de la biblioteca:\n");
					String cod;
					int id;
					while(true) {
						try {
							cod = teclado.nextLine();
							id = Integer.parseInt(cod);
							break;
						} catch (NumberFormatException e) {
							System.err.println("Error: Debes introducir un numero entero");
							teclado.next();
						}

					}
					resultado = stm.executeQuery("SELECT * FROM Trabajadores JOIN NegociacionesColectivas ON Trabajadores.sindicato_id = NegociacionesColectivas.sindicato_id WHERE NegociacionesColectivas.id = " + id);
					mostrarTablaEntera(resultado);
					resultado.close();
				break;
				case 0:
					stm.close();
					conexion.close();
				break;
				default:
					System.out.println("Que quieres hacer: ");
					System.out.println("1.- Modificar una negociación colectiva");
					System.out.println("2.- Mostrar los socios que hay en una negociacion sin acuerdo alcanzado");
					System.out.println("0.- Salir");
					while(true) {
						try {
							opcion = teclado.nextLine();
							op = Integer.parseInt(opcion);
							break;
						}catch(NumberFormatException e) {
							System.err.println("Error: Debes introducir un numero entero");
						}
					}
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
	
	private static void modificarNegociacion(Scanner teclado, Connection conexion, Statement stm) throws SQLException {
		String sql = "UPDATE `NegociacionColectiva` SET `fecha_fin`= ?,`resultado`= ? WHERE id = ?";
		PreparedStatement pstm = conexion.prepareStatement(sql);
		
		boolean existe = false;
		
		while(!existe) {
			System.out.println("Dime el id de la negociacion que quieras modificar");
			String cod;
			int id;
			while(true) {
				try {
					cod = teclado.nextLine();
					id = Integer.parseInt(cod);
					break;
				} catch (NumberFormatException e) {
					System.err.println("Error: Debes introducir un numero entero");
					teclado.next();
				}

			}
			
			ResultSet existeNegociacion = stm.executeQuery("SELECT id FROM NegociacionColectiva WHERE id = " + id);
			if(!existeNegociacion.next()) {
			System.out.println("La negociacion introducida no existe");
			} else {
				pstm.setInt(3, id);
			
				System.out.println("¿Cual es la nueva fecha final de la negociacion?");
				String ffin = teclado.nextLine();
				pstm.setString(1, ffin);
			
				System.out.println("¿Cual es el nuevo resultado de la negociacion?");
				String resultado = teclado.nextLine();
				pstm.setString(2, resultado);
			
				int filas = pstm.executeUpdate();
				System.out.println("Filas modificadas: " + filas);
				existeNegociacion.close();
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
