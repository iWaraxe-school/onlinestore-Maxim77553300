package by.issoft.consoleApp;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.*;


public class StoreApp {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {

        printAllGoods();

        printCommand();

        getConfigCommand();

    }


    private static void printAllGoods() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends Store> storeClass = Store.class;
        Store storeObject = storeClass.getConstructor().newInstance();

        List<Product> products = storeObject.getAllStoreGoods(storeObject);
        for (Category category : storeObject.categories) {
            System.out.println("Section " + category.getNameCategory());
            for (Product product : category.getProducts()) {
                System.out.print(product);
                System.out.println();
            }
        }
    }

    private static void printCommand() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean i = true;
            while (i) {
                System.out.println("PLEASE, ENTER COMMAND:\nsort \ntop \nquit \n");
                String line = reader.readLine();
                String line2 = switch (line) {
                    case "sort" -> "0";
                    case "top" -> "2";
                    case "quit" -> "3";
                    default -> "Error! Please enter real command!!";
                };
                System.out.println(line2);
                if (line2 == "3") {
                    i = false;
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private static void getConfigCommand(){
        try {
            File inputFile = new File("consoleApp/src/main/resources/config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath =  XPathFactory.newInstance().newXPath();

            String expression = "/sort";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    System.out.println("Name : "
                            + eElement
                            .getElementsByTagName("name")
                            .item(0)
                            .getTextContent());
                    System.out.println("Rate : "
                            + eElement
                            .getElementsByTagName("rate")
                            .item(0)
                            .getTextContent());
                    System.out.println("Price : "
                            + eElement
                            .getElementsByTagName("price")
                            .item(0)
                            .getTextContent());
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}




