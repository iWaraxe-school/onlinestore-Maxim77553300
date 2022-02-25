package by.issoft.consoleApp;

import by.issoft.store.Store;

import java.lang.reflect.InvocationTargetException;

public class StoreApp {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<? extends Store> storeClass = Store.class;
        Store storeObject = storeClass.getConstructor().newInstance();
        storeObject.printAllStoreGoods(storeObject);

    }
}

