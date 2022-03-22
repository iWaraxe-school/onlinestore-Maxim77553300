package by.issoft.store;


import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Store {

    private static Store instance;

    public List<Category> categories = new ArrayList<>();
    protected List<Product> productList = new ArrayList<>();


    // pattern Singleton-------------------------
    private Store() {
    }

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }


    public List<Product> getProductList() {
        return productList;
    }

    public List<Product> getAllStoreGoods() throws SQLException {

        StoreHelper storeHelper = new StoreHelper();
        storeHelper.fillStore();

        return getProductList();

    }

}
