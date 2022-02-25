package by.issoft.domain;


public class Product {

//    private final String name;
    private String name;
    private Double rate;
    private ProductPrice price;

    public Product() {
    }

    public Product(String name, Double rate, ProductPrice price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public String getName() {
        return name;
    }


    public Double getRate() {
        return rate;
    }


    public ProductPrice getPrice() {
        return price;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void setPrice(ProductPrice price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", price=" + price +
                '}';
    }

}
