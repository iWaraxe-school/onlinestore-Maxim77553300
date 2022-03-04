package by.issoft.store.util;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Output {

    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Sorted sorted;

    public static void printAllGoods() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends Store> storeClass = Store.class;
        Store storeObject = storeClass.getConstructor().newInstance();

        List<Product> products = storeObject.getAllStoreGoods(storeObject);
        for (Category category : storeObject.categories) {
            System.out.println("Section " + category.getNameCategory());
            for (Product product : category.getProducts()) {
                System.out.print(product);
            }
        }
    }

    public static boolean printCommand() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        while (true) {
            System.out.println("PLEASE, ENTER COMMAND:\nsort \ntop \nquit \n");
            String line = reader.readLine();
            switch (line) {
                case "sort" -> printSortMenu();
                case "top" -> System.out.println("top sort!!");
                case "quit" -> {
                    return false;
                }
            }
        }
    }

    public static void printSortMenu() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        XmlParser xmlParser = new XmlParser();
        Map<String, String> map = xmlParser.parseXmlConfig("store/src/main/resources/config.xml");

        System.out.println("How do you want to sort products? Make your choice!");
        int count = 1;
        for (Map.Entry<String, String> entry : map.entrySet()) {

            System.out.print(count + " " + entry.getKey() + ":");
            System.out.println(entry.getValue());
            count++;
        }
        String s = reader.readLine();
        switch (s) {
            case "1" -> {
                System.out.println("Sort by rate");
                sorted = new SortedByRate();
                getSortedGoods();
            }
            case "2" -> {
                System.out.println("Sort by price");
                sorted = new SortedByPrice();
                getSortedGoods();
            }
            case "3" -> {
                System.out.println("Sorted by name");
                sorted = new SortedByName();
                getSortedGoods();
            }
        }
    }

    private static void getSortedGoods() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class<? extends Store> storeClass = Store.class;
        Store storeObject = storeClass.getConstructor().newInstance();

        List<Product> products = storeObject.getAllStoreGoods(storeObject);
        for (Category category : storeObject.categories) {
            products.addAll(category.getProducts());
        }

        System.out.println(Sort.sortProducts(products, sorted));
    }
}
