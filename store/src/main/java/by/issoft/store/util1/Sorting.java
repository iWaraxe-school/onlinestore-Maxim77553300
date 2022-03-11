package by.issoft.store.util1;

import by.issoft.domain.Product;

import java.util.*;
import java.util.stream.Collectors;

public class Sorting {

    public static List<Product> sort(List<Product> productList){
        XmlParser xmlParser = new XmlParser();


        Map<String, String> configMap = xmlParser.parseXmlConfig(xmlParser.getPathXml());
        Collection<String> values =  configMap.values();

        System.out.println(values.stream().collect(Collectors.toList()).get(0));
        System.out.println(values.stream().collect(Collectors.toList()).get(1));
        System.out.println(values.stream().collect(Collectors.toList()).get(2));

      //  Comparator<Map<String,String>> cmp = Comparator.comparing(configMap::get)

       List<Product> collect = productList.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
//        List<Product> collect = productList.stream()
//                .sorted(Comparator.comparing(Product::getName)
//                        .thenComparing(Product::getPrice)
//                        .thenComparing(Product::getRate))
//                .collect(Collectors.toList());
        return collect;

    }

    private static List<Product> ascMethod(List<Product> productList){
        List<Product> collect = productList.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
        return collect;
    }
    private static List<Product> descMethod(List<Product> productList){
        List<Product> collect = productList.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).collect(Collectors.toList());
        return collect;
    }

}
