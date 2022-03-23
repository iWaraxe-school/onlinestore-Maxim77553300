package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.ProductPrice;

public class RandomStorePopulator {

    public String getName(Category category) {
        return category.toString() + " " + category.getFaker().company().name();
    }

    public Double getRate(Category category) {
        return (double) Math.round((1.00 + category.getFaker().random().nextDouble() * (99.00)));
    }

    public ProductPrice getPrice(Category category) {
        return category.getPrice(category);
    }

    public Integer getCategoryId(Category category){
        return category.getId();
    }

    public void setPrice(ProductPrice price) {
    }

}
