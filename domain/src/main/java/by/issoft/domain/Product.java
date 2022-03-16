package by.issoft.domain;


import by.issoft.domain.discounters.ChristmasDiscounter;
import by.issoft.domain.discounters.Discounter;
import by.issoft.domain.discounters.WeekDiscounter;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String name;
    private Double rate;
    private ProductPrice price;
    // Discounter----Strategy pattern --------
    Discounter discounterWeek = new WeekDiscounter();
    Discounter discountChristmas = new ChristmasDiscounter();

    public static Builder newBuilder() {
        return new Product().new Builder();
    }
    // use Pattern Builder for creating Product
    public class Builder {

        private String name;
        private Double rate;
        private ProductPrice price;

        private Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRate(Double rate) {
            this.rate = rate;
            return this;
        }

        public Builder setPrice(ProductPrice price) {
            this.price = price;
            return this;
        }

        public Product build() {
            Product.this.name = this.name;
            // Product.this.price = this.price;
            Product.this.price = discountPrice(this.price);
            Product.this.rate = this.rate;
            return Product.this;
        }

        private ProductPrice discountPrice(ProductPrice price) {
            List<Discounter> discounterList = new ArrayList<>();
            discounterList.add(discounterWeek);
            discounterList.add(discountChristmas);
            for(Discounter discounter : discounterList) {
                if (discounterWeek.checkCondition()) {
                    Double price1 = price.getPrice();
                    this.price = ProductPrice.of(discounterWeek.applyDiscount(price1));
                } else {
                    this.price = price;
                }
            }
            return this.price;
        }
    }

    public Product getProduct() {
        return this;
    }

    public String getName() {
        return name;
    }

    public Double getRate() {
        return rate;
    }

    public Double getPrice() {
        return price.getPrice();
    }

    @Override
    public String toString() {
        String message;
        if (discounterWeek.checkCondition()) {
            message = "Discount!!!";
        } else {
            message = "";
        }
        return "Product " +
                "name:'" + name + '\'' +
                ", rate:" + rate +
                "," + message + price + "\n";
    }

}
