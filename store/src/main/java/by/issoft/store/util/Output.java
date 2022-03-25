package by.issoft.store.util;

import by.issoft.domain.Product;
import by.issoft.domain.decorator.*;
import by.issoft.store.Store;
import by.issoft.store.dao.ProductDao;
import by.issoft.store.populator.PopulatorBd;
import by.issoft.store.service.ProductServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class Output {

    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static List<Product> products;
    protected static Store storeObject = Store.getInstance();
    private final static ProductDao productDao = new ProductServiceImpl();
    private final static PopulatorBd populator = new PopulatorBd();

    public static void printAllGoods() throws SQLException {
        populator.printAllGoods();
    }

    public static void printCommand() throws IOException, SQLException {

        printAllGoods();

        while (true) {
            System.out.println("PLEASE, ENTER COMMAND:\nsort \ntop \nbuy \nquit \n");
            String line = reader.readLine();
            switch (line) {
                case "sort" -> printSortedGoods();
                case "top" -> printTop();
                case "buy" -> printPurchase();//test for decorator
                case "quit" -> {
                    return;
                }
            }
        }
    }

    private static void printSortedGoods() throws SQLException {
        System.out.println("After sorting :");
        fillProducts();
        Sorting sorting = new Sorting();
        List<Product> sort = sorting.sortAllProducts(products);
        System.out.println(sort);
    }

    private static void printTop() throws SQLException {
        fillProducts();
        Sorting sorting = new Sorting();
        System.out.println(sorting.getTop());
    }

    private static void fillProducts() throws SQLException {
        ProductDao productService = new ProductServiceImpl();
        products = productService.getAll();
    }

    // Test--- Methods for Pattern Decorator---------------------------
    private static void printPurchase() throws IOException, SQLException {
        Service service = null;
        fillProducts();
        while (true) {
            System.out.println("You can buy these goods : \n " + products.toString());
            System.out.println("PLEASE, ENTER COMMAND:\nfirst - (to buy first product in the list) \nenter - (enter the name of product) \nquit \n");
            String line = reader.readLine();
            switch (line) {
                case "first" -> {
                    service = getFirstProduct();
                    printPurchaseWithBag(service);
                }
                case "enter" -> getProductToCart();
                case "quit" -> {
                    return;
                }
            }
        }
    }

    private static Service getFirstProduct() throws SQLException {
        fillProducts();
        Product product = products.get(0);
        System.out.println("You have bought :" + product.getName());
        products.remove(product);

        productDao.delete(product);
        return new BuyFirstProduct(product.getName(), product.getPrice());
    }

    private static BuyBigBag getBigBag(Service buyProduct) {
        return new BuyBigBag(buyProduct);
    }

    private static BuySmallBag getSmallBag(Service buyProduct) {
        return new BuySmallBag(buyProduct);
    }

    private static void printAll(Service buyProduct, OptionDecorator buyBag) {
        System.out.println(buyBag.getName() + " " + buyBag.getPrice());
    }

    private static void printPurchaseWithBag(Service service) throws IOException, SQLException {
        fillProducts();
        while (true) {
            System.out.println("PLEASE, CHOOSE TH BAG: \nsmall(to buy small bag) \nbig(to buy big bag) \nquit \n");
            String line = reader.readLine();
            switch (line) {
                case "big" -> {
                    getBigBag(service);
                    printAll(service, getBigBag(service));
                }
                case "small" -> {
                    getSmallBag(service);
                    printAll(service, getSmallBag(service));
                }
                case "quit" -> {
                    return;
                }
            }
        }
    }

    @SneakyThrows
    private static void getProductToCart() {
        log.info("start create order");
        System.out.println(productDao.getAll());
        String line = reader.readLine();
        Product product = productDao.getAll().stream().filter(a -> a.getName().equals(line)).findAny().orElseThrow();
        List<Product> collect = new ArrayList<>();
        populator.addToCart(product);
        System.out.println("You have bought :");
        IntStream.range(0, collect.size()).forEach(i -> System.out.println(collect.get(i)));
        products.removeAll(collect);
        productDao.delete(product);
        products = productDao.getAll();
    }
}







