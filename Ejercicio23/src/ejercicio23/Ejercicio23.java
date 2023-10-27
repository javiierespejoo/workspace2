package ejercicio23;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Ejercicio23 {
	
	static Scanner teclado = new Scanner(System.in);
	static ArrayList<Empleado> listaEmpleados = new ArrayList<>();
	static String dni, nombre, apellido1, apellido2;
	static int base, complementos;
	static float irpf;
	
	public static void main(String[] args) {

		int opcion = 1;
		
		
		
		while (opcion == 1) {
			System.out.println("¿Quieres añadir un empleado?");
			System.out.println("1.- Si");
			System.out.println("2.- No");
			opcion = teclado.nextInt();
			
			switch(opcion) {
				case 1:
					añadirEmpleado();
					System.out.println("Se ha añadido un nuevo empleado");
				break;
				case 2:
					escribirEmpleados();
					System.out.println("Se han escrito todos los empleados");
				break;
		
			}
		}

	}
	
	private static void añadirEmpleado() {
		System.out.println("Dime su DNI");
		teclado.nextLine();
		dni = teclado.nextLine();
		System.out.println("Dime su nombre");
		nombre = teclado.nextLine();
		System.out.println("Dime su primer apellido");
		apellido1 = teclado.nextLine();
		System.out.println("Dime su segundo apellido");
		apellido2 = teclado.nextLine();
		System.out.println("Dime su sueldo base");
		base = teclado.nextInt();
		System.out.println("Dime los complementos de su sueldo");
		complementos = teclado.nextInt();
		System.out.println("Dime el IRPF");
		irpf = teclado.nextFloat();
		
		Empleado e = new Empleado(dni, nombre, apellido1, apellido2, base, complementos, irpf);
		listaEmpleados.add(e);
	}
	
	private static void escribirEmpleados() {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factoria.newDocumentBuilder();
			DOMImplementation implementatio = builder.getDOMImplementation();
			Document document = implementatio.createDocument(null, "Empleados", null);
			document.setXmlVersion("1.0");

			
			for (Empleado e : listaEmpleados) {
				Element raiz = document.createElement("empleado");
				document.getDocumentElement().appendChild(raiz);
				
				Element elem = document.createElement("dni");
				Text text = document.createTextNode(e.getDni());
				raiz.appendChild(elem);
				elem.appendChild(text);

				elem = document.createElement("nombre");
				text = document.createTextNode(e.getNombre());
				raiz.appendChild(elem);
				elem.appendChild(text);
				
				elem = document.createElement("apellido1");
				text = document.createTextNode(e.getApellido1());
				raiz.appendChild(elem);
				elem.appendChild(text);
				
				elem = document.createElement("apellido2");
				text = document.createTextNode(e.getApellido2());
				raiz.appendChild(elem);
				elem.appendChild(text);
				
				Element elem_sueldo = document.createElement("sueldo");
				raiz.appendChild(elem_sueldo);
				
				elem = document.createElement("base");
				text = document.createTextNode(Integer.toString(e.getBase()));
				elem_sueldo.appendChild(elem);
				elem.appendChild(text);
				
				elem = document.createElement("complementos");
				text = document.createTextNode(Integer.toString(e.getComplementos()));
				elem_sueldo.appendChild(elem);
				elem.appendChild(text);
				
				elem = document.createElement("irpf");
				text = document.createTextNode(Float.toString(e.getIrpf()));
				elem_sueldo.appendChild(elem);
				elem.appendChild(text);
				
			}
			
		      TransformerFactory xformFactory = TransformerFactory.newInstance();  
		      Transformer idTransform = xformFactory.newTransformer();
		      Source input = new DOMSource(document);
		      Result output = new StreamResult(new File("src//ejercicio23//empleados.xml"));
		      idTransform.transform(input, output);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
