package by.issoft.store.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

// Dom Parser
public class XmlParser {

    private String pathXml;

    public String getPathXml() {
        return pathXml;
    }

    {
        try {
            Properties prop = readPropertiesFile("store/src/main/resources/app.properties");
            pathXml = prop.getProperty("pathXml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> parseXmlConfig(String pathXml) {
        Document doc = null;
        NodeList nodeList = null;
        try {
            doc = buildDocument(pathXml);
            Node rootNode = doc.getFirstChild();
            nodeList = rootNode.getChildNodes();
        } catch (Exception e) {
            System.out.println("We don't have config data of sorting, please enter it!! Error! " + e.getMessage());
            throw new RuntimeException();
        }

        return getAllPropertiesMap(nodeList);
    }

    private Document buildDocument(String path) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        return documentBuilderFactory.newDocumentBuilder().parse(file);
    }

    private static Map<String, String> getAllPropertiesMap(NodeList nodeList) {

        Map<String, String> configMap = new LinkedHashMap<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (nodeList.item(i).getNodeName().equals("sort")) {
                continue;
            }
            configMap.put(nodeList.item(i).getNodeName(), nodeList.item(i).getTextContent().toUpperCase(Locale.ROOT));
        }
        return configMap;
    }

    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) fis.close();
        }
        return prop;
    }
}
