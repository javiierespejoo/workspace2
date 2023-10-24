package ejercicio22;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio22 {

	public static void main(String[] args) {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		try {
			
			DocumentBuilder builder = factoria.newDocumentBuilder();
			Document document = builder.parse(new File("src//ejercicio22//departamentos.xml"));
			document.getDocumentElement().normalize();
			
			System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
			
			NodeList departamentos = document.getElementsByTagName("departamento");
			System.out.println("Cantidad de departamentos: " + departamentos.getLength() + "\n");
			
			for (int i = 0; i < departamentos.getLength(); i++) {
				Node departamento = departamentos.item(i);
				if (departamento.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) departamento;
					Node nodo = (Node)elemento.getAttributeNode("id");
					System.out.println("id: " + nodo.getNodeValue());
					
					nodo = (Node)elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0);
					System.out.println("Nombre: " + nodo.getNodeValue());
					
					nodo = (Node)elemento.getElementsByTagName("localidad").item(0).getChildNodes().item(0);
					System.out.println("Localidad: " + nodo.getNodeValue());
					System.out.println("\n");
				}	
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
