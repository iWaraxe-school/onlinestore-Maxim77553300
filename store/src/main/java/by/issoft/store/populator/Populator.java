package by.issoft.store.populator;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.List;

public interface Populator {

    List<Category> getAllCategories();

    void addToCart(Product product);
}
