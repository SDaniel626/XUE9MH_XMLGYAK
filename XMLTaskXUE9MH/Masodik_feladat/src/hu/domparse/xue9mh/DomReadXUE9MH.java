package hu.domparse.xue9mh;

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
	
	public static void main(String[] args) {
		try {
			//xml file megadasa
			File inputFile = new File("XML_XUE9MH.xml");
			
			////Dom-dokumentum letrehozasa az XML dokumentum eleresehez
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			System.out.println("----------------------------");
			
			//Elemek beolvasasa es listazasa metodusokkal
			NodeList nList = doc.getElementsByTagName("Gyorsetterem");
			gyorsetteremRead(nList);
			System.out.println("----------------------------");

			NodeList nList2 = doc.getElementsByTagName("Gyros");
			gyrosRead(nList2);
			System.out.println("----------------------------");

			NodeList nList3 = doc.getElementsByTagName("Rendel");
			rendelesekRead(nList3);
			System.out.println("----------------------------");

			NodeList nList4 = doc.getElementsByTagName("Vasarlo");
			vasarloRead(nList4);
			System.out.println("----------------------------");

			NodeList nList5 = doc.getElementsByTagName("Bankkartya");
			bankkartyaRead(nList5);
			System.out.println("----------------------------");

			NodeList nList6 = doc.getElementsByTagName("Beszallit");
			beszallitRead(nList6);
			System.out.println("----------------------------");

			NodeList nList7 = doc.getElementsByTagName("Beszallito");
			beszallitoRead(nList7);
			System.out.println("----------------------------");
			
			NodeList nList8 = doc.getElementsByTagName("Tulaj");
			tulajRead(nList8);
			System.out.println("----------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Metodus a gyerekelemek kiirasara
	public static void getElement(Element eElement, String elementOut, String elementName) {

		System.out.println(elementOut + " : " + eElement.getElementsByTagName(elementName).item(0).getTextContent());
	}
	//kiiro metodusok
	
	public static void gyorsetteremRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nRoot element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				System.out.println("ID : " + eElement.getAttribute("id"));
				getElement(eElement, "Nev", "Nev");
				getElement(eElement, "Email", "Email");
				getElement(eElement, "Telefonszam", "Telefonszam");
				getElement(eElement, "Hetkoznap", "Hetkoznap");
				getElement(eElement, "Hetvege_unnepek", "Hetvege_unnepek");

			}
		}
	}
	public static void gyrosRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nRoot element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				System.out.println("ID : " + eElement.getAttribute("id"));
				System.out.println("GyorsetteremFK : " + eElement.getAttribute("gyorsetteremFK"));
				getElement(eElement, "Suly", "Suly");
				
				NodeList toltelekList = eElement.getElementsByTagName("Toltelek");
				toltelekRead(toltelekList);
			}
		}
	}	
	
	private static void toltelekRead(NodeList toltelekList) {
        String temp = "Tolelekek: ";
	    for (int i = 0; i < toltelekList.getLength(); i++) {
	        Node toltelekNode = toltelekList.item(i);
	        if (toltelekNode.getNodeType() == Node.ELEMENT_NODE) {
	            Element toltelekElement = (Element) toltelekNode;
	            temp += toltelekElement.getTextContent() + ", ";
	        }
	    }
	    temp = temp.substring(0, temp.length()-2);
	    System.out.println(temp);
	}
	
	public static void rendelesekRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nRoot element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				System.out.println("gyrosFK : " + eElement.getAttribute("gyrosFK"));
				System.out.println("vasarloFK : " + eElement.getAttribute("vasarloFK"));
			}
		}
	}
	
	public static void vasarloRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nRoot element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				System.out.println("ID : " + eElement.getAttribute("id"));
				getElement(eElement, "Nev", "Nev");
				getElement(eElement, "Telefonszam", "Telefonszam");
				getElement(eElement, "Hazszam", "Hazszam");
				getElement(eElement, "Utca", "Utca");
				getElement(eElement, "Varos", "Varos");

			}
		}
	}
	
	public static void bankkartyaRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nRoot element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				System.out.println("ID : " + eElement.getAttribute("id"));
				getElement(eElement, "Kartyaszam", "Kartyaszam");
				getElement(eElement, "CCV", "CCV");
				getElement(eElement, "Tipus", "Tipus");
				getElement(eElement, "Lejarati_datum", "Lejarati_datum");
				System.out.println("VasarloFK : " + eElement.getAttribute("vasarloFK"));

			}
		}
	}
	
	public static void beszallitRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nRoot element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				System.out.println("GyorsetteremFK : " + eElement.getAttribute("gyorsetteremFK"));
				System.out.println("BeszallitoFK : " + eElement.getAttribute("beszallitoFK"));
				getElement(eElement, "Termek", "Termek");
				getElement(eElement, "Datum", "Datum");

			}
		}
	}
	
	public static void beszallitoRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nRoot element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				System.out.println("ID : " + eElement.getAttribute("id"));
				getElement(eElement, "Nev", "Nev");
				getElement(eElement, "Email", "Email");
				getElement(eElement, "Telefonszam", "Telefonszam");
				getElement(eElement, "Hazszam", "Hazszam");
				getElement(eElement, "Utca", "Utca");
				getElement(eElement, "Varos", "Varos");

			}
		}
	}
	
	public static void tulajRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nRoot element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				System.out.println("ID : " + eElement.getAttribute("id"));
				System.out.println("GyorsetteremFK : " + eElement.getAttribute("gyorsetteremFK"));
				getElement(eElement, "Nev", "Nev");
				getElement(eElement, "Email", "Email");
				getElement(eElement, "Telefonszam", "Telefonszam");

			}
		}
	}
	
	
}
