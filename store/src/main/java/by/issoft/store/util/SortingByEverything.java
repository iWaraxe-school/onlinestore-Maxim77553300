package by.issoft.store.util;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortingByEverything<T extends Object> {

    protected Map<Product,T> sortedMap = new LinkedHashMap<>();

    public Map<Product, T> getSortedGoods(Store store) {

        System.out.println(store.toString());
        List<Product> products = store.getAllStoreGoods(store);

        for (Category category : store.categories) {
            products.addAll(category.getProducts());
        }

        System.out.println(products.size());

     //   SortingComparator<T> sortingComparator = new SortingComparator<>();
      //  products.sort(sortingComparator);

        Map<Product, Double> sortedMap = products.stream().collect(Collectors.toMap(Function.identity(), Product::getPrice));
       // Map<Product, Double> sortedMap = products.stream().collect(Collectors.toMap(Function.identity(), Product::getRate));
       // Map<Product, T> sortedMap = products.stream().collect(Collectors.toMap(Function.identity(), Product::getName));

//        for(Map.Entry<Product, Double> pair : sortedMap.entrySet())
//        {
//            String name = pair.getKey().getName();
//            Double value = pair.getValue();
//            System.out.println(name + " " + value);
//        }

        sortedMap.entrySet().stream()
                .sorted(Map.Entry.<Product, Double>comparingByValue())
                .forEach(a -> System.out.println(a.getKey().getName() + " "  + Output.commandName + " " + a.getValue()));
        return (Map<Product, T>) sortedMap;
    }


}
