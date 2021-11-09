package xpathxue9mh1109;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xPathXUE9MH {
	
	public static void main(String[] args) {
		try {
			//File xmlFile = new File("studentXUE9MH.xml");
			
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse("src/xpathxue9mh1109/studentXUE9MH.xml");
			
			document.getDocumentElement().normalize();
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			String expression;
			
			//1) Válassza ki az összes student element, amely a class gyermekei.
			expression = "class/student";
			//2) Válassza ki azt a student elemet, amely rendelkezik "id" attribútummal és értéke "01".
			expression = "class/student[@id='01']";
			//3) Kiválasztja az összes student elemet, függetlenül attól, hogy hol vannak a dokumentumban.
			expression = "//student";
			//4) Válassza ki a második student element, amely a class elem gyermeke.
			expression = "class/student[2]";
			//5) Válassza ki a utolsó student elemet, amely a class elem gyermeke.
			expression = "class/student[last()]";
			//6) 
			
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			for(int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				System.out.println("\nAktualis elem: " + node.getNodeName());
				
				
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
