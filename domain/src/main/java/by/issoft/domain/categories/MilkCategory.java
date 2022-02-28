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
    public ProductPrice getPrice(Category category) {
        return new ProductPrice((double) Math.round(1.00 + category.getFaker().random().nextDouble() * (10.00)));
    }
}
