package by.issoft.store.populator;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Order;
import by.issoft.store.Store;
import by.issoft.store.dao.CategoryDao;
import by.issoft.store.dao.ProductDao;
import by.issoft.store.service.CategoryServiceImpl;
import by.issoft.store.service.ProductServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PopulatorBd implements Populator {

    protected static Store storeObject = Store.getInstance();
    private static List<Product> products;

    @Override
    public List<Category> getAllCategories() {
        return storeObject.categories;
    }

    @Override
    public void addToCart(Product product) {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        new Thread(new Order(productList)).start();
    }

    public static void printAllGoods() throws SQLException {
        products = storeObject.getAllStoreGoods();
        ProductDao productService = new ProductServiceImpl();
        CategoryDao categoryDao = new CategoryServiceImpl();
        categoryDao.addListCategory(storeObject.categories);
        for (Category category : storeObject.categories) {
            if (category.getProducts() != null) {
                productService.addListProduct(category.getProducts());
            }
        }
        System.out.println(productService.getAll());
    }

    public List<Product> getAllGoods() {
        return storeObject.getProductList();
    }
}
