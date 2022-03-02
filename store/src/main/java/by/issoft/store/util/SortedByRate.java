package by.issoft.store.util;

import by.issoft.domain.Product;

import java.util.Comparator;

public class SortedByRate implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        Double rate1 = product1.getRate();
        Double rate2 = product2.getRate();

        if(rate1 > rate2){
            return 1;
        } else if(rate1 < rate2){
            return -1;
        } else {
            return 0;
        }
    }
}
