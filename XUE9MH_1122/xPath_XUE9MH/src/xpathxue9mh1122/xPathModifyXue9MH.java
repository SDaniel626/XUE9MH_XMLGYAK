package xpathxue9mh1122;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xPathModifyXue9MH {
	public static void main(String[] args) {
		 try {
	         File inputFile = new File("XUE9MH_orarend.xml");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();

	         /*Oktato modositasa*/
	         modifyElementValue(doc, "x11", "oktato", "Kovacs Laszlo");

	         /*Helyszin modositasa*/
	         modifyElementValue(doc, "x03", "helyszin", "Inf. 103");

	         /*Targy modositasa*/
	         modifyElementValue(doc, "x01", "targy", "Adatbázisok 1");

	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         transformer.setOutputProperty(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "no");
	         transformer.setOutputProperty(javax.xml.transform.OutputKeys.METHOD, "xml");
	         transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
	         transformer.setOutputProperty(javax.xml.transform.OutputKeys.STANDALONE, "no");

	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(new File("XUE9MH_orarend1.xml"));
	         transformer.transform(source, result);

	         printModifiedElements(doc);
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	}

   private static void modifyElementValue(Document doc, String id, String elementName, String newValue) {
       NodeList nodeList = doc.getElementsByTagName("ora");
       for (int i = 0; i < nodeList.getLength(); i++) {
           Node node = nodeList.item(i);
           if (node.getNodeType() == Node.ELEMENT_NODE) {
               Element element = (Element) node;
               if (element.getAttribute("ID").equals(id)) {
                   NodeList targetElements = element.getElementsByTagName(elementName);
                   if (targetElements.getLength() > 0) {
                       targetElements.item(0).setTextContent(newValue);
                   }
               }
           }
       }
   }

   private static void printModifiedElements(Document doc) {
       NodeList nodeList = doc.getElementsByTagName("ora");
       for (int i = 0; i < nodeList.getLength(); i++) {
           Node node = nodeList.item(i);
           if (node.getNodeType() == Node.ELEMENT_NODE) {
               Element element = (Element) node;
               String id = element.getAttribute("ID");
               String tipus = element.getAttribute("tipus");
               String targy = element.getElementsByTagName("targy").item(0).getTextContent();
               String nap = element.getElementsByTagName("nap").item(0).getTextContent();
               String tol = element.getElementsByTagName("tol").item(0).getTextContent();
               String ig = element.getElementsByTagName("ig").item(0).getTextContent();
               String helyszin = element.getElementsByTagName("helyszin").item(0).getTextContent();
               String oktato = element.getElementsByTagName("oktato").item(0).getTextContent();

               System.out.println(String.format("ID: %s, Tipus: %s, Targy: %s, Nap: %s, Idopont: %s-%s, Helyszin: %s, Oktato: %s",
                       id, tipus, targy, nap, tol, ig, helyszin, oktato));
           }
       }
   }

}
