package by.issoft.store;

import by.issoft.domain.Product;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Order implements Runnable {

    private CleanOrder cleanOrder;
    private final List<Product> productList;
    private List<Product> orderProductList = new CopyOnWriteArrayList<>();

    public Order(List<Product> productList) {
        this.productList = productList;
    }


    @SneakyThrows
    @Override
    public void run() {
        // Integer randomTime = new Random().ints(1, 1, 31).boxed().findAny().get();
        int randomTime = ThreadLocalRandom.current().nextInt(1, 31);
        synchronized (productList) {
            log.info("start order thread, time of processing your order :" + randomTime);
            productList.wait(randomTime * 1000);
            cleanOrder = new CleanOrder(orderHandler());
            (new Thread(cleanOrder)).start();
        }
    }

    @SneakyThrows
    private synchronized List<Product> orderHandler() {
        orderProductList = this.productList;
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Your order is completed :" + orderProductList.toString());
        return orderProductList;
    }
}
