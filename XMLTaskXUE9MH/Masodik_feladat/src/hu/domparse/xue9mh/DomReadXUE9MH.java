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
			
			//Dom-fa letrehozasa az XML dokumentum eleresehez
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("<" + doc.getDocumentElement().getNodeName() +">");			
			
			//Elemek beolvasasa es listazasa metodusokkal
			NodeList nList = doc.getElementsByTagName("Gyorsetterem");
			gyorsetteremRead(nList);
			

			NodeList nList2 = doc.getElementsByTagName("Gyros");
			gyrosRead(nList2);
			

			NodeList nList3 = doc.getElementsByTagName("Rendel");
			rendelesekRead(nList3);
			

			NodeList nList4 = doc.getElementsByTagName("Vasarlo");
			vasarloRead(nList4);
			

			NodeList nList5 = doc.getElementsByTagName("Bankkartya");
			bankkartyaRead(nList5);
			

			NodeList nList6 = doc.getElementsByTagName("Beszallit");
			beszallitRead(nList6);
			

			NodeList nList7 = doc.getElementsByTagName("Beszallito");
			beszallitoRead(nList7);
			
			
			NodeList nList8 = doc.getElementsByTagName("Tulaj");
			tulajRead(nList8);
			
			System.out.println("</" + doc.getDocumentElement().getNodeName() +">");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Metodus a gyerekelemek kiirasara
	public static void getElement(Element eElement, String elementOut, String elementName) {

		System.out.println("\t\t<"+elementOut + ">" + eElement.getElementsByTagName(elementName).item(0).getTextContent() + "</"+elementOut + ">");
	}
	
	public static void getSubElement(Element eElement, String elementOut, String elementName) {

		System.out.println("\t\t\t<"+elementOut + ">" + eElement.getElementsByTagName(elementName).item(0).getTextContent() + "</"+elementOut + ">");
	}
	//beolvaso metodusok
	
	public static void gyorsetteremRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if(temp==0) {
				System.out.println("\t<" + nNode.getNodeName() + " id:" + ((Element) nNode).getAttribute("id") + ">");
			} else {
				System.out.println("\n\t<" + nNode.getNodeName() + " id:" + ((Element) nNode).getAttribute("id") + ">");
			}
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				getElement(eElement, "Nev", "Nev");
				getElement(eElement, "Email", "Email");
				getElement(eElement, "Telefonszam", "Telefonszam");
				System.out.println("\t\t<Nyitvatartas>");
				getSubElement(eElement, "Hetkoznap", "Hetkoznap");
				getSubElement(eElement, "Hetvege_unnepek", "Hetvege_unnepek");
				System.out.println("\t\t</Nyitvatartas>");

			}
			System.out.println("\t</" + nNode.getNodeName() + ">");
		}
	}
	public static void gyrosRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\n\t<" + nNode.getNodeName() + " id:" + ((Element) nNode).getAttribute("id") + " gyorsetteremFK:" + ((Element) nNode).getAttribute("gyorsetteremFK") + ">");

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				getElement(eElement, "Suly", "Suly");
				
				NodeList toltelekList = eElement.getElementsByTagName("Toltelek");
				toltelekRead(toltelekList);
			}
			System.out.println("\t</" + nNode.getNodeName() + ">");
		}
	}	
	
	private static void toltelekRead(NodeList toltelekList) {
	    for (int i = 0; i < toltelekList.getLength(); i++) {
	        Node toltelekNode = toltelekList.item(i);
	        if (toltelekNode.getNodeType() == Node.ELEMENT_NODE) {
	            Element toltelekElement = (Element) toltelekNode;
	            System.out.println("\t\t<Toltelek>" + toltelekElement.getTextContent()+ "</Toltelek>");
	        }	        
	    }
	}
	
	public static void rendelesekRead(NodeList nList) {
		System.out.println("\n\t<Rendelesek>");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				System.out.println("\t\t<" + nNode.getNodeName() + " gyrosFK : " + eElement.getAttribute("gyrosFK") + " vasarloFK : " + eElement.getAttribute("vasarloFK") + "/>");
			}
		}
		System.out.println("\t</Rendelesek>");
	}
	
	public static void vasarloRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\n\t<" + nNode.getNodeName() + " id:" + ((Element) nNode).getAttribute("id") + ">");

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				getElement(eElement, "Nev", "Nev");
				getElement(eElement, "Telefonszam", "Telefonszam");
				System.out.println("\t\t<Cim>");
				getSubElement(eElement, "Hazszam", "Hazszam");
				getSubElement(eElement, "Utca", "Utca");
				getSubElement(eElement, "Varos", "Varos");
				System.out.println("\t\t</Cim>");

			}
			System.out.println("\t</" + nNode.getNodeName() + ">");
		}
	}
	
	public static void bankkartyaRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\n\t<" + nNode.getNodeName() + " id: " + ((Element) nNode).getAttribute("id") + " vasarloFK : " + ((Element) nNode).getAttribute("vasarloFK") + ">");

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				getElement(eElement, "Kartyaszam", "Kartyaszam");
				getElement(eElement, "CCV", "CCV");
				getElement(eElement, "Tipus", "Tipus");
				getElement(eElement, "Lejarati_datum", "Lejarati_datum");
				System.out.println("\t</" + nNode.getNodeName() + ">");
			}
		}
	}
	
	public static void beszallitRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\n\t<" + nNode.getNodeName() + " gyorsetteremFK : " + ((Element) nNode).getAttribute("gyorsetteremFK") + " beszallitoFK : " + ((Element) nNode).getAttribute("beszallitoFK") + ">");

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				getElement(eElement, "Termek", "Termek");
				getElement(eElement, "Datum", "Datum");
				System.out.println("\t</" + nNode.getNodeName() + ">");
			}
		}
	}
	
	public static void beszallitoRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\n\t<" + nNode.getNodeName() + " id:" + ((Element) nNode).getAttribute("id") + ">");

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				getElement(eElement, "Nev", "Nev");
				getElement(eElement, "Email", "Email");
				getElement(eElement, "Telefonszam", "Telefonszam");
				System.out.println("\t\t<Cim>");
				getSubElement(eElement, "Hazszam", "Hazszam");
				getSubElement(eElement, "Utca", "Utca");
				getSubElement(eElement, "Varos", "Varos");
				System.out.println("\t\t</Cim>");
				System.out.println("\t</" + nNode.getNodeName() + ">");
			}
		}
	}
	
	public static void tulajRead(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\n\t<" + nNode.getNodeName() + " id:" + ((Element) nNode).getAttribute("id") + " gyorsetteremFK : " + ((Element) nNode).getAttribute("gyorsetteremFK") + ">");

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				getElement(eElement, "Nev", "Nev");
				getElement(eElement, "Email", "Email");
				getElement(eElement, "Telefonszam", "Telefonszam");
				System.out.println("\t</" + nNode.getNodeName() + ">");
			}
		}
	}	
}