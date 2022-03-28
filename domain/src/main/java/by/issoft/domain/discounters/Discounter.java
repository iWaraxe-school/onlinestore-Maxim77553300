package by.issoft.domain.discounters;

// strategy pattern ---------------
public interface Discounter {
    Double applyDiscount(Double price);

    boolean checkCondition();
}
