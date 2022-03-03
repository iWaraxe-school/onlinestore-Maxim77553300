package by.issoft.store.util;

import by.issoft.domain.Product;

import java.util.Comparator;

public class SortedByRate extends Sorted implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        Double rate1 = product1.getRate();
        Double rate2 = product2.getRate();

        return rate1.compareTo(rate2);
    }
}
