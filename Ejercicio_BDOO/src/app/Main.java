package app;

import java.sql.Date;
import java.util.HashSet;
import java.util.Scanner;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import datos.Autor;
import datos.Departamento;
import datos.Empleado;
import datos.Libro;

public class Main {
	static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		boolean salir = false;
		int opcion;
		crearPersonal();
		
		while (!salir) {
			menu();
			opcion = Utilidades.pedirEntero("");
			if (opcion >= 0 && opcion < 7) {
				switch (opcion) {
					case 1:
						insertarLibro();
					break;
					case 2:
						insertarAutor();
					break;
					case 3:
						añadirAutor();
					break;
					case 4:
						empleadosJefe();
					break;
					case 5:
						empleadosVentas();
					break;
					case 6:
						empleadosDepartamentos();
					break;
					case 0:
						salir = true;
					break;
				}
			} else {
				System.out.println("El numero tiene que estar comprendido entre 0 y 6");
			}
		}
	}
	
	public static void menu() {
		System.out.println("Seleccione una opcion:");
		System.out.println("0.- Salir.");
		System.out.println("1.- Insertar un libro, sin especificar ningun autor.");
		System.out.println("2.- Insertar un autor, sin especificar ningun libro.");
		System.out.println("3.- Modificar un libro para añadirle un autor existente de la bbdd.");
		System.out.println("4.- Obtener los nombres de los empleados cuyo jefe es Pedro Lopez.");
		System.out.println("5.- Obtener el numero de empleados del departamento de Ventas.");
		System.out.println("6.- Obtener el numero de empleados de cada departamento (OPCIONAL).");
	}

	public static void insertarLibro() {
		ODB baseDatos = ODBFactory.open("bbdd/Biblioteca", "javier", "Pass!123456");
		
		String titulo = Utilidades.pedirCadena("Dime el Titulo");
		
		
		
		String editorial = Utilidades.pedirCadena("Dime su editorial");
		
		int paginas = Utilidades.pedirEntero("Dime su numero de paginas");
		
		int anyo = Utilidades.pedirEntero("Dime su año de publicacion");
		
		Libro l = new Libro(titulo, anyo, editorial, paginas, null);
		baseDatos.store(l);
		baseDatos.commit();
		baseDatos.close();
	}
	
	public static void insertarAutor() {
		ODB baseDatos = ODBFactory.open("bbdd/Biblioteca", "javier", "Pass!123456");
		
			String nombre = Utilidades.pedirCadena("Dime su nombre");
		
			String apellidos = Utilidades.pedirCadena("Dime su apellido");
		
			String nacionalidad = Utilidades.pedirCadena("Dime su nacionalidad");
		
			int edad = Utilidades.pedirEntero("Dime su edad");

		
			Autor a = new Autor(nombre, apellidos, nacionalidad, edad, null);
			baseDatos.store(a);
			baseDatos.commit();
			baseDatos.close();
	}
	
	public static void añadirAutor() {
		ODB baseDatos = ODBFactory.open("bbdd/Biblioteca", "javier", "Pass!123456");
		
		IQuery qLibros = new CriteriaQuery(Libro.class);
		Objects<Libro> listaLibros = baseDatos.getObjects(qLibros);
		
		for(Libro l : listaLibros) {
			System.out.println(l.toString());
			System.out.println("-----------------------------");
		}
		
		System.out.println("Seleccione un libro al que añadirle un autor");
		String titulo = Utilidades.pedirCadena("Introduce el titulo");
		
		IQuery qLibroModificar = new CriteriaQuery(Libro.class, Where.equal("titulo",titulo));
		Objects<Libro> libros = baseDatos.getObjects(qLibroModificar);
		Libro libro = libros.getFirst();
		
		IQuery qAutores = new CriteriaQuery(Autor.class);
		Objects<Autor> listaAutores = baseDatos.getObjects(qAutores);
		
		for(Autor a : listaAutores) {
			System.out.println(a.toString());
			System.out.println("-----------------------------");
		}
		
		System.out.println("Seleccione un autor para asignarle al libro");
		String autor = Utilidades.pedirCadena("Introduce el nombre");
		
		IQuery qAutorAnadir = new CriteriaQuery(Autor.class, Where.equal("nombre", autor));
		Objects<Autor> autorAnadir = baseDatos.getObjects(qAutorAnadir);
		Autor autor2 = autorAnadir.getFirst();
		
		if(libro.getAutores() == null) {
			libro.setAutores(new HashSet<>());
			libro.getAutores().add(autor2);
		}
		
		baseDatos.store(libro);
		baseDatos.commit();
		baseDatos.close();
		
	}
	
	public static void empleadosJefe() {
		ODB baseDatos = ODBFactory.open("bbdd/Personal", "javier", "Pass!123456");
		
		IQuery qEmpleadosJefe = new CriteriaQuery(Empleado.class, Where.equal("jefe.apellido","Lopez"));
		Objects<Empleado> listaEmpleados = baseDatos.getObjects(qEmpleadosJefe);
		
		System.out.println("Estos son los empleados a cargo del Sr. Lopez: \n");
		for(Empleado e : listaEmpleados) {
				System.out.println(e.toString());
				System.out.println("-----------------------------");	
		}
		
		baseDatos.close();
		
	}
	
	public static void empleadosVentas() {
		ODB baseDatos = ODBFactory.open("bbdd/Personal", "javier", "Pass!123456");
		
		IQuery qEmpleadosVentas = new CriteriaQuery(Empleado.class, Where.equal("departamento.nombre","Ventas"));
		Objects<Empleado> listaEmpleadosVentas = baseDatos.getObjects(qEmpleadosVentas);
		
		System.out.print("Estos son los empleados del departamento de Ventas: \n");
		int cont = 0;
		for(Empleado e : listaEmpleadosVentas) {
				cont++;	
		}
		System.out.print(cont);
		System.out.println();
		
		baseDatos.close();
	}
	
	public static void empleadosDepartamentos() {
		ODB baseDatos = ODBFactory.open("bbdd/Personal", "javier", "Pass!123456");
		
		IQuery qDepartamentos = new CriteriaQuery(Departamento.class);
		Objects<Departamento> listaDeps = baseDatos.getObjects(qDepartamentos);
		
		for(Departamento d : listaDeps) {
			IQuery qEmpleadosDep = new CriteriaQuery(Empleado.class, Where.equal("departamento.nombre", d.getNombre()));
			Objects<Empleado> listaEmpleadosDep = baseDatos.getObjects(qEmpleadosDep);
			
			System.out.println(d.getNombre() + ": " + listaEmpleadosDep.size());
			System.out.println("-----------------------------");
			
				
		}
		
		baseDatos.close();
	}
	
	public static void crearPersonal() {
		ODB baseDatos = ODBFactory.open("bbdd/Personal", "javier", "Pass!123456");
		Departamento ventas = new Departamento(1, "Ventas", "Zaragoza");
		Departamento logistica = new Departamento(2, "Logistica", "Zaragoza");
		Departamento rrhh = new Departamento(3, "RRHH", "Zaragoza");
		
		baseDatos.store(ventas);
		baseDatos.store(logistica);
		baseDatos.store(rrhh);
		
		Empleado e1 = new Empleado(1, "Espejo", "CEO", new Date(2002/07/29), 2000.0f, 1000.0f, null, null);
		Empleado e2 = new Empleado(2, "Lopez", "jefe ventas", new Date(2003/10/9), 1500.0f, 200.0f, e1, ventas);
		Empleado e3 = new Empleado(3, "Nebra", "jefe rrhh", new Date(2002/12/10), 1500.0f, 200.0f, e1, rrhh);
		Empleado e4 = new Empleado(4, "Rodriguez", "jefe logistica", new Date(2004/06/3), 1500.0f, 200.0f, e1, logistica);
		Empleado e5 = new Empleado(5, "Molina", "oficinista", new Date(2005/01/20), 1000.0f, 100.0f, e2, ventas);
		Empleado e6 = new Empleado(6, "Royo", "Limpiador", new Date(2007/03/12), 1000.0f, 100.0f, e2, ventas);
		Empleado e7 = new Empleado(7, "Rioja", "Contratadora", new Date(2009/11/15), 1000.0f, 100.0f, e3, rrhh);
		Empleado e8 = new Empleado(8, "Dominguez", "Despedidora", new Date(2010/02/23), 1000.0f, 100.0f, e3, rrhh);
		Empleado e9 = new Empleado(9, "Burges", "camionero", new Date(2008/12/6), 1200.0f, 150.0f, e4, logistica);
		Empleado e10 = new Empleado(10, "Buedo", "reponedor", new Date(2012/04/5), 1200.0f, 100.0f, e4, logistica);
		baseDatos.store(e1);
		baseDatos.store(e2);
		baseDatos.store(e3);
		baseDatos.store(e4);
		baseDatos.store(e5);
		baseDatos.store(e6);
		baseDatos.store(e7);
		baseDatos.store(e8);
		baseDatos.store(e9);
		baseDatos.store(e10);
		baseDatos.commit();
		baseDatos.close();
	}
	
	
}
















