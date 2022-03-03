package by.issoft.store.util;

import by.issoft.domain.Product;

import java.util.Comparator;

public class SortedByName extends Sorted implements Comparator<Product> {

    @Override
    public int compare(Product product1, Product product2) {
        String name1 = product1.getName();
        String name2 = product2.getName();

        return name1.compareTo(name2);
    }
}
