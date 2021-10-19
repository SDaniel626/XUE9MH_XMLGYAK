package SaxXUE9MH1019;


import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxXUE9MH {

	public static void main(String[] args) {
		try {
			SAXParserFactory saxParseFactory = SAXParserFactory.newInstance();
			
			SAXParser saxParser = saxParseFactory.newSAXParser();
			
			SaxHandler handler = new SaxHandler();
			
			saxParser.parse(new File("szemelyekXUE9MH.xml"), handler);
		}catch(ParserConfigurationException | SAXException | IOException e ) {
			e.printStackTrace();
		}
			
	}

}
