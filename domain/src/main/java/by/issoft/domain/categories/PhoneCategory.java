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
    public ProductPrice generatePrice(Category category, Double min, Double max) {
        return super.generatePrice(category,100.00,10000.00);
    }
}
