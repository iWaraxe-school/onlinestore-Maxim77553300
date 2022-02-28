package by.issoft.domain.categories;

import by.issoft.domain.Category;
import by.issoft.domain.ProductPrice;

public class PhoneCategory extends Category {

    public PhoneCategory() {
        this("Phone");
    }

    public PhoneCategory(String nameAttribute) {
        super(nameAttribute);
    }

    @Override
    public ProductPrice getPrice(Category category) {
        return new ProductPrice((double) Math.round(100.00 + category.getFaker().random().nextDouble() * (10000.00)));

    }
}
