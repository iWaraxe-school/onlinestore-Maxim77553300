package by.issoft.store.populator;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.ProductPrice;
import by.issoft.store.Order;

import java.util.ArrayList;
import java.util.List;

public class RandomStorePopulator implements Populator {

    public static List<Category> fakerCategory = new ArrayList<>();

    public String getName(Category category) {
        return category.toString() + " " + category.getFaker().company().name();
    }

    public Double getRate(Category category) {
        return (double) Math.round((1.00 + category.getFaker().random().nextDouble() * (99.00)));
    }

    public ProductPrice getPrice(Category category) {
        return category.getPrice(category);
    }

    public Integer getCategoryId(Category category) {
        return category.getId();
    }

    public void setPrice(ProductPrice price) {
    }

    @Override
    public List<Category> getAllCategories() {
        return fakerCategory;
    }

    @Override
    public void addToCart(Product product) {
        List<Product> productList = new ArrayList<>();
        new Thread(new Order(productList)).start();
    }
}
