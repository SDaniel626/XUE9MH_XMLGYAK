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
			//xml file nev megadasa
			File inputFile = new File("./src\\hu\\domparse\\xue9mh\\XML_XUE9MH.xml");
			
			////Dom-dokumentum letrehozasa az XML dokumentum eleresehez
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			System.out.println("----------------------------");
			
			//Elemek kiiratasa metodusokkal
			NodeList nList = doc.getElementsByTagName("Gyros");
			gyrosRead(nList);
			System.out.println("----------------------------");

			NodeList nList2 = doc.getElementsByTagName("Gyrosos");
			gyrososRead(nList2);
			System.out.println("----------------------------");

			NodeList nList3 = doc.getElementsByTagName("Rendel");
			rendelRead(nList3);
			System.out.println("----------------------------");

			NodeList nList4 = doc.getElementsByTagName("Vasarlo");
			vasarloRead(nList4);
			System.out.println("----------------------------");

			NodeList nList5 = doc.getElementsByTagName("Beszallit");
			beszallitRead(nList5);
			System.out.println("----------------------------");

			NodeList nList6 = doc.getElementsByTagName("Beszallito");
			beszallitoRead(nList6);
			System.out.println("----------------------------");

			NodeList nList7 = doc.getElementsByTagName("Kiszallito");
			kiszallitoRead(nList7);
			System.out.println("----------------------------");
			
			NodeList nList8 = doc.getElementsByTagName("Bankkartya");
			bankkartyaRead(nList8);
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
	public static void gyrosRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\n Root element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				System.out.println("ID : " + eElement.getAttribute("id"));
				getElement(eElement, "Suly", "Suly");
				getElement(eElement, "Toltelek", "Toltelek");
				getElement(eElement, "GyrososFK", "GyrososFK");


			}
		}
	}
	
	public static void gyrososRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\n Root element :" + nNode.getNodeName());

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
	
	public static void rendelRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\n Root element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				getElement(eElement, "GyrosFK", "GyrosFK");
				getElement(eElement, "VasarloFK", "VasarloFK");

			}
		}
	}
	
	public static void vasarloRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\n Root element :" + nNode.getNodeName());

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
	
	public static void beszallitRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\n Root element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				getElement(eElement, "GyrososFK", "GyrososFK");
				getElement(eElement, "BeszallitoFK", "BeszallitoFK");
				getElement(eElement, "Termek", "Termek");
				getElement(eElement, "Datum", "Datum");

			}
		}
	}
	
	public static void beszallitoRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\n Root element :" + nNode.getNodeName());

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
	
	public static void kiszallitoRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\n Root element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				System.out.println("ID : " + eElement.getAttribute("id"));
				getElement(eElement, "Nev", "Nev");
				getElement(eElement, "Email", "Email");
				getElement(eElement, "Telefonszam", "Telefonszam");
				getElement(eElement, "GyrososFK", "GyrososFK");

			}
		}
	}
	
	public static void bankkartyaRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\n Root element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				getElement(eElement, "Kartyaszam", "Kartyaszam");
				getElement(eElement, "Bank", "Bank");
				getElement(eElement, "Tipus", "Tipus");
				getElement(eElement, "Lejarati_datum", "Lejarati_datum");
				getElement(eElement, "VasarloFK", "VasarloFK");

			}
		}
	}
}
