package sqlite;

import java.sql.*;
import java.util.Scanner;

public class AccesoSQLite {

	public static void main(String[] args) {
		//Ejercicio 6 del Tema 2
		 
		try {
			Scanner teclado = new Scanner(System.in);
			int opcion = 1;
			// Crear una instancia del JDBC driver .
			Class.forName("org.sqlite.JDBC");
			
			//Especificar la url de la base de datos.
			//Establecer una conexión usando el driver que crea el  objeto Connection.
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:src/sqlite/biblioteca.db");
			
			//Crear un objeto Statement, usando Connection.
			Statement stm = conexion.createStatement();
			
			
			while(opcion != 0) {
				System.out.println("Selecciona una opcion:");
				System.out.println("0.- Finalizar el programa");
				System.out.println("1.- Listar todos los libros");
				System.out.println("2.- Listar todos los socios");
				System.out.println("3.- Listar todos los prestamos");
				System.out.println("4.- Listar todos los libros prestados actualmente");
				System.out.println("5.- Numero de libros prestados a un socio determinado");
				System.out.println("6.- Listar los libros que han superado la fecha de prestamo");
				System.out.println("7.- Listar los socios con libros que han superado la fecha de prestamo");
				opcion = teclado.nextInt();
				
				ResultSet resultado;
				ResultSetMetaData rsmd;
				int cont = 0;
				
				switch(opcion) {
				case 1:
					resultado = stm.executeQuery("SELECT * FROM LIBRO");
					mostrarTablaEntera(resultado);
					resultado.close();
				break;
				case 2:
					resultado = stm.executeQuery("SELECT * FROM SOCIO");
					mostrarTablaEntera(resultado);
					resultado.close();
				break;
				case 3:
					resultado = stm.executeQuery("SELECT * FROM PRESTAMO");
					mostrarTablaEntera(resultado);
					resultado.close();
				break;
				case 4:
					resultado = stm.executeQuery("SELECT Titulo, FechaIniPrestamo, FechaFinPrestamo FROM  PRESTAMO JOIN LIBRO ON CodLibro = Codigo WHERE FechaIniPrestamo <= date() AND FechaFinPrestamo > date()");
					
					System.out.println("Libros prestados actualmente:\n");
					
					while(resultado.next()) {
							
						String titulo = resultado.getString("Titulo");
						String fechai = resultado.getString("FechaIniPrestamo");
						String fechaf = resultado.getString("FechaFinPrestamo");
							
						System.out.println("Titulo: " + titulo);
						System.out.println("Fecha inicio de prestamo: " + fechai);
						System.out.println("Fecha fin de prestamo: " + fechaf);
						System.out.println("");
						cont++;	
						}
					
					if (cont == 0) {
						System.out.println("Actualmente no hay libros prestados\n");
					}
					
					resultado.close();
				break;
				case 5:
					resultado = stm.executeQuery("SELECT * FROM SOCIO");
					mostrarTablaEntera(resultado);
					resultado.close();
					System.out.println("Dime un numero de socio: ");
					int socio = teclado.nextInt();
					
					resultado = stm.executeQuery("SELECT Nombre, Apellidos, count(CodSocio) AS NumLibros FROM PRESTAMO JOIN SOCIO ON CodSocio = Codigo WHERE CodSocio = " + socio + " AND FechaFinPrestamo > date()");
					
					while(resultado.next()) {
						int nLibros = resultado.getInt(3);
						if (nLibros == 0) {
							System.out.println("El socio con codigo = " + socio + " no tiene libros prestados actualmente\n");
						} else {
							String nombre = resultado.getString("Nombre");
							String apellidos = resultado.getString("Apellidos");
							
							System.out.println("Libros prestados al socio con codigo = "+ socio +": ");
							System.out.println("Nombre: " + nombre);
							System.out.println("Apellidos: " + apellidos);
							System.out.println("Numero de libros prestados: " + nLibros);
						}
						
					}
					
					resultado.close();
				break;
				case 6:
					resultado = stm.executeQuery("SELECT Titulo, FechaIniPrestamo, FechaFinPrestamo FROM PRESTAMO JOIN LIBRO ON CodLibro = Codigo WHERE FechaFinPrestamo < date()");
					System.out.println("Libros que han superado la fecha de prestamo:\n");
					
					while(resultado.next()) {
						String titulo = resultado.getString("Titulo");
						String fechai = resultado.getString("FechaIniPrestamo");
						String fechaf = resultado.getString("FechaFinPrestamo");
							
						System.out.println("Titulo: " + titulo);
						System.out.println("Fecha inicio de prestamo: " + fechai);
						System.out.println("fecha fin de prestamo: " + fechaf);
						System.out.println("");
						cont++;
					}
					
					if (cont == 0) {
						System.out.println("De momento no hay ningun libro que haya superado la fecha de prestamo\n");
					}
						
					resultado.close();
				break;
				case 7:
					resultado = stm.executeQuery("SELECT DISTINCT CodSocio, Nombre, Apellidos FROM PRESTAMO JOIN SOCIO ON CodSocio = Codigo WHERE FechaFinPrestamo < date()");
					System.out.println("Socios con libros que han superado la fecha final de prestamo:\n");
					
					while(resultado.next()) {
						String codSocio = resultado.getString("CodSocio");
						String nombre = resultado.getString("Nombre");
						String apellidos = resultado.getString("Apellidos");
						System.out.println("Codigo de socio: "+ codSocio);
						System.out.println("Nombre: "+ nombre);
						System.out.println("Apellidos: " + apellidos);
						System.out.println("");
						cont++;
					}
					
					if (cont == 0) {
						System.out.println("No hay ningun socio que tenga libros que superen la fecha de prestamo\n");
					}
						
					resultado.close();
				break;
				case 0:
					System.out.println("Ha seleccionado la finalizacion del programa");
					stm.close();
					conexion.close();
				break;
				default:
					System.out.println("Selecciona una opcion:");
					System.out.println("0.- Finalizar el programa");
					System.out.println("1.- Listar todos los libros prestados actualmente");
					System.out.println("2.- Numero de libros prestados a un socio determinado");
					System.out.println("3.- Listar los libros que han superado la fecha de prestamo");
					System.out.println("4.- Listar los socios con libros que han superado la fecha de prestamo");
					opcion = teclado.nextInt();
				break;
					
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("No se ha encontrado la clase");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param resultado
	 * @throws SQLException
	 */
	private static void mostrarTablaEntera(ResultSet resultado) throws SQLException {
		ResultSetMetaData rsmd;
		rsmd = resultado.getMetaData();
		
		int numColumnas = rsmd.getColumnCount();
		System.out.println("Se han devuelto " + numColumnas + " columnas");
		
		System.out.println("Todos los datos de la tabla: " + rsmd.getCatalogName(1));
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
