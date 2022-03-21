package by.issoft.store;


import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.dao.CategoryDao;
import by.issoft.store.dao.ProductDao;
import by.issoft.store.service.CategoryService;
import by.issoft.store.service.ProductService;

import java.lang.reflect.Method;
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

       // fillDataBase();
        return productList;

    }

//    public void fillDataBase() throws SQLException {
//        ProductDao productService = new ProductService();
//
//        CategoryDao categoryDao = new CategoryService();
//        categoryDao.addListCategory(categories);
//        productService.addListProduct(productList);
//    }
}
