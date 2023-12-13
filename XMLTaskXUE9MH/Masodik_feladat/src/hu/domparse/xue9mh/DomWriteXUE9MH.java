package hu.domparse.xue9mh;


import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomWriteXUE9MH extends DomReadXUE9MH {
	public static void main(String[] args) {
        try {
            // Beolvasom az xml file tartalmat és beparsolom a document-be vagyis felepitem a DOM fát
            File inputFile = new File("XML_XUE9MH.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            
            document.getDocumentElement().normalize();
			System.out.println("<" + document.getDocumentElement().getNodeName() +">");			
			
			//Elemek beolvasasa es listazasa metodusokkal
			NodeList nList = document.getElementsByTagName("Gyorsetterem");
			gyorsetteremRead(nList);
			

			NodeList nList2 = document.getElementsByTagName("Gyros");
			gyrosRead(nList2);
			

			NodeList nList3 = document.getElementsByTagName("Rendel");
			rendelesekRead(nList3);
			

			NodeList nList4 = document.getElementsByTagName("Vasarlo");
			vasarloRead(nList4);
			

			NodeList nList5 = document.getElementsByTagName("Bankkartya");
			bankkartyaRead(nList5);
			

			NodeList nList6 = document.getElementsByTagName("Beszallit");
			beszallitRead(nList6);
			

			NodeList nList7 = document.getElementsByTagName("Beszallito");
			beszallitoRead(nList7);
			
			
			NodeList nList8 = document.getElementsByTagName("Tulaj");
			tulajRead(nList8);
			
			System.out.println("</" + document.getDocumentElement().getNodeName() +">");

            // Kiiratom az xml tartalmat konzolra
            //printDocument(document);

            // Kiirom a dokumentumot egy uj fajlba
            //File outputFile = new File("XML_XUE9MH2.xml");
            //writeDocument(document, outputFile);
            
            PrintWriter writer = new PrintWriter(new FileWriter("XML_XUE9MH3.xml"));
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<" + document.getDocumentElement().getNodeName() + " xsi:noNamespaceSchemaLocation=\"XMLSchemaXUE9MH.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +">");
            gyorsetteremWrite(nList, writer);
            gyrosWrite(nList2, writer);
            rendelesekWrite(nList3, writer);
            vasarloWrite(nList4, writer);
            bankkartyaWrite(nList5, writer);
            beszallitWrite(nList6, writer);
            beszallitoWrite(nList7, writer);
            tulajWrite(nList8, writer);
            writer.println("</" + document.getDocumentElement().getNodeName() +">");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // XML tartalmat konzolra kiiro metodus
    private static void printDocument(Document document) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            System.out.println(writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // XML tartalmat uj fajlba kiiro metodus
    private static void writeDocument(Document document, File outputFile) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            transformer.transform(new DOMSource(document), new StreamResult(outputFile));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
  //Metodus a gyerekelemek kiirasara
  	public static void writeElement(Element eElement, PrintWriter writer, String elementOut, String elementName) {
  		 writer.println("\t\t<" + elementOut + ">" + eElement.getElementsByTagName(elementName).item(0).getTextContent() + "</" + elementOut + ">");
  	}
  	
  	public static void writeSubElement(Element eElement, PrintWriter writer, String elementOut, String elementName) {
  		writer.println("\t\t\t<" + elementOut + ">" + eElement.getElementsByTagName(elementName).item(0).getTextContent() + "</" + elementOut + ">");
  	}
  	//beolvaso metodusok
  	
  	public static void gyorsetteremWrite(NodeList nList,PrintWriter writer) throws IOException {
  		for (int temp = 0; temp < nList.getLength(); temp++) {
  			Node nNode = nList.item(temp);
  			if(temp==0) {
  				writer.println("\t<" + nNode.getNodeName() + " id:" + ((Element) nNode).getAttribute("id") + ">");
  			} else {
  				writer.println("\n\t<" + nNode.getNodeName() + " id:" + ((Element) nNode).getAttribute("id") + ">");
  			}
  			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
  				Element eElement = (Element) nNode;

  				writeElement(eElement,writer, "Nev", "Nev");
  				writeElement(eElement,writer, "Email", "Email");
  				writeElement(eElement,writer, "Telefonszam", "Telefonszam");
  				writer.println("\t\t<Nyitvatartas>");
  				writeSubElement(eElement,writer, "Hetkoznap", "Hetkoznap");
  				writeSubElement(eElement,writer, "Hetvege_unnepek", "Hetvege_unnepek");
  				writer.println("\t\t</Nyitvatartas>");

  			}
  			writer.println("\t</" + nNode.getNodeName() + ">");
  		}
  	}
  	public static void gyrosWrite(NodeList nList,PrintWriter writer) throws IOException {
  		for (int temp = 0; temp < nList.getLength(); temp++) {
  			Node nNode = nList.item(temp);
  			writer.println("\n\t<" + nNode.getNodeName() + " id:" + ((Element) nNode).getAttribute("id") + " gyorsetteremFK:" + ((Element) nNode).getAttribute("gyorsetteremFK") + ">");

  			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
  				Element eElement = (Element) nNode;

  				writeElement(eElement,writer, "Suly", "Suly");
  				
  				NodeList toltelekList = eElement.getElementsByTagName("Toltelek");
  				toltelekWrite(toltelekList,writer);
  			}
  			writer.println("\t</" + nNode.getNodeName() + ">");
  		}
  	}	
  	
  	private static void toltelekWrite(NodeList toltelekList, PrintWriter writer) throws IOException {
  	    for (int i = 0; i < toltelekList.getLength(); i++) {
  	        Node toltelekNode = toltelekList.item(i);
  	        if (toltelekNode.getNodeType() == Node.ELEMENT_NODE) {
  	            Element toltelekElement = (Element) toltelekNode;
  	          writer.println("\t\t<Toltelek>" + toltelekElement.getTextContent()+ "</Toltelek>");
  	        }	        
  	    }
  	}
  	
  	public static void rendelesekWrite(NodeList nList, PrintWriter writer) throws IOException {
  		writer.println("\n\t<Rendelesek>");
  		for (int temp = 0; temp < nList.getLength(); temp++) {
  			Node nNode = nList.item(temp);

  			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
  				Element eElement = (Element) nNode;
  				writer.println("\t\t<" + nNode.getNodeName() + " gyrosFK : " + eElement.getAttribute("gyrosFK") + " vasarloFK : " + eElement.getAttribute("vasarloFK") + "/>");
  			}
  		}
  		writer.println("\t</Rendelesek>");
  	}
  	
  	public static void vasarloWrite(NodeList nList, PrintWriter writer) throws IOException {
  		for (int temp = 0; temp < nList.getLength(); temp++) {
  			Node nNode = nList.item(temp);
  			writer.println("\n\t<" + nNode.getNodeName() + " id:" + ((Element) nNode).getAttribute("id") + ">");

  			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
  				Element eElement = (Element) nNode;

  				writeElement(eElement,writer, "Nev", "Nev");
  				writeElement(eElement,writer, "Telefonszam", "Telefonszam");
  				writer.println("\t\t<Cim>");
  				writeSubElement(eElement,writer, "Hazszam", "Hazszam");
  				writeSubElement(eElement,writer, "Utca", "Utca");
  				writeSubElement(eElement,writer, "Varos", "Varos");
  				writer.println("\t\t</Cim>");

  			}
  			writer.println("\t</" + nNode.getNodeName() + ">");
  		}
  	}
  	
  	public static void bankkartyaWrite(NodeList nList, PrintWriter writer) throws IOException {
  		for (int temp = 0; temp < nList.getLength(); temp++) {
  			Node nNode = nList.item(temp);
  			writer.println("\n\t<" + nNode.getNodeName() + " id: " + ((Element) nNode).getAttribute("id") + " vasarloFK : " + ((Element) nNode).getAttribute("vasarloFK") + ">");

  			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
  				Element eElement = (Element) nNode;

  				writeElement(eElement,writer, "Kartyaszam", "Kartyaszam");
  				writeElement(eElement,writer, "CCV", "CCV");
  				writeElement(eElement,writer, "Tipus", "Tipus");
  				writeElement(eElement,writer, "Lejarati_datum", "Lejarati_datum");
  				writer.println("\t</" + nNode.getNodeName() + ">");
  			}
  		}
  	}
  	
  	public static void beszallitWrite(NodeList nList, PrintWriter writer) throws IOException {
  		for (int temp = 0; temp < nList.getLength(); temp++) {
  			Node nNode = nList.item(temp);
  			writer.println("\n\t<" + nNode.getNodeName() + " gyorsetteremFK : " + ((Element) nNode).getAttribute("gyorsetteremFK") + " beszallitoFK : " + ((Element) nNode).getAttribute("beszallitoFK") + ">");

  			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
  				Element eElement = (Element) nNode;

  				writeElement(eElement,writer, "Termek", "Termek");
  				writeElement(eElement,writer, "Datum", "Datum");
  				writer.println("\t</" + nNode.getNodeName() + ">");
  			}
  		}
  	}
  	
  	public static void beszallitoWrite(NodeList nList,PrintWriter writer) throws IOException {
  		for (int temp = 0; temp < nList.getLength(); temp++) {
  			Node nNode = nList.item(temp);
  			writer.println("\n\t<" + nNode.getNodeName() + " id:" + ((Element) nNode).getAttribute("id") + ">");

  			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
  				Element eElement = (Element) nNode;

  				writeElement(eElement,writer, "Nev", "Nev");
  				writeElement(eElement,writer, "Email", "Email");
  				writeElement(eElement,writer, "Telefonszam", "Telefonszam");
  				writer.println("\t\t<Cim>");
  				writeSubElement(eElement,writer, "Hazszam", "Hazszam");
  				writeSubElement(eElement,writer, "Utca", "Utca");
  				writeSubElement(eElement,writer, "Varos", "Varos");
  				writer.println("\t\t</Cim>");
  				writer.println("\t</" + nNode.getNodeName() + ">");
  			}
  		}
  	}
  	
  	public static void tulajWrite(NodeList nList, PrintWriter writer) throws IOException {
  		for (int temp = 0; temp < nList.getLength(); temp++) {
  			Node nNode = nList.item(temp);
  			writer.println("\n\t<" + nNode.getNodeName() + " id:" + ((Element) nNode).getAttribute("id") + " gyorsetteremFK : " + ((Element) nNode).getAttribute("gyorsetteremFK") + ">");

  			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
  				Element eElement = (Element) nNode;

  				writeElement(eElement,writer, "Nev", "Nev");
  				writeElement(eElement,writer, "Email", "Email");
  				writeElement(eElement,writer, "Telefonszam", "Telefonszam");
  				writer.println("\t</" + nNode.getNodeName() + ">");
  			}
  		}
  	}
}