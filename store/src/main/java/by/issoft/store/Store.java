package by.issoft.store;


import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.List;

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
