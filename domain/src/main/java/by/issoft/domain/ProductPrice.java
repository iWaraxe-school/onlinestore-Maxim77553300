package by.issoft.domain;


import com.google.common.base.Preconditions;

public class ProductPrice {

    private final Double price;

    private ProductPrice(Double price) {
        this.price = price;
    }

    // example - factory method
    public static ProductPrice of(Double price) {
        Preconditions.checkArgument(price > 0, "Price must be positive");
        Preconditions.checkArgument(price < 10000, "Price must less than 10000");

        return new ProductPrice(price);
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Price :" + price;
    }
}
