package by.issoft.store.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Dom Parser
public class XmlParser {

    public Map<String, String> parseXmlConfig(String pathXml) {
        Document doc = null;
        try {
            doc = buildDocument(pathXml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Node rootNode = doc.getFirstChild();

        NodeList nodeList = rootNode.getChildNodes();

        return fillMap(nodeList);
    }

    private Document buildDocument(String path) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        return documentBuilderFactory.newDocumentBuilder().parse(file);
    }

    private static Map<String, String> fillMap(NodeList nodeList) {
        Map<String, String> configMap = new HashMap<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (nodeList.item(i).getNodeName().equals("sort")) {
                continue;
            }
            configMap.put(nodeList.item(i).getNodeName(), nodeList.item(i).getTextContent());
        }
        return configMap;
    }
}
