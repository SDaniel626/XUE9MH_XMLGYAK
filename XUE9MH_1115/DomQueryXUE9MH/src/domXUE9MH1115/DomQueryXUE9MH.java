package domXUE9MH1115;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryXUE9MH {
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException
	{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("XUE9MH_kurzusfelvetel.xml");

        NodeList kurzusokNodeList = doc.getElementsByTagName("kurzusnev");
        System.out.print("Kurzusn�v: [");
        for (int i = 0; i < kurzusokNodeList.getLength(); i++)
		{
            Node kurzusNode = kurzusokNodeList.item(i);
            System.out.print(kurzusNode.getTextContent());
            if (i < kurzusokNodeList.getLength() - 1)
                System.out.print(", ");
        }

        System.out.println("]");

        // Elso hallgat� adatainak ki�r�sa konzolra
        NodeList hallgatokNodeList = doc.getElementsByTagName("hallgato");
        Node elsoHallgatoNode = hallgatokNodeList.item(0);
        Element elsoHallgatoElem = (Element) elsoHallgatoNode;

        System.out.println("Hallgat� neve: " + elsoHallgatoElem.getElementsByTagName("hnev").item(0).getTextContent());
        System.out.println("Sz�let�si �v: " + elsoHallgatoElem.getElementsByTagName("szulev").item(0).getTextContent());
        System.out.println("Szak: " + elsoHallgatoElem.getElementsByTagName("szak").item(0).getTextContent());
        System.out.println();

        // Elso hallgat� adatainak ki�r�sa f�jlba
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("XUE9MH_kurzusfelvetel_lekerdezes.txt")))
		{
            writer.write("Hallgat� neve: " + elsoHallgatoElem.getElementsByTagName("hnev").item(0).getTextContent() + "\n");
            writer.write("Sz�let�si �v: " + elsoHallgatoElem.getElementsByTagName("szulev").item(0).getTextContent() + "\n");
            writer.write("Szak: " + elsoHallgatoElem.getElementsByTagName("szak").item(0).getTextContent() + "\n\n");
        }

        // Kurzusokat oktat�k nev�nek lek�rdez�se
        NodeList oktatokNodeList = doc.getElementsByTagName("oktato");
        HashSet<String> oktatokNeve = new HashSet<>();

        for (int i = 0; i < oktatokNodeList.getLength(); i++)
		{
            Node oktatoNode = oktatokNodeList.item(i);
            oktatokNeve.add(oktatoNode.getTextContent());
        }

        System.out.println("\nOktat�k nevei: " + oktatokNeve);

        String oktatoNeve = "Dr. Bednarik L�szl�";

        NodeList kurzusokNodeList2 = doc.getElementsByTagName("kurzus");

        for (int i = 0; i < kurzusokNodeList2.getLength(); i++)
		{
            Node kurzusNode = kurzusokNodeList2.item(i);
            Element kurzusElem = (Element) kurzusNode;

            NodeList kurzusOktatokNodeList = kurzusElem.getElementsByTagName("oktato");

            for (int j = 0; j < kurzusOktatokNodeList.getLength(); j++)
			{
                Node oktatoNode = kurzusOktatokNodeList.item(j);

                if (oktatoNode.getTextContent().equals(oktatoNeve))
				{
                    System.out.println("Oktat� neve: " + oktatoNeve);
                    System.out.println("Kurzusn�v: " + kurzusElem.getElementsByTagName("kurzusnev").item(0).getTextContent());
                    System.out.println("Kredit: " + kurzusElem.getElementsByTagName("kredit").item(0).getTextContent());
                    System.out.println("Hely: " + kurzusElem.getElementsByTagName("hely").item(0).getTextContent());
                    System.out.println("Idopont: " + kurzusElem.getElementsByTagName("idopont").item(0).getTextContent());
                    System.out.println();
                    break;
                }
            }
        }
    }
}
