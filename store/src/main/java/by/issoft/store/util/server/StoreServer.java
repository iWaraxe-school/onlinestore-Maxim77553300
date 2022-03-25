package by.issoft.store.util.server;

import by.issoft.store.util.server.handler.CartHandler;
import by.issoft.store.util.server.handler.CategoryHandler;
import by.issoft.store.util.server.handler.Handler;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import static com.sun.net.httpserver.HttpServer.create;

public class StoreServer {

    public static final String USER_NAME = "admin";
    public static final String PASSWORD = "password";

    public void start() {
        try {
            HttpServer httpServer = create(new InetSocketAddress(8080), 0);
            buildContext(httpServer, "/categories", new CategoryHandler());
            buildContext(httpServer, "/", new Handler());
            buildContext(httpServer, "/cart", new CartHandler());
            httpServer.setExecutor(null);
            httpServer.start();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void buildContext(HttpServer server, String path, HttpHandler handler) {
        server.createContext(path, handler).setAuthenticator(new BasicAuthenticator("test") {
            @Override
            public boolean checkCredentials(String username, String password) {
                return username.equals(USER_NAME) && password.equals(PASSWORD);
            }
        });
    }

}
