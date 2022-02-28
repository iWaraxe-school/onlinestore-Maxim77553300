package by.issoft.domain.categories;

import by.issoft.domain.Category;
import by.issoft.domain.ProductPrice;

public class BikeCategory extends Category {

    public BikeCategory() {
        this("Bike");
    }

    public BikeCategory(String nameAttribute) {
        super(nameAttribute);
    }

    @Override
    public ProductPrice getPrice(Category category) {
        return new ProductPrice((double) Math.round(100.00 + category.getFaker().random().nextDouble() * (1000.00)));

    }
}
