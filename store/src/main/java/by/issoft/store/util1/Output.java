package by.issoft.store.util1;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.StoreHelper;
import by.issoft.store.util1.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Output {

    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static List<Product> products;
    private static Store storeObject;


    public static void printAllGoods() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends Store> storeClass = Store.class;
        storeObject = storeClass.getConstructor().newInstance();

        products = storeObject.getAllStoreGoods(storeObject);
        for (Category category : storeObject.categories) {
            System.out.println("Section " + category.getNameCategory());
            for (Product product : category.getProducts()) {
                System.out.print(product);
            }
        }
    }

    public static boolean printCommand() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        printAllGoods();

        while (true) {
            System.out.println("PLEASE, ENTER COMMAND:\nsort \ntop \nquit \n");
            String line = reader.readLine();
            switch (line) {
                case "sort" -> printSortedGoods();
                case "top" -> printAllGoods();
                case "quit" -> {
                    return false;
                }
            }
        }
    }

    private static void printSortedGoods() {
        System.out.println("After sorting :");

        products = storeObject.getAllStoreGoods(storeObject);
        for (Category category : storeObject.categories) {
            products.addAll(category.getProducts());
        }

        List<Product> sort = Sorting.sort(products);
        System.out.println(sort);

    }

}







