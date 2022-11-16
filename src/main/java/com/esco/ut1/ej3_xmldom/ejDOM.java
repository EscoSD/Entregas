/*3. Con el mismo tipo de registro,
realiza una aplicación que genere un fichero XML DOM y después lo muestre por pantalla.*/

package com.esco.ut1.ej3_xmldom;

// Importamos la clase Doujinshi que se encuentra en el paquete del ejercicio 1
import com.esco.ut1.ej1_raf.Doujinshi;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class ejDOM {

	public static void main(String[] args) {

		xmlCreate();
		xmlRead();

	}

	public static void xmlCreate() {

		int code;
		ArrayList<Doujinshi> list = new ArrayList<>();

		try (RandomAccessFile raf = new RandomAccessFile("RegistroD.dat", "rw")) {

			// Rellenamos un ArrayList con todos los objetos Doujin almacenados en el RAF.
			for (int i = 1; i <= 100; i++) {
				raf.seek((i - 1) * 50);

				code = raf.readInt();
				Doujinshi doujin;

				if (code != 0) {
					doujin = new Doujinshi(code, raf.readUTF(), raf.readInt(), raf.readDouble());
					list.add(doujin);
				}

			}

			escritura(list);

		} catch (IOException | ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}

	}

	private static void escritura(ArrayList<Doujinshi> list) throws ParserConfigurationException, TransformerException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		Element nodoDoujin, nodoDatos;
		Text text;

		DocumentBuilder db = dbf.newDocumentBuilder();
		DOMImplementation dom = db.getDOMImplementation();
		Document document = dom.createDocument(null, "xml", null);

		Element root = document.createElement("doujinshis");
		document.getDocumentElement().appendChild(root);

		for (Doujinshi doujin : list) {	// Escribimos todos los doujins en un XML.

			nodoDoujin = document.createElement("doujinshi");
			root.appendChild(nodoDoujin);

			nodoDatos = document.createElement("code");
			nodoDoujin.appendChild(nodoDatos);
			text = document.createTextNode(String.valueOf(doujin.getCode()));
			nodoDatos.appendChild(text);

			nodoDatos = document.createElement("name");
			nodoDoujin.appendChild(nodoDatos);
			text = document.createTextNode(String.valueOf(doujin.getName()));
			nodoDatos.appendChild(text);

			nodoDatos = document.createElement("pages");
			nodoDoujin.appendChild(nodoDatos);
			text = document.createTextNode(String.valueOf(doujin.getPages()));
			nodoDatos.appendChild(text);

			nodoDatos = document.createElement("prize");
			nodoDoujin.appendChild(nodoDatos);
			text = document.createTextNode(String.valueOf(doujin.getPrize()));
			nodoDatos.appendChild(text);
		}

		Source source = new DOMSource(document);
		Result resultado = new StreamResult(new File("Doujinshis.xml"));
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, resultado);

	}

	public static void xmlRead() {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;

		try {
			db = dbf.newDocumentBuilder();
			Document document = db.parse(new File("Doujinshis.xml"));

			NodeList list = document.getElementsByTagName("doujinshi");

			for (int i = 0; i < list.getLength(); i++) {
				Node nodo = list.item(i);
				Element element = (Element) nodo;

				System.out.print(element.getElementsByTagName("code").item(0).getTextContent() + ".- ");
				System.out.println(element.getElementsByTagName("name").item(0).getTextContent());
				System.out.println(element.getElementsByTagName("prize").item(0).getTextContent() + "€");
				System.out.println(element.getElementsByTagName("pages").item(0).getTextContent() + " páginas\n");
			}

		} catch (ParserConfigurationException | IOException | SAXException e) {
			e.printStackTrace();
		}
	}
}
