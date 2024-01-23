package app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import controlador.HibernateUtil;
import datos.Cliente;
import datos.Producto;

public class Main {

	public static void main(String[] args) {
		SessionFactory fabrica = HibernateUtil.getSessionFactory();

		Session sesion = fabrica.openSession();

		boolean salir = false;
		int opcion;
		while (!salir) {
			menu();
			opcion = Utilidades.pedirEntero("Seleccione una opcion: ");
			if (opcion > 0 && opcion < 4) {
				switch (opcion) {
					case 1:
						modificarPrecio(sesion);
						break;
					case 2:
						vaciarVentas(sesion);
						break;
					case 0:
						salir = true;
						sesion.close();
						fabrica.close();
						break;

				}
			} else
				System.out.println("EL numero tiene que estar comprendido entre 1 y 3");
		}
	}

	public static void menu() {
		System.out.println("Elige una opcion: ");
			System.out.println("0.- Salir.");
			System.out.println("1.- Modificar el precio de los productos (añadir el IVA).");
			System.out.println("2.- Vaciar las ventas de un cliente.");
	}
	
	public static void modificarPrecio(Session sesion) {

		Transaction tx = sesion.beginTransaction();
		try {

			Query<Producto> qProductos = sesion.createQuery("FROM Producto", Producto.class);
			List<Producto> listaProductos = qProductos.list();

			for (Producto p : listaProductos) {
				float nuevoPrecio = (float) (p.getPrecio() * 1.21);
				p.setPrecio(nuevoPrecio);
				sesion.update(p);
			}

			Utilidades.mostrarEnPantalla("Se han modificado los precios.");
			tx.commit();
		} catch (Exception e) {
			Utilidades.mostrarEnPantalla("Error al modificar los precios: " + e.getMessage());
			tx.rollback();
		}
		
	}

	public static void vaciarVentas(Session sesion) {

		Transaction tx = sesion.beginTransaction();
		try {
			

			Query<Cliente> qClientes = sesion.createQuery("Select cliente FROM Venta ", Cliente.class);
			List<Cliente> listaClientes = qClientes.list();

			Utilidades.mostrarEnPantalla("Lista de clientes:");
			for (Cliente c : listaClientes) {
				Utilidades.mostrarEnPantalla("Nombre: " + c.getNombre() + " con id: " + c.getId());
			}

			int idCliente = Utilidades.pedirEntero("Dime el cliente al que le quieres vaciar las ventas: ");

			Query<?> qVaciarVentas = sesion.createQuery("DELETE FROM Venta WHERE cliente.id = :idCliente");
			qVaciarVentas.setParameter("idCliente", idCliente);
			int filas = qVaciarVentas.executeUpdate();

			if (filas > 0) {
				Utilidades.mostrarEnPantalla("Las ventas del cliente con id: " + idCliente + " han sido vaciadas.");
			} else {
				Utilidades.mostrarEnPantalla("El cliente con id: " + idCliente + " no tiene ventas.");
			}
			tx.commit();
		} catch (Exception e) {
			Utilidades.mostrarEnPantalla("Error al vaciar las ventas: " + e.getMessage());
			tx.rollback();
		}
	}
	
}


