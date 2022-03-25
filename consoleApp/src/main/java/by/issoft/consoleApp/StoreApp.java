package by.issoft.consoleApp;

import by.issoft.store.util.Output;
import by.issoft.store.util.server.StoreServer;

import java.io.IOException;
import java.sql.SQLException;

public class StoreApp {
    public static void main(String[] args) throws SQLException, IOException {
        StoreServer storeServer = new StoreServer();
        storeServer.start();
        Output.printCommand();
    }

}




