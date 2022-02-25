package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.ProductPrice;
import com.github.javafaker.Faker;

public class RandomStorePopulator {

    private ProductPrice price;
    Faker faker = new Faker();

    public String getName(Category category) {
        return category.toString() + " " + faker.food().dish();
    }

    public Double getRate() {
        return faker.random().nextDouble();
    }

    public ProductPrice getPrice() {
        price = new ProductPrice(faker.random().nextDouble());
        return price;
    }

    public void setPrice(ProductPrice price) {
        this.price = price;
    }

}
