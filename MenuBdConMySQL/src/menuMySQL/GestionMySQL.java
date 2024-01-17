package menuMySQL;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class GestionMySQL {
    
    public static void leerdatos(Statement sentencia) {
        ResultSet resultado;
        try {
            resultado = sentencia.executeQuery("select * from socio");
            ResultSetMetaData rsmd = resultado.getMetaData();
            int numcolumnas = rsmd.getColumnCount();
            
            // Iterar a través de los resultados y mostrarlos en la consola
            while (resultado.next()) {
                for (int i = 1; i <= numcolumnas; i++) {
                    switch (rsmd.getColumnType(i)) {
                        case Types.VARCHAR:
                            System.out.println("Nombre de la columna " + rsmd.getColumnLabel(i) + " = " +
                                    resultado.getString(i));
                            break;
                        case Types.INTEGER:
                            System.out.println("Nombre de la columna " + rsmd.getColumnLabel(i) + " = " +
                                    resultado.getInt(i));
                            break;
                        case Types.DATE:
                            System.out.println("Nombre de la columna " + rsmd.getColumnLabel(i) + " = " +
                                    resultado.getDate(i));
                            break;
                        default:
                            System.out.println("No se de que tipo es");
                    }
                }
            }
            System.out.println("-------------------------");
            resultado.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Consulta(Statement sentencia) {
        ResultSet resultado;

        int idsocio = Utilidades.pedirEntero("Dime el id del socio del que quieres saber si tienen prestamos: ");

        try {
            resultado = sentencia.executeQuery(
                    "SELECT s.Nombre , s.Apellidos, s.Domicilio FROM prestamo p, socio s WHERE p.Csocio = s.Codigo AND s.Codigo = "
                            + idsocio);
            ResultSetMetaData rsmd = resultado.getMetaData();
            int numcolumnas = rsmd.getColumnCount();

            while (resultado.next()) {
                for (int i = 1; i <= numcolumnas; i++) {
                    switch (rsmd.getColumnType(i)) {
                        case Types.VARCHAR:
                            System.out.println("Nombre de la columna " + rsmd.getColumnLabel(i) + " = " +
                                    resultado.getString(i));
                            break;
                        case Types.INTEGER:
                            System.out.println("Nombre de la columna " + rsmd.getColumnLabel(i) + " = " +
                                    resultado.getInt(i));
                            break;
                        case Types.DATE:
                            System.out.println("Nombre de la columna " + rsmd.getColumnLabel(i) + " = " +
                                    resultado.getDate(i));
                            break;
                        case Types.TIMESTAMP:
                            System.out.println("Nombre de la columna " + rsmd.getColumnLabel(i) + " = " +
                                    resultado.getTimestamp(i));
                            break;
                        default:
                            System.out.println("No se de que tipo es");
                    }
                }
                System.out.println("-------------------------");
            }
            resultado.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void modificarSocio(Statement sentencia) {
    	 try {
    	        int codigoSocio = Utilidades.pedirEntero("Introduce el código del socio que deseas modificar: ");
    	        String nuevoNombre = Utilidades.pedirCadena("Introduce el nuevo nombre del socio: ");
    	        String nuevosApellidos = Utilidades.pedirCadena("Introduce los nuevos apellidos del socio: ");
    	        String nuevoDomicilio = Utilidades.pedirCadena("Introduce el nuevo domicilio del socio: ");

    	        // Actualizar los datos del socio en la base de datos
    	        String query = "UPDATE socio SET Nombre = '" + nuevoNombre + "', Apellidos = '" + nuevosApellidos
    	                + "', Domicilio = '" + nuevoDomicilio + "' WHERE Codigo = " + codigoSocio;

    	        int filasAfectadas = sentencia.executeUpdate(query);

    	        if (filasAfectadas > 0) {
    	            System.out.println("Socio modificado correctamente.");
    	        } else {
    	            System.out.println("No se pudo modificar el socio. Verifica el código del socio.");
    	        }
    	       
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	 /* 
    	     * LO MISMO PERO SE MODIFICA CON UN STATEMENT
    	     * try {
    	            int codigoSocio = Utilidades.pedirEntero("Introduce el código del socio que deseas modificar: ");
    	            String nuevoNombre = Utilidades.pedirCadena("Introduce el nuevo nombre del socio: ");
    	            String nuevosApellidos = Utilidades.pedirCadena("Introduce los nuevos apellidos del socio: ");
    	            String nuevoDomicilio = Utilidades.pedirCadena("Introduce el nuevo domicilio del socio: ");

    	            // Actualizar los datos del socio en la base de datos usando PreparedStatement
    	            String query = "UPDATE socio SET Nombre = ?, Apellidos = ?, Domicilio = ? WHERE Codigo = ?";
    	            
    	            preparedStatement.setString(1, nuevoNombre);
    	            preparedStatement.setString(2, nuevosApellidos);
    	            preparedStatement.setString(3, nuevoDomicilio);
    	            preparedStatement.setInt(4, codigoSocio);

    	            int filasAfectadas = preparedStatement.executeUpdate();

    	            if (filasAfectadas > 0) {
    	                System.out.println("Socio modificado correctamente.");
    	            } else {
    	                System.out.println("No se pudo modificar el socio. Verifica el código del socio.");
    	            }
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }*/
    }
    public static void EliminarSocio(Statement sentencia) {
    	try {
            int codigoSocio = Utilidades.pedirEntero("Introduce el código del socio que deseas eliminar: ");

            // Verificar si el socio existe antes de intentar eliminarlo
            if (existeSocio(sentencia, codigoSocio)) {
                // Eliminar el socio de la base de datos
                String query = "DELETE FROM socio WHERE Codigo = " + codigoSocio;
                int filasAfectadas = sentencia.executeUpdate(query);

                if (filasAfectadas > 0) {
                    System.out.println("Socio eliminado correctamente.");
                } else {
                    System.out.println("No se pudo eliminar el socio. Verifica el código del socio.");
                }
            } else {
                System.out.println("El socio con código " + codigoSocio + " no existe en la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static boolean existeSocio(Statement sentencia, int codigoSocio) throws SQLException {
        String query = "SELECT COUNT(*) FROM socio WHERE Codigo = " + codigoSocio;
        try (ResultSet resultSet = sentencia.executeQuery(query)) {
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        }
    }
    public static void InsertarSocio(Statement sentencia) {
    	 try {
             // Obtener los datos del nuevo socio
             String nuevoNombre = Utilidades.pedirCadena("Introduce el nombre del nuevo socio: ");
             String nuevosApellidos = Utilidades.pedirCadena("Introduce los apellidos del nuevo socio: ");
             String nuevoDomicilio = Utilidades.pedirCadena("Introduce el domicilio del nuevo socio: ");

             // Insertar el nuevo socio en la base de datos
             String query = "INSERT INTO socio (Nombre, Apellidos, Domicilio) VALUES ('"
                     + nuevoNombre + "', '" + nuevosApellidos + "', '" + nuevoDomicilio + "')";

             int filasAfectadas = sentencia.executeUpdate(query);

             if (filasAfectadas > 0) {
                 System.out.println("Socio insertado correctamente.");
             } else {
                 System.out.println("No se pudo insertar el nuevo socio. Verifica los datos proporcionados.");
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
    	 /*
    	  * LO MISMO PERP CON PREPARED STATEMENT
    	  * try {
            // Obtener los datos del nuevo socio
            String nuevoNombre = Utilidades.pedirCadena("Introduce el nombre del nuevo socio: ");
            String nuevosApellidos = Utilidades.pedirCadena("Introduce los apellidos del nuevo socio: ");
            String nuevoDomicilio = Utilidades.pedirCadena("Introduce el domicilio del nuevo socio: ");

            // Insertar el nuevo socio en la base de datos usando PreparedStatement
            String query = "INSERT INTO socio (Nombre, Apellidos, Domicilio) VALUES (?, ?, ?)";

            preparedStatement.setString(1, nuevoNombre);
            preparedStatement.setString(2, nuevosApellidos);
            preparedStatement.setString(3, nuevoDomicilio);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Socio insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el nuevo socio. Verifica los datos proporcionados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	  */
    }
    
}
