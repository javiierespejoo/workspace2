package sqlite;

import java.sql.*;

public class AccesoSQLite {

	public static void main(String[] args) {
		 
		try {
			// Crear una instancia del JDBC driver .
			Class.forName("org.sqlite.JDBC");
			
			//Especificar la url de la base de datos.
			//Establecer una conexión usando el driver que crea el  objeto Connection.
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:src/sqlite/biblioteca.db");
			
			//Crear un objeto Statement, usando Connection.
			Statement stm = conexion.createStatement();
			
			/*
			ResultSet resultado = stm.executeQuery("SELECT Titulo, FechaIniPrestamo, FechaFinPrestamo FROM  PRESTAMO JOIN LIBRO ON CodLibro = Codigo WHERE FechaIniPrestamo <= date() AND FechaFinPrestamo > date()");
			
			System.out.println("Libros prestados actualmente:\n");
			while(resultado.next()) {
				
				String titulo = resultado.getString("Titulo");
				String fechai = resultado.getString("FechaIniPrestamo");
				String fechaf = resultado.getString("FechaFinPrestamo");
				
				System.out.println("Titulo: " + titulo);
				System.out.println("Fecha inicio de prestamo: " + fechai);
				System.out.println("Fecha fin de prestamo: " fechaf);
				
			}
			
			
			ResultSet resultado = stm.executeQuery("SELECT Nombre, Apellidos, count(CodSocio) AS NumLibros FROM PRESTAMO JOIN SOCIO ON CodSocio = Codigo WHERE CodSocio = 1 AND FechaFinPrestamo > date()");
			
			while(resultado.next()) {
				String nombre = resultado.getString("Nombre");
				String apellidos = resultado.getString("Apellidos");
				int nLibros = resultado.getInt(3);
				System.out.println("Libros prestados al socio con codigo = 1: ");
				System.out.println("Nombre: " + nombre);
				System.out.println("Apellidos: " + apellidos);
				System.out.println("Numero de libros prestados: " + nLibros);
			}
			
			
			ResultSet resultado = stm.executeQuery("SELECT Titulo, FechaIniPrestamo, FechaFinPrestamo FROM PRESTAMO JOIN LIBRO ON CodLibro = Codigo WHERE FechaFinPrestamo < date()");
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
			*/
			
			ResultSet resultado = stm.executeQuery("SELECT DISTINCT CodSocio, Nombre, Apellidos FROM PRESTAMO JOIN SOCIO ON CodSocio = Codigo WHERE FechaFinPrestamo < date()");
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
			stm.close();
			conexion.close();
			
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
