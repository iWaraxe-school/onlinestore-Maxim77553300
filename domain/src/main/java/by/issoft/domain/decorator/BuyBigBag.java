package by.issoft.domain.decorator;

public class BuyBigBag extends OptionDecorator{
    public BuyBigBag(Service service) {
        super(service,  "+ big bag ", 50.00);
    }
}
