package ejercicio24;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.*;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;



public class Ejercicio24 {

	static ArrayList<Empleado> listaEmpleados = new ArrayList<>();
	
	public static void main(String[] args) {
		
		leerEmpleados();
		
		escribirNominas();

	}
	
	public static void leerEmpleados() {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		try {
			
			DocumentBuilder builder = factoria.newDocumentBuilder();
			Document document = builder.parse(new File("src//ejercicio24//empleados.xml"));
			document.getDocumentElement().normalize();
			
			NodeList empleados = document.getElementsByTagName("empleado");
			
			for(int i = 0; i < empleados.getLength(); i++) {
				Node empleado = empleados.item(i);
				if(empleado.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) empleado;
					
					Node nodo = (Node)elemento.getElementsByTagName("dni").item(0).getChildNodes().item(0);
					String dni = nodo.getNodeValue();
					
					nodo = (Node)elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0);
					String nombre = nodo.getNodeValue();
					
					nodo = (Node)elemento.getElementsByTagName("apellido1").item(0).getChildNodes().item(0);
					String apellido1 = nodo.getNodeValue();
					
					nodo = (Node)elemento.getElementsByTagName("apellido2").item(0).getChildNodes().item(0);
					String apellido2 = nodo.getNodeValue();
					
					nodo = (Node)elemento.getElementsByTagName("sueldo").item(0).getChildNodes().item(0).getChildNodes().item(0);
					String base = nodo.getNodeValue();
					Integer.parseInt(base);
					
					nodo = (Node)elemento.getElementsByTagName("sueldo").item(0).getChildNodes().item(1).getChildNodes().item(0);
					String complementos = nodo.getNodeValue();
					
					nodo = (Node)elemento.getElementsByTagName("sueldo").item(0).getChildNodes().item(2).getChildNodes().item(0);
					String irpf = nodo.getNodeValue();	
					
					Empleado e = new Empleado(dni, nombre, apellido1, apellido2, Integer.parseInt(base), Integer.parseInt(complementos), Float.parseFloat(irpf));
					listaEmpleados.add(e);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void escribirNominas() {
DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factoria.newDocumentBuilder();
			DOMImplementation implementatio = builder.getDOMImplementation();
			Document document = implementatio.createDocument(null, "Nominas", null);
			document.setXmlVersion("1.0");

			
			for (Empleado e : listaEmpleados) {
				Element raiz = document.createElement("empleado");
				document.getDocumentElement().appendChild(raiz);
				
				Element elem = document.createElement("dni");
				Text text = document.createTextNode(e.getDni());
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
				
				Float total = (e.getBase() + e.getComplementos()) * (1 - (e.getIrpf()/100));
				elem = document.createElement("total");
				text = document.createTextNode(Float.toString(total));
				elem_sueldo.appendChild(elem);
				elem.appendChild(text);
				
			}
			
		      TransformerFactory xformFactory = TransformerFactory.newInstance();  
		      Transformer idTransform = xformFactory.newTransformer();
		      Source input = new DOMSource(document);
		      Result output = new StreamResult(new File("src//ejercicio24//nominas.xml"));
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
