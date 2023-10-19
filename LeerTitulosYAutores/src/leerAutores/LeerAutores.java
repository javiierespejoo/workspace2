package leerAutores;

import java.io.*;
import java.util.Iterator;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class LeerAutores {

	public static void main(String[] args) {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		try {
			
			DocumentBuilder builder = factoria.newDocumentBuilder();
			Document document = builder.parse(new File("src//xml//datos//libros.xml"));
			document.getDocumentElement().normalize();
			System.out.println("Elemento raiz :" + document.getDocumentElement().getNodeName());;
			
			// crear una lista de los libros
			NodeList libros = document.getElementsByTagName("libro");
			System.out.println("Cantidad de libros " + libros.getLength());
			
			for (int i = 0; i < libros.getLength(); i++) {
				Node libro = libros.item(i);
				if (libro.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) libro;
					Node nodo = (Node)elemento.getElementsByTagName("titulo").item(0).getChildNodes().item(0);
					System.out.println("titulo: " + nodo.getNodeValue());
					
					for (int j = 0; j < elemento.getElementsByTagName("autor").getLength(); j++) {
						nodo = (Node)elemento.getElementsByTagName("autor").item(j).getChildNodes().item(0);
						System.out.println("Autor: " + nodo.getNodeValue());
					}
				}	
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
