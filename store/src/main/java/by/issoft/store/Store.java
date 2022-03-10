package by.issoft.store;


import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Store {

    public List<Category> categories = new ArrayList<>();
    public List<Product> productList = new ArrayList<>();

    public Store() {
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<Product> getAllStoreGoods(Store store) {

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

//        for (Category category : categories) {
//            productList = category.getProducts();
//        }
        return productList;

    }
}
