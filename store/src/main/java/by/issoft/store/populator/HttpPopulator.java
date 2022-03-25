package by.issoft.store.populator;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Order;
import by.issoft.store.dao.CategoryDao;
import by.issoft.store.service.CategoryServiceImpl;
import by.issoft.store.util.server.HttpClient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HttpPopulator implements Populator {

    private final HttpClient httpClient = new HttpClient();

    @Override
    public List<Category> getAllCategories() {
        CategoryDao categoryDao = new CategoryServiceImpl();
        List<Category> all = new ArrayList<>();
        try {
            all = categoryDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    @Override
    public void addToCart(Product product) {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        new Thread(new Order(productList)).start();
        httpClient.addProductToCart(product);
    }
}
