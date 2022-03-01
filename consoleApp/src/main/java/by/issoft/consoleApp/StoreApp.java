package by.issoft.consoleApp;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class StoreApp {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

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
}

