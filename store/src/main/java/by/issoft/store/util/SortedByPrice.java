package by.issoft.store.util;

import by.issoft.domain.Product;

import java.util.Comparator;

public class SortedByPrice extends Sorted implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        Double price1 = product1.getPrice();
        Double price2 = product2.getPrice();

        if (price1 > price2) {
            return 1;
        } else if (price1 < price2) {
            return -1;
        } else {
            return 0;
        }
    }
}
