package xpathxue9mh;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class xPathXUE9MH {
	public static void main(String[] args) {
		try {

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document document = documentBuilder.parse("studentXUE9MH.xml");

			document.getDocumentElement().normalize();

			XPath xPath = XPathFactory.newInstance().newXPath();

			String xue9mh;
			
			//1) Válassza ki az összes student element, amely a class gyermekei!
			xue9mh = "class/student";
			//2) Válassza ki azt a student elemet, amely rendelkezik "id" attribútummal és értéke "02"!
			xue9mh = "class/student[@id='01']";
			//3) Kiválasztja az összes student elemet, függetlenül attól, hogy hol vannak a dokumentumban!
			xue9mh = "//student";
			//4) Válassza ki a második student element, amely a class root element gyermeke!
			xue9mh = "class/student[2]";
			//5) Válassza ki a utolsó student elemet, amely a class root element gyermeke!
			xue9mh = "class/student[last()]";
			//6) Válassza ki a utolsó elotti student elemet, amely a class root elem gyermeke!
			xue9mh = "class/student[last() - 1]";
			//7) Válassza ki az elso két student elemet, amelyek a class root elem gyermekei!
			xue9mh = "class/student[position() < 3]";
			//8) Válassza ki class root element osszes gyermek elemét!
			xue9mh = "class/*";
			//9) Válassza ki az összes student elemet, amely rendelkezik legalább egy bármilyen attribútummal!
			xue9mh = "class/student[@*]";
			//10) Válassza ki a dokumentum összes elemét!
			xue9mh = "//*";
			//11) Válassza ki a class root element összes student elemét, amelynél a kor > 20 !
			xue9mh = "//*[kor>20]";
			//12) Válassza ki az összes student elem összes keresztnev és vezeteknev csommópontot!
			xue9mh = "class/student/keresztnev | class/student/vezeteknev";

			NodeList nodeList = (NodeList) xPath.compile(xue9mh).evaluate(document, XPathConstants.NODESET);
			
			for(int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				System.out.println("\nAktualis elem: " + node.getNodeName());
				
				//csak a 12. lekerdezesnel hasznalatos
				if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("keresztnev")) {
					Element element = (Element) node;
					
					System.out.println(element.getTextContent());
				}
				//csak a 12. lekerdezesnel hasznalatos
				if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("vezeteknev")) {
					Element element = (Element) node;
					
					System.out.println(element.getTextContent());
				}
				
				
				if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					Element element = (Element) node;
					
					System.out.println("Hallgato ID: " + element.getAttribute("id"));
					
					System.out.println("Keresztnev: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
					
					System.out.println("Vezeteknev: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					
					System.out.println("Becenev: " + element.getElementsByTagName("becenev").item(0).getTextContent());
					
					System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());
				}
			} 

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}catch (ParserConfigurationException e) {
			e.printStackTrace();
		}catch (XPathExpressionException e) {
			e.printStackTrace();
		}

	}
}
