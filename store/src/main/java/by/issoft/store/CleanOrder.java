package by.issoft.store;

import by.issoft.domain.Product;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CleanOrder implements Runnable {

    private final List<Product> orderProductList;

    public CleanOrder(List<Product> orderProductList) {
        this.orderProductList = orderProductList;
    }

    @SneakyThrows
    @Override
    public void run() {
        log.info("start clean thread");
        while (true) {
            synchronized (orderProductList) {
                System.out.println("Cleaning the shopping cart " + orderProductList.toString());
                orderProductList.clear();
                System.out.println("Your list of goods is empty " + orderProductList.toString());
                orderProductList.wait(60_000);
            }
        }
    }
}
