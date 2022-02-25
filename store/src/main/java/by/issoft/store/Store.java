package by.issoft.store;


import by.issoft.domain.Category;
import by.issoft.domain.Product;
import com.google.common.eventbus.Subscribe;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Store {

    public List<Category> categories = new ArrayList<>();
    public List<Product> productList;

    public Store() {
    }

    public void printAllStoreGoods(Store store) {

        StoreHelper storeHelper = new StoreHelper(store);
        StoreHelper.createRandomCategoryMap();
        storeHelper.fillStore();
        for (Category category : categories) {
            category.printAllProducts();
        }


    }



}
