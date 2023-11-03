package sqlite;

import java.sql.*;
import java.util.Scanner;

public class AccesoSQLite {

	public static void main(String[] args) {
		 
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
				System.out.println("1.- Listar todos los libros prestados actualmente");
				System.out.println("2.- Numero de libros prestados a un socio determinado");
				System.out.println("3.- Listar los libros que han superado la fecha de prestamo");
				System.out.println("4.- Listar los socios con libros que han superado la fecha de prestamo");
				opcion = teclado.nextInt();
				
				switch(opcion) {
				case 1:
					ResultSet resultado = stm.executeQuery("SELECT Titulo, FechaIniPrestamo, FechaFinPrestamo FROM  PRESTAMO JOIN LIBRO ON CodLibro = Codigo WHERE FechaIniPrestamo <= date() AND FechaFinPrestamo > date()");
					
					System.out.println("Libros prestados actualmente:\n");
					while(resultado.next()) {
						
						String titulo = resultado.getString("Titulo");
						String fechai = resultado.getString("FechaIniPrestamo");
						String fechaf = resultado.getString("FechaFinPrestamo");
						
						System.out.println("Titulo: " + titulo);
						System.out.println("Fecha inicio de prestamo: " + fechai);
						System.out.println("Fecha fin de prestamo: " + fechaf);
						System.out.println("");
						
					}
					resultado.close();
				break;
				case 2:
					System.out.println("Dime un numero de socio: ");
					int socio = teclado.nextInt();
					
					resultado = stm.executeQuery("SELECT Nombre, Apellidos, count(CodSocio) AS NumLibros FROM PRESTAMO JOIN SOCIO ON CodSocio = Codigo WHERE CodSocio = "+ socio +" AND FechaFinPrestamo > date()");
					while(resultado.next()) {
						String nombre = resultado.getString("Nombre");
						String apellidos = resultado.getString("Apellidos");
						int nLibros = resultado.getInt(3);
						System.out.println("Libros prestados al socio con codigo = "+ socio +": ");
						System.out.println("Nombre: " + nombre);
						System.out.println("Apellidos: " + apellidos);
						System.out.println("Numero de libros prestados: " + nLibros);
					}
					resultado.close();
				break;
				case 3:
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
					}
					resultado.close();
				break;
				case 4:
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

}
