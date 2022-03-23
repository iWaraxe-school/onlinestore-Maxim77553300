package by.issoft.domain.decorator;

public class BuySmallBag extends OptionDecorator{
    public BuySmallBag(Service service) {
        super(service, "+ small bag ", 10.00);
    }
}
