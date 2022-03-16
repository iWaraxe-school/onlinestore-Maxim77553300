package by.issoft.domain.decorator;

public class BuyFirstProduct implements Service{

    private final String name;
    private final Double price;

    public BuyFirstProduct(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
