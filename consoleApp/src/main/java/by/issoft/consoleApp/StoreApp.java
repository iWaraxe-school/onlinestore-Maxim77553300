package by.issoft.consoleApp;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.ProductPrice;
import by.issoft.store.RandomStorePopulator;
import by.issoft.store.Store;
import by.issoft.store.StoreHelper;
import com.github.javafaker.Faker;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class StoreApp {
    public static void main(String[] args) {
        Store store = new Store();

        store.printAllStoreGoods(store);

    }
}

