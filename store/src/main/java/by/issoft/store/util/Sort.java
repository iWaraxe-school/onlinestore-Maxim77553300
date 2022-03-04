package by.issoft.store.util;

import by.issoft.domain.Product;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Sort {

    public static List<Product> sortProducts(List<Product> products, Sorted kindOfSort) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends Sorted> aClass = kindOfSort.getClass();
        Sorted objectKindOfSorted = aClass.getConstructor().newInstance();

        products.sort(objectKindOfSorted);
        return products;
    }


}
