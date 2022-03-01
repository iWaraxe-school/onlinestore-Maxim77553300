package by.issoft.domain.categories;

import by.issoft.domain.Category;
import by.issoft.domain.ProductPrice;

public class MilkCategory extends Category {

    public MilkCategory() {
        this("Milk");
    }

    public MilkCategory(String nameAttribute) {
        super(nameAttribute);
    }

    @Override
    public ProductPrice generatePrice(Category category,Double min,Double max) {
        return super.generatePrice(category,1.00,10.00);
    }
}
