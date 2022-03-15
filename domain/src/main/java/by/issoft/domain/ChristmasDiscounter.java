package by.issoft.domain;

import java.util.Calendar;

public class ChristmasDiscounter implements Discounter{

    private final static Double VALUE_OF_DISCOUNT = 0.5;

    @Override
    public Double applyDiscount(Double price) {
        return Math.round(price * VALUE_OF_DISCOUNT*100.00)/100.00;
    }

    @Override
    public boolean checkCondition() {
        Calendar calendar = Calendar.getInstance();
        long currentDate = calendar.getTimeInMillis();
        calendar.set(Calendar.MONTH,11);
        calendar.set(Calendar.DAY_OF_MONTH,30);
        long endDiscountDate = calendar.getTimeInMillis();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        long startDiscountDate = calendar.getTimeInMillis();
        return currentDate >= startDiscountDate && currentDate <= endDiscountDate;
    }
}
