package by.issoft.store.util;

import by.issoft.domain.Product;

import java.util.Comparator;
import java.util.Map;

public class SortedByPriceTop extends Sorted implements Comparator<Product> {

    private static final String name = "price";

    @Override
    public int compare(Product product1, Product product2) {

        KindOfSort sort = KindOfSort.DESC;
        Double price1 = product1.getPrice();
        Double price2 = product2.getPrice();

        return price2.compareTo(price1);

    }


}



