package ejercicio21;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

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

import javax.xml.*;
public class Ejercicio21 {

	private static int tamagnoNombre = 15;
	private static int tamagnoLocalidad = 15;
	public static void main(String[] args) throws IOException {
		
		RandomAccessFile mifichero = null;
		try {
			mifichero = new RandomAccessFile("src\\ejercicio21\\departamentos.dat", "rw");
		} catch (FileNotFoundException e) {

			System.err.println("No existe el fichero");
			e.printStackTrace();
		}
		
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factoria.newDocumentBuilder();
			DOMImplementation implementatio = builder.getDOMImplementation();
			Document document = implementatio.createDocument(null, "Departamentos", null);
			document.setXmlVersion("1.0");
		
			for(int i = 1; funcion(i) < mifichero.length(); i++) {
				mifichero.seek(funcion(i));
				if (mifichero.readInt() != 0) {
					
				
					mifichero.seek(funcion(i));
				
					Element dep = document.createElement("departamento");
					document.getDocumentElement().appendChild(dep);
					
					dep.setAttribute("id", Integer.toString(mifichero.readInt()));
					
					Element nom = document.createElement("nombre");
					Text texto = document.createTextNode(obtenerString(mifichero, tamagnoNombre));
					dep.appendChild(nom);
					nom.appendChild(texto);
					
					Element loc = document.createElement("localidad");
					texto = document.createTextNode(obtenerString(mifichero, tamagnoLocalidad));
					dep.appendChild(loc);
					loc.appendChild(texto);
					
				
				}
			}
			
			TransformerFactory xformFactory = TransformerFactory.newInstance();  
		      Transformer idTransform = xformFactory.newTransformer();
		      Source input = new DOMSource(document);
		      Result output = new StreamResult(new File("src//ejercicio21//departamentos.xml"));
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
	
	private static int funcion(int numero) {
		int pos = 0;
		int tamagnoRegistro = 64;
		pos = tamagnoRegistro * (numero - 1) + 1;
		return pos;
	}

	private static String obtenerString(RandomAccessFile mifichero, int tamagno) throws IOException {
		char datos[] = new char[tamagno];
		for (int i = 0; i < tamagno; i++) {
			datos[i] = mifichero.readChar();
		}
		return (new String(datos)).trim();
	}

}
