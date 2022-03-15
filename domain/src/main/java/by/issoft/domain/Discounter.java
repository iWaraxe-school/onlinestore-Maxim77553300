package by.issoft.domain;

// strategy pattern ---------------
public interface Discounter {
    Double applyDiscount(Double price);
    boolean checkCondition();
}
