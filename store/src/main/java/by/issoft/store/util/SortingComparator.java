package by.issoft.store.util;

import by.issoft.domain.Product;

import java.util.Comparator;
import java.util.Map;

public class SortingComparator implements Comparator<Product> {

    //Map<Price,Desc>
    Map<String, String> configMap;

    @Override
    public int compare(Product product1, Product product2) {

        configMap = XmlParser.configMap;


        String priceValue = configMap.entrySet().stream().filter(a -> a.getKey().equals("price")).map(a -> a.getValue()).toString();
        String rateValue = configMap.entrySet().stream().filter(a -> a.getKey().equals("rate")).map(a -> a.getValue()).toString();
        String nameValue = configMap.entrySet().stream().filter(a -> a.getKey().equals("price")).map(a -> a.getValue()).toString();



        if (rateValue.equals("asc")) {
            return product1.getRate().compareTo(product2.getRate());
        } else {
            return product2.getRate().compareTo(product1.getRate());
        }

        if (priceValue.equals("asc")) {
            return product1.getPrice().compareTo(product2.getPrice());
        } else {
            return product2.getPrice().compareTo(product1.getPrice());
        }

        if (nameValue.equals("asc")) {
            return product1.getName().compareTo(product2.getName());
        } else {
            return product2.getName().compareTo(product1.getName());
        }
    }



}
