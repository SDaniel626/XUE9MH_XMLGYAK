package domXUE9MH1108;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReadXUE9MH {
	public static void main (String[] args) {
		try {
			//xml file megnyitasa
			File inputFile = new File("XUE9MH_orarend.xml");
			
			////Dom-dokumentum letrehozasa az XML dokumentum eleresehez
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			System.out.println("----------------------------");
			
			//Elemek kiiratasa metodusokkal
			NodeList nList = doc.getElementsByTagName("ora");
			oraRead(nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Metodus a gyerekelemek kiirasara
	public static void getElement(Element eElement, String elementOut, String elementName) {

		System.out.println("\t<" + elementOut + ">" + eElement.getElementsByTagName(elementName).item(0).getTextContent() + "</" + elementOut + ">" );
	}

	// kiiro metodusok
	public static void oraRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\n Root element :");

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				System.out.println("<" + nNode.getNodeName() + "ID : " + eElement.getAttribute("ID") + "   Tipus: " + eElement.getAttribute("tipus") + ">");
				getElement(eElement, "targy", "targy");
				getElement(eElement, "nap", "nap");
				getElement(eElement, "tol", "tol");
				getElement(eElement, "ig", "ig");
				getElement(eElement, "helyszin", "helyszin");
				getElement(eElement, "oktato", "oktato");
				getElement(eElement, "szak", "szak");
				System.out.println("</" + nNode.getNodeName() + ">");
			}

		}

	}
}
