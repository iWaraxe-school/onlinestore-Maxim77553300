package by.issoft.store.util;

import by.issoft.domain.Product;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Sorting {

    public List<Product> sortAllProducts(List<Product> productList) {
        Map<String, String> configMap;

        XmlParser xmlParser = new XmlParser();
        configMap = xmlParser.parseXmlConfig(xmlParser.getPathXml());

        return sortAllProducts(configMap);
    }

    public List<Product> sortAllProducts(Map<String, String> sortBy) {

        List<Product> allProducts = Output.storeObject.getProductList();
        allProducts.sort(new ProductComparator(sortBy));
        return allProducts;
    }

    public List<Product> getTop() {

        Map<String, String> sortBy = new HashMap<>();
        List<Product> sortedList = sortAllProducts(sortBy);

        return sortedList.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(5).collect(Collectors.toList());
    }

}
