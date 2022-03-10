package by.issoft.store.util;

import by.issoft.domain.Product;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class SortedByPrice extends Sorted implements Comparator<Product> {

    private static final String name = "price";


    @Override
    public int compare(Product product1, Product product2) {

        KindOfSort sort = getSortParams();
        Double price1 = product1.getPrice();
        Double price2 = product2.getPrice();
        if (sort == KindOfSort.ASC) {
            return price1.compareTo(price2);
        } else {
            return price2.compareTo(price1);
        }
    }

    private static KindOfSort getSortParams() {

        XmlParser xmlParser = new XmlParser();
        Map<String, String> stringStringMap = xmlParser.parseXmlConfig(xmlParser.getPathXml());

        if (stringStringMap.get(name).equals("asc")) {
            return KindOfSort.ASC;
        }
        return KindOfSort.DESC;
    }


}



