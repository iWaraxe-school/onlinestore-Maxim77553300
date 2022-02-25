package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.ProductPrice;
import com.github.javafaker.Faker;

public class RandomStorePopulator {

    private String name;
    private Double rate;
    private ProductPrice price;
    Faker faker = new Faker();


    public String getName(Category category) {
        name = category.toString() + " " + faker.food().dish();
        return name;
    }

    public Double getRate() {
        rate = faker.random().nextDouble();
        return rate;
    }

    public ProductPrice getPrice() {
        price = new ProductPrice(faker.random().nextDouble());
        return price;
    }

    public void setPrice(ProductPrice price) {
        this.price = price;
    }

}
