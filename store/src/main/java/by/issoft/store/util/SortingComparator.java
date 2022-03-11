package by.issoft.store.util;

import by.issoft.domain.Product;

import java.util.Comparator;
import java.util.Map;

public class SortingComparator implements Comparator<Product> {


    //Map<Price,Desc>
    Map<String, String> configMap;


    @Override
    public int compare(Product o1, Product o2) {
        return 0;
    }
}
