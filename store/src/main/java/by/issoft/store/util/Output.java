package by.issoft.store.util;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Output {

    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static List<Product> products;
    protected static Store storeObject;
    private static int count = 0;

    public static void printAllGoods() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends Store> storeClass = Store.class;
        storeObject = storeClass.getConstructor().newInstance();
        products = storeObject.getAllStoreGoods(storeObject);
        for (Category category : storeObject.categories) {
            if (category.getProducts() != null) {
                for (Product product : category.getProducts()) {
                    System.out.print(product);
                }
            } else {
                continue;
            }
        }
    }

    public static void printCommand() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        printAllGoods();

        while (true) {
            System.out.println("PLEASE, ENTER COMMAND:\nsort \ntop \nquit \n");
            String line = reader.readLine();
            switch (line) {
                case "sort" -> printSortedGoods();
                case "top" -> printTop();
                case "quit" -> {
                    return;
                }
            }
        }
    }

    private static void printSortedGoods() {
        System.out.println("After sorting :");
        fillProducts();
        Sorting sorting = new Sorting();
        List<Product> sort = sorting.sortAllProducts(products);
        System.out.println(sort);
    }

    private static void printTop() {
        fillProducts();
        Sorting sorting = new Sorting();
        System.out.println(sorting.getTop());
    }

    private static void fillProducts() {
        if (count == 0) {
            products = storeObject.getAllStoreGoods(storeObject);
            for (Category category : storeObject.categories) {
                if (category.getProducts() != null) {
                    products.addAll(category.getProducts());
                } else {
                    continue;
                }
            }
            count++;
        }
    }
}







