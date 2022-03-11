package by.issoft.store.util1;

import by.issoft.domain.Product;

import java.util.Comparator;
import java.util.Map;

public class ProductComparator implements Comparator<Product> {


    @Override
    public int compare(Product o1, Product o2) {

        XmlParser xmlParser = new XmlParser();
        Map<String, String> configMap = xmlParser.parseXmlConfig(xmlParser.getPathXml());


        int comparison = 0;

        String name = configMap.get("name");
        if (name.equals("asc")) {
            comparison = o1.getName().compareTo(o2.getName());
        } else {
            comparison = o2.getName().compareTo(o1.getName());
        }
        if (comparison == 0) {
            String price = configMap.get("price");
            if (price.equals("asc")) {
                comparison = o1.getPrice().compareTo(o2.getPrice());
            } else {
                comparison = o2.getPrice().compareTo(o1.getPrice());
            }
        }

        if (comparison == 0) {
            String rate = configMap.get("rate");
            if (rate.equals("asc")) {
                comparison = o1.getRate().compareTo(o2.getRate());
            } else {
                comparison = o2.getRate().compareTo(o1.getRate());
            }
        }

        return comparison;
    }
}
