package by.issoft.consoleApp;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.util.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;


public class StoreApp {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, ParserConfigurationException, SAXException {
        XmlParser xmlParser = new XmlParser();
        Output.printAllGoods();
        Output.printCommand();

        Map<String, String> map = xmlParser.parseXmlConfig("store/src/main/resources/config.xml");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.print(entry.getKey() + ":");
            System.out.println(entry.getValue());
        }
        System.out.println("How do you want to sort products? Make your choice!");

        Class<? extends Store> storeClass = Store.class;
        Store storeObject = storeClass.getConstructor().newInstance();

        List<Product> products = storeObject.getAllStoreGoods(storeObject);
        for (Category category : storeObject.categories) {

            for (int i = 0; i < storeObject.categories.size(); i++) {
                products.addAll(category.getProducts());
            }
        }
        Sorted sorted = new SortedByPrice();
        System.out.println(Sort.sortProducts(products, sorted));
    }

}




