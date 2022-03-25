package by.issoft.store.service;

import by.issoft.domain.Product;
import by.issoft.store.dao.ProductDao;

import java.sql.SQLException;
import java.util.List;

public interface ProductService extends ProductDao {

    void add(Product product) throws SQLException;

    List<Product> getAll() throws SQLException;

    Product getById(Integer id) throws SQLException;

    void delete(Product product) throws SQLException;

    void addListProduct(List<Product> productList) throws SQLException;
}
