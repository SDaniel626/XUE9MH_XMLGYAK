package hu.domparse.xue9mh;


import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class DomWriteXUE9MH {
	public static void main(String[] args) {
        try {
            // Beolvasom az xml file tartalmat
            File inputFile = new File("XML_XUE9MH.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);

            // Kiiratom az xml tartalmat konzolra
            printDocument(document);

            // Kiirom a dokumentumot egy uj fajlba
            File outputFile = new File("XML_XUE9MH1.xml");
            writeDocument(document, outputFile);

            System.out.println("XML content written to 'output.xml'.");

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
}
