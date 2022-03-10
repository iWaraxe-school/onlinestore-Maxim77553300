package by.issoft.store.util;

import by.issoft.domain.Product;

import java.util.Comparator;
import java.util.Map;

public class SortedByRate extends Sorted implements Comparator<Product> {

    private final static String name = "rate";

    @Override
    public int compare(Product product1, Product product2) {

        KindOfSort sort = getSortParams();
        Double rate1 = product1.getRate();
        Double rate2 = product2.getRate();

        if (sort == KindOfSort.ASC) {
            return rate1.compareTo(rate2);
        } else {
            return rate2.compareTo(rate1);
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
