package menuMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Scanner teclado = new Scanner(System.in);
			// CARGAR EL CONTROLADOR JDBC de la base de datos
			Class.forName("com.mysql.jdbc.Driver");

			// ESTABLECER LA CONEXIÓN con la base de datos
			Connection conexion = DriverManager.getConnection("jdbc:mysql://192.168.56.102/biblioteca", "josetomas",
					"Pass!123456");

			// PREPARAMOS LA SENTENCIA SQL
			Statement sentencia = (Statement) conexion.createStatement();

			boolean continuar = true;
			int opcion;
			while (continuar) {
				menu();
				opcion = Utilidades.pedirEntero("Elija una opcion: ");
				if (opcion > 0 && opcion < 7) {
					switch (opcion) {
					case 1:
						System.out.println("1. Leer datos de la base de datos");
						System.out.println("------------------------------");
						GestionMySQL.leerdatos(sentencia);
						break;
					case 2:
						System.out.println("2. Consulta");
						System.out.println("------------------------------");
						GestionMySQL.Consulta(sentencia);
						break;
					case 3:
						System.out.println("3. Modificar un socio");
						System.out.println("------------------------------");
						GestionMySQL.leerdatos(sentencia);
						GestionMySQL.modificarSocio(sentencia);
						GestionMySQL.leerdatos(sentencia);
						break;
					case 4:
						System.out.println("4. Eliminar un socio");
						System.out.println("------------------------------");
						GestionMySQL.leerdatos(sentencia);
						GestionMySQL.EliminarSocio(sentencia);
						GestionMySQL.leerdatos(sentencia);
						break;
					case 5:
						System.out.println("5. Insertar un socio");
						System.out.println("------------------------------");
						GestionMySQL.leerdatos(sentencia);
						GestionMySQL.InsertarSocio(sentencia);
						GestionMySQL.leerdatos(sentencia);
						break;
					case 6:
						System.out.println("6. Salir");
						System.out.println("-----------");
						continuar = false;
						sentencia.close();
						conexion.close();
						break;

					}
				} else
					System.out.println("EL numero introducido tiene que ser entre el 1 y el 4");
			}
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();

		}
	}

	public static void menu() {
		System.out.println("Que es lo que quieres hacer?");
		System.out.println("__________________________________");
		System.out.println("1. Leer datos de la base de datos");
		System.out.println("2. Consulta");
		System.out.println("3. Modificar un socio");
		System.out.println("4. Eliminar un socio");
		System.out.println("5. Insertar un socio");
		System.out.println("6. Salir");
	}

}
