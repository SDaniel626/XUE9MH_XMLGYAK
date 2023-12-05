package hu.domparse.xue9mh;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

//DomRead leszarmazottjakent hozom ezt letre hogy a read metodusokat ne kelljen ujra irni 
public class DomQueryXUE9MH extends DomReadXUE9MH {
	public static void main(String[] args) {
		try {
			//xml file nev megadasa
			File inputFile = new File("XML_XUE9MH.xml");
			
			////Dom-dokumentum letrehozasa az XML dokumentum eleresehez
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			System.out.println("----------------------------");
			
			//Beolvasas
			Scanner myObj = new Scanner(System.in);
			System.out.println("Which element would you like to see?");
			System.out.println("Opciók:Gyros\tGyorsetterem\tRendel\tVasarlo\tBeszallit\tBeszallito\tTulaj\tBankkartya");
			String element = myObj.nextLine(); 

			NodeList nList = doc.getElementsByTagName(element);
			switch(element) {
			  case "Gyros":
				  gyrosRead(nList);
			    break;
			  case "Gyorsetterem":
				  gyorsetteremRead(nList);
			    break;
			  case "Rendel":
				  rendelesekRead(nList);
				    break;
			  case "Vasarlo":
				  vasarloRead(nList);
				    break;
			  case "Beszallit":
				  beszallitRead(nList);
				    break;
			  case "Beszallito":
				  beszallitoRead(nList);
				    break;
			  case "Tulaj":
				  tulajRead(nList);
				    break;
			  case "Bankkartya":
				  bankkartyaRead(nList);
				  break;
			  default:
				  System.out.println("No such element!");
			}
			myObj.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
