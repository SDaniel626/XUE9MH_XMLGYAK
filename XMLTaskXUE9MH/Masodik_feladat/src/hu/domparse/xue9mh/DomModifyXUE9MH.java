package hu.domparse.xue9mh;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomModifyXUE9MH {
	public static void main(String[] args) throws TransformerException {
		//Dom-fa letrehozasa az XML dokumentum eleresehez
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try (InputStream is = new FileInputStream("XML_XUE9MH.xml")) {
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			// Bekerjuk a modositani kivant elemet
			Scanner rootElem = new Scanner(System.in);
			System.out.println("Which element do you want to modify?");
			String element = rootElem.nextLine();
			
			Scanner elemID = new Scanner(System.in);
			System.out.println("Please provide the ID of said element.");
			String childElementID = elemID.nextLine();
			
			NodeList listOfElements = doc.getElementsByTagName(element);

			Scanner elem = new Scanner(System.in);
			System.out.println("Which child element do you want to modify?");
			String childElement = elem.nextLine();
			
			
			System.out.println("What should its new value be?");
			Scanner ujElem = new Scanner(System.in);
			String childElementElementNew = ujElem.nextLine();
			
			//megkeressuk a kivant id-vel rendelkezo elemet
			for (int i = 0; i < listOfElements.getLength(); i++) {
				Node staff = listOfElements.item(i);
				if (staff.getNodeType() == Node.ELEMENT_NODE) {
					String id = staff.getAttributes().getNamedItem("id").getTextContent();
					
					if (childElementID.equals(id.trim())) {

						NodeList childNodes = staff.getChildNodes();
						//beazonositjuk a gyerek elemet aminek modositjuk az erteket
						for (int j = 0; j < childNodes.getLength(); j++) {
							Node item = childNodes.item(j);
							if (item.getNodeType() == Node.ELEMENT_NODE) {
								//modositjuk a tartalmat
								if (childElement.equalsIgnoreCase(item.getNodeName())) {
									item.setTextContent(childElementElementNew);
								} else if(childElement.equalsIgnoreCase("Hazszam") || childElement.equalsIgnoreCase("Utca") || childElement.equalsIgnoreCase("Varos") || childElement.equalsIgnoreCase("Hetkoznap") || childElement.equalsIgnoreCase("Hetvege_unnepek") ) {
									NodeList subchildren = item.getChildNodes();
									for(int k = 0; k < subchildren.getLength();k++) {
										Node subchild = subchildren.item(k);
										if (childElement.equalsIgnoreCase(subchild.getNodeName())) {
											subchild.setTextContent(childElementElementNew);
										}
									}									
								}
							}
						}
					}
				}				
			}
			
			// kiirjuk a konzolra a modositott xml-t  
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			System.out.println("-----------After Modification-----------");
			transformer.transform(new DOMSource(doc), new StreamResult(System.out));
		
		} catch (ParserConfigurationException | SAXException | IOException  e) {
			e.printStackTrace();
		}
	}
}