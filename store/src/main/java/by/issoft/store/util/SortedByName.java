package by.issoft.store.util;

import by.issoft.domain.Product;

import java.util.Comparator;
import java.util.Map;

public class SortedByName extends Sorted implements Comparator<Product> {

    private final static String name = "name";

    @Override
    public int compare(Product product1, Product product2) {

        KindOfSort sort = getSortParams();
        String name1 = product1.getName();
        String name2 = product2.getName();
        if (sort == KindOfSort.ASC) {
            return name1.compareTo(name2);
        } else {
            return name2.compareTo(name1);
        }
    }

    private static KindOfSort getSortParams() {

        XmlParser xmlParser = new XmlParser();
        Map<String, String> stringStringMap = xmlParser.parseXmlConfig("store/src/main/resources/config.xml");

        if (stringStringMap.get(name).equals("asc")) {
            return KindOfSort.ASC;
        }
        return KindOfSort.DESC;
    }
}
