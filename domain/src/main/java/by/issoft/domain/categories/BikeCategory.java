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
    public ProductPrice generatePrice(Category category,Double min,Double max) {
        return super.generatePrice(category,100.00,1000.00);
    }
}
