package Lab01;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;

public class ParseXML {

    private static final CurrencyRateTable currencyRateTable = CurrencyRateTable.getInstance();

    public static void parseXML(StringBuffer buffer) throws IOException {

        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(buffer.toString())));

            NodeList tableNumbers = document.getElementsByTagName("numer_tabeli");
            Element tableNumberElement = (Element) tableNumbers.item(0);
            currencyRateTable.setTableNumber(tableNumberElement.getTextContent());

            NodeList dateOfPublication = document.getElementsByTagName("data_publikacji");
            Element dateOfPublicationElement = (Element) dateOfPublication.item(0);
            currencyRateTable.setDateOfPublication(dateOfPublicationElement.getTextContent());

            NodeList positionList = document.getElementsByTagName("pozycja");
            for (int i = 0; i < positionList.getLength(); i++){
                Node p = positionList.item(i);

                if (p.getNodeType() == Node.ELEMENT_NODE){
                    Element position = (Element) p;
                    NodeList list = position.getChildNodes();

                    LinkedList<String> strings = new LinkedList<>();

                    for (int j = 0; j < list.getLength(); j++){
                        Node n = list.item(j);

                        if (n.getNodeType() == Node.ELEMENT_NODE){
                            Element element = (Element) n;

                            strings.add(element.getTextContent());
                        }
                    }

                    Currency currency = new Currency(strings.get(0), Double.parseDouble(strings.get(1).replace(",", "."))
                            , strings.get(2), Double.parseDouble(strings.get(3).replace(",", ".")));
                    currencyRateTable.add(currency);


                }
            }
            Currency zl = new Currency("złoty polski", 1, "PLN", 1);
            currencyRateTable.add(zl);

        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

}
