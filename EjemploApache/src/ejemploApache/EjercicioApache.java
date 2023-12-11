package ejemploApache;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class EjercicioApache {

	public static void main(String[] args) {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection conexion = DriverManager.getConnection("jdbc:derby:ejbiblio.db");
			Statement stm = conexion.createStatement();
			
			ResultSet resultado = stm.executeQuery("SELECT * FROM SOCIO");
			
			ResultSetMetaData rsmd = resultado.getMetaData();
			
			int numColumnas = rsmd.getColumnCount();
			System.out.println("Se han devuelto " + numColumnas + " columnas");
			
			System.out.println("Nombre de la tabla: " + rsmd.getCatalogName(1));
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
			
			resultado.close();
			stm.close();
			conexion.close();
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
