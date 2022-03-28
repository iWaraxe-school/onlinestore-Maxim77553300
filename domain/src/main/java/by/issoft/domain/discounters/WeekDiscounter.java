package by.issoft.domain.discounters;

import java.util.Calendar;

// strategy pattern--------------
public class WeekDiscounter implements Discounter {

    private final static Double VALUE_OF_DISCOUNT = 0.7;

    @Override
    public Double applyDiscount(Double price) {
        return Math.round(price * VALUE_OF_DISCOUNT * 100.00) / 100.00;
    }

    @Override
    public boolean checkCondition() {
        Calendar calendar = Calendar.getInstance();
        long currentDate = calendar.getTimeInMillis();
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.DAY_OF_MONTH, 20);
        long endDiscountWeek = calendar.getTimeInMillis();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        long startDiscountWeek = calendar.getTimeInMillis();

        return currentDate >= startDiscountWeek && currentDate <= endDiscountWeek;
    }
}
