package domXUE9MH;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomReadXUE9MH {
	
	public static void main(String[] args) {
		File xmlFile = new File("src/domXUE9MH/usersXUE9MH.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlFile);
			doc.getDocumentElement().normalize();
			Element root = doc.getDocumentElement();
			
			System.out.println("Root element: " + root.getTagName());
			
			NodeList nodeList = root.getChildNodes();
			
			for (int i = 1; i < nodeList.getLength(); i++) {
//				Node nNode = nodeList.item(i);
//				
//				System.out.println("\nCurrent Element: " + nNode.getNodeName());
				Element el = (Element) nodeList.item(i);
				System.out.println("\nCurrent Element: " + el.getNodeName());
				String attrVal = el.getAttribute("id");
				System.out.println("id: "+attrVal);
				
				NodeList userValues = el.getChildNodes();
				
				
				for(int j = 1; j < userValues.getLength(); j+=2) {
					Element userValue = (Element)userValues.item(j);
					String value = userValue.getTextContent();
					String tagname = userValue.getTagName();
					System.out.println("  "+tagname+" : "+value);
				}
			}
		}catch(IOException | SAXException | ParserConfigurationException | ClassCastException e) {
			e.printStackTrace();
		}
	}
}
