package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private final String nameCategory;
    private List<Product> products;

    public Category(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public void addProducts(Product product){
       if(products == null){
           products = new ArrayList<>();
       }
       products.add(product);
    }

    public void printAllProducts(){
        for (Product product : products) {
            System.out.print(product);
            System.out.println();
        }
    }


    @Override
    public String toString() {
        return "Category{" +
                "nameCategory='" + nameCategory + '\'' +
                ", products=" + products +
                '}';
    }
}
