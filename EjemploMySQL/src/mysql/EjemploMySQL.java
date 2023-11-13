package mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class EjemploMySQL {
    public static void main(String[] args) {
        try {
        	// CARGAR EL CONTROLADOR JDBC de la base de datos
        	Class.forName("com.mysql.jdbc.Driver");
        	
        	// ESTABLECER LA CONEXIÓN con la base de datos
            Connection conexion = DriverManager.getConnection("jdbc:mysql://192.168.56.102/Tienda", "javier", "Pass!123456");
            // parametro 1 = Driver que utilizamos y ruta y nombre de la base de datos
            //				jdbc:sqlite:C:\\Users\\Eva Royo\\Documents\\BBDD\\sqlite\\biblioteca.db
            // parametro 2 = nombre del usuario
            // parametro 3 = contraseña del usuario
            
            
            // PREPARAMOS LA SENTENCIA SQL
            Statement sentencia = (Statement) conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM CLIENTE");
            
            // recorro el resultado
            while (resultado.next()) {
            	System.out.println(resultado.getInt(1) + "-"+ resultado.getString(2));
            }
            
            // LIBRERAR LOS RECURSOS
            resultado.close();
            sentencia.close();
            conexion.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error en la conexión de la base de datos");
            ex.printStackTrace();
        }
    }
}