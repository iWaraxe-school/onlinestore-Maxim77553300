package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class StoreHelper {

    Store store;

    public StoreHelper() {
    }

    public Store getStore() {
        return store;
    }

    public StoreHelper(Store store) {
        this.store = store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void fillStore() {
        Map<Category, Integer> categoryProductsMapToAdd = createRandomCategoryMap();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();

        for (Map.Entry<Category, Integer> entry : categoryProductsMapToAdd.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Product product = new Product(randomStorePopulator.getName(entry.getKey())
                        , randomStorePopulator.getRate(entry.getKey())
                        , randomStorePopulator.getPrice(entry.getKey()));
                entry.getKey().addProducts(product);
            }
            this.store.categories.add(entry.getKey());
        }
    }

    public static Map<Category, Integer> createRandomCategoryMap() {
        Map<Category, Integer> categoryMap = new HashMap<>();

        Reflections reflections = new Reflections("by.issoft.domain.categories");
        Set<Class<? extends Category>> subTypesOf = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> type : subTypesOf) {
            Random random = new Random();
            try {
                categoryMap.put(type.getConstructor().newInstance(), random.nextInt(10));
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
        return categoryMap;
    }

}
