package by.issoft.store;


import by.issoft.domain.Category;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Store {

    public List<Category> categories = new ArrayList<>();
    // public List<Product> productList;

    public Store() {
    }

    public void printAllStoreGoods(Store store) {
//
//        StoreHelper storeHelper = new StoreHelper(store);
//        StoreHelper.createRandomCategoryMap();
//        storeHelper.fillStore();

        Class<StoreHelper> storeHelperClass = StoreHelper.class;

        try {
            StoreHelper storeHelper = storeHelperClass.getConstructor().newInstance();
            storeHelper.setStore(store);
            Method createRandomCategoryMap = storeHelperClass.getMethod("createRandomCategoryMap");
            createRandomCategoryMap.setAccessible(true);
            createRandomCategoryMap.invoke(storeHelper);
            storeHelper.fillStore();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        for (Category category : categories) {
            category.printAllProducts();
        }

    }
}
