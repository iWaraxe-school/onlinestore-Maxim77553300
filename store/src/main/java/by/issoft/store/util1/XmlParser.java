package by.issoft.store.util1;

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
import java.util.Map;
import java.util.Properties;

// Dom Parser
public class XmlParser {

    private String pathXml;

    private Properties prop;

    protected static Map<String, String> configMap = new LinkedHashMap<>();

    public String getPathXml() {
        return pathXml;
    }

    {
        try {
            prop = readPropertiesFile("store/src/main/resources/app.properties");
            pathXml = prop.getProperty("pathXml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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

    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
        }

        return prop;
    }
}
