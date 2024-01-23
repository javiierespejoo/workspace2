package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import controlador.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Realiza un programa Java que utilice Hibernate para ejecutar las siguientes
		 * operaciones de gestión de objetos mapeados a partir de la base de datos
		 * personal:
		 * 
		 * En cada apartado, añade las comprobaciones necesarias en caso de errores,
		 * visualiza en pantalla los mensajes apropiados para todas las situaciones y
		 * también los atributos de los objetos manipulados en Java.
		 * 
		 */
		// ------------------UTILIZAMOS LO DEFINIDO ANTES-------------
		// obtener la f�brica de la conexi�n actual para crear una sesi�n
		SessionFactory fabrica = HibernateUtil.getSessionFactory();
		// ------------------------------------------------------------
		// creamos la sesi�n
		Session sesion = fabrica.openSession();
		// creamos la transacci�n de la sesi�n
		

		boolean continuar = true;
		int opcion;
		while (continuar) {
			menu();
			opcion = Utilidades.pedirEntero("Elija una opcion: ");
			if (opcion > 0 && opcion < 4) {
				switch (opcion) {
					case 1:
						System.out.println("1. Modificar el precio de los productos.(Incluye IVA)");
						System.out.println("------------------------------");
						GestionHib.modificarPrecio(sesion);
						break;
					case 2:
						System.out.println("2. Eliminar las ventas.");
						System.out.println("------------------------------");
						GestionHib.eliminarVentas(sesion);
						break;
					case 3:
						System.out.println("3. Salir");
						System.out.println("-----------");
						continuar = false;
						sesion.close();
						fabrica.close();
						break;

				}
			} else
				System.out.println("EL numero introducido tiene que ser entre el 1 y el 3");
		}
	}

	public static void menu() {
		System.out.println("Elige una opcion: ");
			System.out.println("0.- Salir.");
			System.out.println("1.- Modificar el precio de todos los productos para que incluyan el 21% de IVA.");
			System.out.println("2.- Eliminar las ventas realizadas por un cliente a elegir");
	}
	
	public static void modificarPrecio(Session sesion) {
		// Modificar el precio de todos los productos de forma que incluyan un IVA del
		// 21%.
		Transaction tx = sesion.beginTransaction();
		try {
			// Consulta para obtener todos los productos
			Query<Producto> query = sesion.createQuery("FROM Producto", Producto.class);
			List<Producto> productos = query.list();

			// Modificar el precio de cada producto aplicando un IVA del 21%
			for (Producto producto : productos) {
				float nuevoPrecio = (float) (producto.getPrecio() * 1.21);
				producto.setPrecio(nuevoPrecio);
				sesion.update(producto);
			}

			Utilidades.mostrarEnPantalla("Precios modificados con IVA del 21%.");
			tx.commit();
		} catch (Exception e) {
			Utilidades.mostrarEnPantalla("Error al modificar los precios: " + e.getMessage());
			tx.rollback();
		}
		
	}

	public static void eliminarVentas(Session sesion) {
		// Eliminar las ventas realizadas por el cliente que el usuario elija
		// (para que el usuario sepa qué cliente elegir, la aplicación deberá mostrar el
		// nombre y el id del cliente antes)
		Transaction tx = sesion.beginTransaction();
		try {
			
			// Consulta para obtener todos los clientes
			Query<Cliente> queryClientes = sesion.createQuery("Select cliente FROM Venta ", Cliente.class);
			List<Cliente> clientes = queryClientes.list();

			// Mostrar la lista de clientes al usuario
			Utilidades.mostrarEnPantalla("Lista de clientes:");
			for (Cliente cliente : clientes) {
				Utilidades.mostrarEnPantalla("ID: " + cliente.getId() + ", Nombre: " + cliente.getNombre());
			}

			// Solicitar al usuario que elija un cliente por ID
			int idCliente = Utilidades.pedirEntero("Introduce el ID del cliente cuyas ventas deseas eliminar:");

			// Consulta para eliminar las ventas del cliente seleccionado
			Query<?> queryEliminarVentas = sesion.createQuery("DELETE FROM Venta WHERE cliente.id = :idCliente");
			queryEliminarVentas.setParameter("idCliente", idCliente);
			int filasAfectadas = queryEliminarVentas.executeUpdate();

			if (filasAfectadas > 0) {
				Utilidades.mostrarEnPantalla("Ventas del cliente eliminadas correctamente.");
			} else {
				Utilidades.mostrarEnPantalla("No se encontraron ventas para el cliente con ID: " + idCliente);
			}
			tx.commit();
		} catch (Exception e) {
			Utilidades.mostrarEnPantalla("Error al eliminar las ventas: " + e.getMessage());
			tx.rollback();
		}
	}
	
}


