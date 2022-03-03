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


    public static void printAllGoods() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
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

    public static void printCommand() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean i = true;
            while (i) {
                System.out.println("PLEASE, ENTER COMMAND:\nsort \ntop \nquit \n");
                String line = reader.readLine();
                String line2 = switch (line) {
                    case "sort" -> "Please, chose variant of sort:\nname\nrate\nprice";
                    case "top" ->  "1";
                    case "quit" -> "3";
                    default -> "Error! Please enter real command!!";
                };
                System.out.println(line2);
                if (line2.equals("3")) {
                    i = false;
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
