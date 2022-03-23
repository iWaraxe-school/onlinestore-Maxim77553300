package by.issoft.domain.decorator;
// test Pattern Decorator ---------------------------------
public class OptionDecorator implements Service{

    private final Service service;
    private final String name;
    private final Double price;

    public OptionDecorator(Service service, String name, Double price) {
        this.service = service;
        this.name = name;
        this.price = price;
    }

    @Override
    public Double getPrice() {
        return this.price + service.getPrice();
    }

    @Override
    public String getName() {
        return this.name + service.getName();
    }
}
