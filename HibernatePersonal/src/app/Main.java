package app;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import controlador.HibernateUtil;
import datos.Departamento;
import datos.Empleado;


public class Main {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		SessionFactory fabrica;
		Session sesion;
		Transaction tx;
		
		String opcion = "5";
		int op = Integer.parseInt(opcion);
		
		while(op != 0) {
			System.out.println("Elige una opcion del menu:");
			System.out.println("1.- Modificar un departamento");
			System.out.println("2.- Insertar un departamento");
			System.out.println("3.- Leer un empleado y su departamento");
			System.out.println("4.- Eliminar un empleado");
			System.out.println("5.- Eliminar un departamento");
			System.out.println("0.- Salir");
			while(true) {
				try {
					opcion = teclado.nextLine();
					op = Integer.parseInt(opcion);
					break;
				} catch (NumberFormatException e) {
					System.err.println("Error: Debes introducir un numero entero");
				}
			}
			
			switch(op) {
			case 1:
				fabrica = HibernateUtil.getSessionFactory();
				
				sesion = fabrica.openSession();	
				
				tx = sesion.beginTransaction();
				
				System.out.println("Estos son los departamentos");
				Query<Departamento> q = sesion.createQuery("from Departamento");
				List <Departamento> lista = q.list();
				Iterator <Departamento> iter = lista.iterator();
				
				System.out.println("Numero de registros:"  + lista.size());
				while (iter.hasNext())
				{
				   
					Departamento dep = (Departamento) iter.next(); 
					System.out.println("ID departamento = " + dep.getIdDep() + " Nombre =" + dep.getNombre());		   
				}
				try {
					String num;
					int numDep;
				System.out.println("Que departamento quieres modificar: ");
				while(true) {
					try {
						num = teclado.nextLine();
						numDep = Integer.parseInt(opcion);
						break;
					} catch (NumberFormatException e) {
						System.err.println("Error: Debes introducir un numero entero");
					}
				}
				
				
					Departamento dep = (Departamento)sesion.get(Departamento.class, numDep);
					System.out.println("Dime su nuevo nombre");
					String nombre = teclado.nextLine();
					dep.setNombre(nombre);
					
					System.out.println("Dime su nueva localidad");
					String localidad = teclado.nextLine();
					dep.setLocalidad(localidad);
					
					sesion.update(dep);
					tx.commit();
				}catch(IllegalArgumentException e) {
					e.printStackTrace();
					tx.rollback();
				} finally {
					sesion.close();
					fabrica.close();
				}
			break;
			case 2:
				fabrica = HibernateUtil.getSessionFactory();
				
				sesion = fabrica.openSession();	
				
				tx = sesion.beginTransaction();
				try {
					Departamento dep = new Departamento();
					System.out.println("Dime el ID del departamento");
					Byte id = teclado.nextByte();
					dep.setIdDep(id);
					
					System.out.println("Dime su nuevo nombre");
					String nombre = teclado.nextLine();
					dep.setNombre(nombre);
					
					System.out.println("Dime su nueva localidad");
					String localidad = teclado.nextLine();
					dep.setLocalidad(localidad);
					Set e = null;
					dep.setEmpleados(e);
					
					dep = new Departamento(id, nombre, localidad, e);
					sesion.saveOrUpdate(dep);
					
					tx.commit();
				} catch(IllegalArgumentException e) {
					e.printStackTrace();
					tx.rollback();
				} finally {
					sesion.close();
					fabrica.close();
				}
				
			break;
			case 3:
				
			break;
			}
		}

	}

}
