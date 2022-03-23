package by.issoft.store.util;

import by.issoft.domain.Product;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Map;

public class ProductComparator implements Comparator<Product> {

    private final Map<String, String> sortBy;

    public ProductComparator(Map<String,String> sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public int compare(Product p1, Product p2) {
        CompareToBuilder compareBuilder = new CompareToBuilder();
        for (Map.Entry<String, String> item : sortBy.entrySet()) {
            SortOrder sortOrder = (SortOrder.valueOf(sortBy.get(item.getKey())));
            try {
                if (sortOrder == SortOrder.ASC) {
                    compareBuilder.append(this.getPropertyValue(p1, item.getKey()), this.getPropertyValue(p2, item.getKey()));
                } else {
                    compareBuilder.append(this.getPropertyValue(p2, item.getKey()), this.getPropertyValue(p1, item.getKey()));
                }
            } catch (Exception e) {
                System.out.println("Error! Products were not compared !!" + e.getMessage());
                return 0;
            }
        }
        return compareBuilder.toComparison();
    }

    private String getPropertyValue(Product product, String property) throws Exception {

        Field declaredField = product.getClass().getDeclaredField(property);
        declaredField.setAccessible(true);

        return declaredField.get(product).toString();
    }
}
