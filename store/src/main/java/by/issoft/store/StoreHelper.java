package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.dao.CategoryDao;
import by.issoft.store.service.CategoryService;
import org.reflections.Reflections;

import java.sql.SQLException;
import java.util.*;

public class StoreHelper {

    private Store store = Store.getInstance();
    private static int count = 0;

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

    public void fillStore() throws SQLException {
        Map<Category, Integer> categoryProductsMapToAdd;
        if (count == 0) {
            categoryProductsMapToAdd = createRandomCategoryMap();
        } else {
            categoryProductsMapToAdd = createCategoryMap();
        }
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
        for (Map.Entry<Category, Integer> entry : categoryProductsMapToAdd.entrySet()) {

            for (int i = 0; i < entry.getValue(); i++) {

// pattern Builder-------
                Product product = Product.newBuilder()
                        .setName(randomStorePopulator.getName(entry.getKey()))
                        .setPrice(randomStorePopulator.getPrice(entry.getKey()))
                        .setRate(randomStorePopulator.getRate(entry.getKey()))
                        .setCategory_id(randomStorePopulator.getCategoryId(entry.getKey()))
                        .build();
                entry.getKey().addProducts(product);
                store.productList.add(product);

            }

            this.store.categories.add(entry.getKey());
        }
        count++;
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

    public Map<Category, Integer> createCategoryMap() throws SQLException {
        CategoryDao categoryDao = new CategoryService();
        List<Category> categoriesList;
        categoriesList = categoryDao.getAll();
        Map<Category, Integer> categoryMap = new HashMap<>();

        Random random = new Random();
        for (Category category : categoriesList) {
            int i = random.nextInt(10);
            categoryMap.put(category, i);
        }
        return categoryMap;
    }


}
