package by.issoft.store.util;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.decorator.*;
import by.issoft.store.Order;
import by.issoft.store.Store;
import by.issoft.store.dao.CategoryDao;
import by.issoft.store.dao.ProductDao;
import by.issoft.store.service.CategoryService;
import by.issoft.store.service.ProductService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Output {

    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static List<Product> products;
    protected static Store storeObject = Store.getInstance();
    private static int count = 0;

    public static void printAllGoods() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {


        Class<? extends Store> storeClass = Store.class;
        Method method = storeClass.getMethod("getInstance");
        Store storeObject = (Store) method.invoke(storeClass);
        products = storeObject.getAllStoreGoods(storeObject);

        ProductDao productService = new ProductService();

        CategoryDao categoryDao = new CategoryService();
        categoryDao.addListCategory(storeObject.categories);


        for (Category category : storeObject.categories) {
            if (category.getProducts() != null) {
                for (Product product : category.getProducts()) {
                    System.out.print(product);

                    productService.addListProduct(category.getProducts());

                }
            } else {
                continue;
            }
        }

    }

    public static void printCommand() throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, SQLException {

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
        if (count == 0) {
            products = storeObject.getAllStoreGoods(storeObject);
            for (Category category : storeObject.categories) {
                if (category.getProducts() != null) {
                    products.addAll(category.getProducts());
                } else {
                    continue;
                }
            }
            count++;
        }
    }


    // Test--- Methods for Pattern Decorator---------------------------
    private static void printPurchase() throws IOException, SQLException {
        Service service = null;
        fillProducts();
        while (true) {
            System.out.println("You can buy theese goods : \n " + products.toString());
            System.out.println("PLEASE, ENTER COMMAND:\nfirst - (to buy first product in the list) \nenter - (enter the name of product) \nquit \n");
            String line = reader.readLine();
            switch (line) {
                case "first" -> {
                    service = getFirstProduct();
                    printPurchaseWithBag(service);
                }
                case "enter" -> getProduct();
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
    private static void getProduct() {

        log.info("start create order");
        String line = reader.readLine();
        fillProducts();
        List<Product> collect = products.stream().filter(a -> a.getName().equals(line)).collect(Collectors.toList());
        System.out.println("You have bought :");
        IntStream.range(0, collect.size()).forEach(i -> System.out.println(collect.get(i)));
        new Thread(new Order(collect)).start();
        products.removeAll(collect);

    }
}







