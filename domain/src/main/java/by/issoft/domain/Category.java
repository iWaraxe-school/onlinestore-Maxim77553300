package by.issoft.domain;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category {

    private Integer id;
    private String nameCategory;
    private List<Product> products;
    private final Faker faker = new Faker();
    private ProductPrice price;
    private Double min;
    private Double max;

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public Faker getFaker() {
        return faker;
    }

    public ProductPrice getPrice(Category category) {
        generatePrice(category,min,max);
        return price;
    }

    public void setPrice(ProductPrice price) {
        this.price = price;
    }

    public List<Product> getProducts() {
        return products;
    }



    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Category(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public void addProducts(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
    }

    public void printAllProducts() {
        System.out.println("Section " + this.nameCategory);
        for (Product product : products) {
            System.out.print(product);
            System.out.println();
        }
    }

    public ProductPrice generatePrice(Category category, Double min, Double max) {
        price = ProductPrice.of(((double) Math.round(min + faker.random().nextDouble() * (max))));
        return price;
    }

    @Override
    public String toString() {
        return this.nameCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return nameCategory.equals(category.nameCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameCategory);
    }
}
